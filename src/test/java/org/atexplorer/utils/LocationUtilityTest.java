package org.atexplorer.utils;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LocationUtilityTest {

    @Test
    void testGetX(){
        int x = LocationUtility.getX("A1");
        assertEquals(0, x);
    }

    @Test
    void testGetY(){
        int y = LocationUtility.getY("E1");
        assertEquals(4, y);
    }

    @Test
    void testGetAlphaLocation(){
        String alphaLocation = LocationUtility.getAlphaLocation(65);
        assertEquals("F7", alphaLocation);
    }

    @Test
    void testGetNumericLocation(){
        int numericLocation = LocationUtility.getNumericLocation("F7");
        assertEquals(65, numericLocation);
    }
}
