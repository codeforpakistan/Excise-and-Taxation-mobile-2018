package com.emvsc.excise.utilClasses;



/**
 * Created by shahzaib on 14-Aug-18.
 */

public class GlobalApp {
    private static GlobalApp instance = new GlobalApp();

    private GlobalApp() {}

    public static GlobalApp getInstance() {
        return instance;
    }

    public Values details = new Values ();
}