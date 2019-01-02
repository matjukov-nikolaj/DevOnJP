package com.javacourse2018.model;

import java.util.HashMap;
import java.util.Map;

public enum DisplayMode {

    UNIFIED("unified"),
    SPLIT("split");

    private final String name;

    DisplayMode(final String name) {
        this.name = name;
    }

    private static Map<String, DisplayMode> getDisplayMode() {
        HashMap<String, DisplayMode> stringToDisplayMode = new HashMap<>();
        stringToDisplayMode.put(UNIFIED.name, UNIFIED);
        stringToDisplayMode.put(SPLIT.name, SPLIT);
        return stringToDisplayMode;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static DisplayMode createFromString(String mode) {
        return getDisplayMode().get(mode);
    }
}
