package org.hld.hugold.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends  RuntimeException{
    private static final long serialVersionUID = 4936006127238912453L;

    public CustomerNotFoundException(){
        super();
    }

    public CustomerNotFoundException(String message){
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public CustomerNotFoundException(Throwable cause){
        super(cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
