package com.elogstation.api.elogstationapi.checksum;

import org.junit.Test;

import static com.elogstation.api.elogstationapi.checksum.EventData.mainCheck;
import static org.junit.Assert.*;

public class EventDataTest {

    @Test
    public void mainCheckTest() {
        assertEquals(mainCheck(587), "99");
    }
}