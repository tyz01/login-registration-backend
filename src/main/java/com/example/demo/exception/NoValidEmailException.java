package com.example.demo.exception;

public class NoValidEmailException extends Exception{
    public NoValidEmailException(String message){
        super(message);
    }
}
