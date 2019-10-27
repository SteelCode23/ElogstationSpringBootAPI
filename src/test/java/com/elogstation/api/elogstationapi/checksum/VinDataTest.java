package com.elogstation.api.elogstationapi.checksum;

import org.junit.Test;

import static com.elogstation.api.elogstationapi.checksum.VinData.calVinCheckDigit;
import static org.junit.Assert.*;

public class VinDataTest {

    @Test
    public void calVinCheckDigitTest() {
        assertEquals(calVinCheckDigit("ABCDEFG1534567890"), '5');
    }
}