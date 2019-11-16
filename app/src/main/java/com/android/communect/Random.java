package com.android.communect;

import java.nio.charset.Charset;

public class Random {
    public static String RandomString() {
        java.util.Random random = new java.util.Random();
        byte[] RAWWWWWWarray = new byte[10];
        random.nextBytes(RAWWWWWWarray);
        return new String(RAWWWWWWarray, Charset.forName("UTF-8"));
    }
}
