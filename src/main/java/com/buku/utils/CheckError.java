package com.buku.utils;

public class CheckError {

    static public void env() {
        if (System.getenv("env") == null) {
            throw new RuntimeException("env is null, please set env first");
        }

        if (!System.getenv("env").equals("test")) {
            throw new RuntimeException("env must be test on removeAll function");
        }
    }

}
