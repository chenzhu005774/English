package com.amt.cip;

public class Identify {
    static {
        System.loadLibrary("native-lib");
    }
    private Identify(){
    }

    private static Identify identify;

    public static Identify getIdentifyInstance(){

        if (null==identify){
            identify = new Identify();
        }
        return identify;
    }

    public native int getAntennaSum(int antennaSum);
    public native int getInfo();

}
