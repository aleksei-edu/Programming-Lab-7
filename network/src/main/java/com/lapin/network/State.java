package com.lapin.network;

public class State {
    private static boolean performanceStatus = true;
    public static boolean getPS() {
        return performanceStatus;
    }
    public static void setPS(boolean performanceStatus) {
        State.performanceStatus = performanceStatus;
    }
    public static void switchPS() {
        performanceStatus = !performanceStatus;
    }
}
