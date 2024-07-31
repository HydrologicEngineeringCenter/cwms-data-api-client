/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_JSON;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

import java.util.Objects;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;


public final class ProjectLockRevokerRightsInput {
    static final String OFFICE_MASK = "office-mask";
    static final String OFFICE = "office";
    static final String PROJECT_MASK = "project-mask";
    static final String APPLICATION_MASK = "application-mask";
    static final String USER = "user-id";
    static final String ALLOW = "allow";

    private ProjectLockRevokerRightsInput() {
        throw new AssertionError("factory class");
    }

    public static ProjectLockRevokerRightsInput.GetAll getAll(String officeMask,
                                                              String projectMask, String applicationMask) {
        return new ProjectLockRevokerRightsInput.GetAll(officeMask, projectMask, applicationMask);
    }

    public static ProjectLockRevokerRightsInput.Update update(String sessionOffice, String officeMask,
                                                              String projectMask, String applicationMask, String userId, boolean allow) {
        return new ProjectLockRevokerRightsInput.Update(sessionOffice, officeMask, projectMask, applicationMask, userId, allow);
    }

    public static final class GetAll extends EndpointInput {
        private final String officeMask;
        private final String projectMask;
        private final String applicationMask;

        private GetAll(String officeMask, String projectMask, String applicationMask) {
            this.officeMask = Objects.requireNonNull(officeMask, "Cannot retrieve LockRevokerRights without an officeMask.");
            this.projectMask = projectMask;
            this.applicationMask = applicationMask;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(OFFICE_MASK, officeMask)
                    .addQueryParameter(PROJECT_MASK, projectMask)
                    .addQueryParameter(APPLICATION_MASK, applicationMask)
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

    public static final class Update extends EndpointInput {

        private final String sessionOffice;
        private String officeMask;
        private String projectMask;
        private final String applicationMask;
        private final String user;
        private final boolean allow;

        private Update(String sessionOffice, String officeMask, String projectMask, String applicationMask, String user, boolean allow) {
            this.sessionOffice = Objects.requireNonNull(sessionOffice, "Cannot update LockRevokerRights without a session office.");
            this.officeMask = officeMask;
            this.projectMask = projectMask;
            this.applicationMask = Objects.requireNonNull(applicationMask, "Cannot update LockRevokerRights without a application mask.");
            this.user = Objects.requireNonNull(user, "Cannot update LockRevokerRights without a user.");
            this.allow = allow;
        }

        public Update officeMask(String mask) {
            this.officeMask = mask;
            return this;
        }

        public Update projectMask(String mask) {
            this.projectMask = mask;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder
                    .addQueryParameter(OFFICE, sessionOffice)
                    .addQueryParameter(OFFICE_MASK, officeMask)
                    .addQueryParameter(PROJECT_MASK, projectMask)
                    .addQueryParameter(APPLICATION_MASK, applicationMask)
                    .addQueryParameter(USER, user)
                    .addQueryParameter(ALLOW, String.valueOf(allow))
                .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_JSON);
        }
    }

}
