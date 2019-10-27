package com.elogstation.api.elogstationapi.checksum;

import org.junit.Test;

import static com.elogstation.api.elogstationapi.checksum.FileData.mainCheck;
import static org.junit.Assert.*;

public class FileDataTest {

    @Test
    public void mainCheckTest() {

        assertEquals(mainCheck(2470), "DEA9");
    }
}