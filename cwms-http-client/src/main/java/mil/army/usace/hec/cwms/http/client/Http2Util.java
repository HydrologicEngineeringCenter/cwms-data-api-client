/*
 * Copyright (c) 2023
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client;

final class Http2Util {

    private Http2Util() {
        throw new AssertionError("Utility class");
    }

    static boolean isHttp2NativelySupported() {
        boolean retVal = false;
        String version = System.getProperty("java.version");
        if (version.startsWith("1.")) {
            version = version.substring(2, 3);
        } else { //if Java 9 or higher
            int dot = version.indexOf(".");
            if (dot != -1) {
                version = version.substring(0, dot);
            }
        }
        int majorVersion = Integer.parseInt(version);
        if (majorVersion == 8) {
            String minorVersionStr = version.substring(version.lastIndexOf("_") + 1);
            retVal = Integer.parseInt(minorVersionStr) >= 251;
        } else if (majorVersion > 8) {
            retVal = true;
        }
        return retVal;
    }
}
