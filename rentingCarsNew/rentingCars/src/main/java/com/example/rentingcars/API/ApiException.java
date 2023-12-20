package com.example.rentingcars.API;



public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}