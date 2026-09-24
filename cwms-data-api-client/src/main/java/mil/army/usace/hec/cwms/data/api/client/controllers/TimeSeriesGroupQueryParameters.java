/*
 * MIT License
 *
 * Copyright (c) 2026 Hydrologic Engineering Center
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

package mil.army.usace.hec.cwms.data.api.client.controllers;

/**
 * Query parameter names shared by the v1 and v2 time series group endpoint inputs.
 */
final class TimeSeriesGroupQueryParameters {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String CATEGORY_ID_QUERY_PARAMETER = "category-id";
    static final String INCLUDE_ASSIGNED_QUERY_PARAMETER = "include-assigned";
    static final String FAIL_IF_EXISTS = "fail-if-exists";
    static final String CATEGORY_MASK_QUERY_PARAMETER = "timeseries-category-like";
    static final String GROUP_MASK_QUERY_PARAMETER = "timeseries-group-like";
    static final String CATEGORY_OFFICE_QUERY_PARAMETER = "category-office-id";
    static final String GROUP_OFFICE_QUERY_PARAMETER = "group-office-id";
    static final String CASCADE_DELETE_QUERY_PARAMETER = "cascade-delete";
    static final String REPLACE_ASSIGNED_TS_QUERY_PARAMETER = "replace-assigned-ts";

    private TimeSeriesGroupQueryParameters() {
        throw new AssertionError("constants class");
    }
}
