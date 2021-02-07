package com.example.kunal.springproject.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EmptyEmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmptyEmailException(String msg) {
        super(msg);
    }

    public EmptyEmailException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
