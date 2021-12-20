package com.buku.exception;

public class BukuNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "buku not found";
    }
}
