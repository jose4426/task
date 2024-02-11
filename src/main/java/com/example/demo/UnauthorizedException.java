package com.example.demo;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(final String msg) {
        super( msg);
    }
}
