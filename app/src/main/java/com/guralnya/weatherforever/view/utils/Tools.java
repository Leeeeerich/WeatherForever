package com.guralnya.weatherforever.view.utils;

import com.guralnya.weatherforever.utils.Constants;

public class Tools {

    public static String setPositiveSymbol(String s) {
        if (Integer.parseInt(s) > 0) {
            return "+" + s + Constants.DEGREE;
        }
        return s + Constants.DEGREE;
    }

    public static String pascalToMillimetersOfMercury(String s) {
        return String.valueOf(Math.round(Integer.valueOf(s) / 1.333));
    }

    /**
     * Convert from double to integer in string
     *
     * @param s - double number in String
     * @return - integer in String
     */
    public static String convertDoubleToInt(String s) {
        return String.valueOf(Math.round(Double.valueOf(s)));
    }

}
