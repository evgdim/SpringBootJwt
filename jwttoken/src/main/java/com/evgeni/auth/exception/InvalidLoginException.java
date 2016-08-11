package com.evgeni.auth.exception;

/**
 * Created by evgeni on 8/11/2016.
 */
public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException(Exception ex){
        super(ex);
    }
}
