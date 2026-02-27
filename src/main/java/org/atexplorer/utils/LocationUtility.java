package org.atexplorer.utils;

import org.atexplorer.Controller;

public class LocationUtility {

    public static int getX(String location){
        String[] cords = location.split("");
        return Integer.parseInt(cords[1])-1;
    }

    public static int getY(String location){
        String[] cords = location.split("");
        return cords[0].charAt(0) - 65;
    }

    public static String getAlphaLocation(int numericLocation){
        String character = String.valueOf(((char) (numericLocation % Controller.COLUMNS + 65)));
        return  character + (numericLocation / Controller.ROWS + 1);
    }

    public static int getNumericLocation(String alphaLocation){
        return getX(alphaLocation)*10 + getY(alphaLocation);
    }
}
