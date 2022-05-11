package com.pavelvic.ya_calc_ui_tests;

public class Utils {

    public static void waitSomething(long mills)
    {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
