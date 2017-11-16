package com.penta.boxable_android.utils;

import java.util.HashMap;
import java.util.Map;

public class ColorUtils {

    public static Map<String, Integer> parse(int color) {
        HashMap<String, Integer> map = new HashMap<>(3);
        int r = (color >> 16) & 0xff;
        int g = (color >>  8) & 0xff;
        int b = (color      ) & 0xff;

        map.put("r", r);
        map.put("g", g);
        map.put("b", b);

        return map;
    }
}
