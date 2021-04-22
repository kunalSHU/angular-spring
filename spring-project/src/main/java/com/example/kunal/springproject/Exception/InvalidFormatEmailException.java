package com.example.kunal.springproject.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus
public class InvalidFormatEmailException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2990989836435449879L;

    public InvalidFormatEmailException(String msg) {
        super(msg);
    }
}
