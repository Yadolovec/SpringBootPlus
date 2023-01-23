package com.RESTApp.App.utils;

public class PersonNotCreatedException extends RuntimeException{
    public PersonNotCreatedException(String msg){
        super(msg);
    }
}
