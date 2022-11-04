/*
 * Copyright (c) 2022
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.aaa.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

final class CacKeyManagerTest {

    @Test
    void testPivEdipiPattern() {
        assertTrue(CacKeyManager.EDIPI_PATTERN.matcher("1234567890123456@mil").matches());
        assertTrue(CacKeyManager.EDIPI_PATTERN.matcher("1234567890123456@MIL").matches());
        assertFalse(CacKeyManager.EDIPI_PATTERN.matcher("1234567890123456@ARMY").matches());
        assertFalse(CacKeyManager.EDIPI_PATTERN.matcher("234567890123456@mil").matches());
    }
}
