package com.elogstation.api.elogstationapi.checksum;

import org.junit.Test;

import static com.elogstation.api.elogstationapi.checksum.Common.getCharacterMapping;
import static org.junit.Assert.*;

public class CommonTest {

    @Test
    public void getCharacterMappingTest() {
        assertEquals(getCharacterMapping("012, ABC, xyz!"), 276);
        assertEquals(getCharacterMapping("1"), 1);
        assertEquals(getCharacterMapping("051217"), 16);
        assertEquals(getCharacterMapping("134410"), 13);
        assertEquals(getCharacterMapping("ZZZZZ12345"), 225);
        assertEquals(getCharacterMapping("Jon_Doe"), 287);
        assertEquals(getCharacterMapping("-87.07"), 22);

    }
}