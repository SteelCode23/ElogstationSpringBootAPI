package com.elogstation.api.elogstationapi.checksum;

import org.junit.Test;

import static com.elogstation.api.elogstationapi.checksum.LineData.mainCheck;
import static org.junit.Assert.*;

public class LineDataTest {

    @Test
    public void mainCheckTest() {
        assertEquals(mainCheck(104), "D5");
    }
}