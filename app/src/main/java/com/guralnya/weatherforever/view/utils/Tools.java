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

}
