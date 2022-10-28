/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.aaa.client;

public final class CacCertificateException extends Exception {

    CacCertificateException(String message, Exception e) {
        super(message, e);
    }

    public CacCertificateException(String message) {
        super(message);
    }

}
