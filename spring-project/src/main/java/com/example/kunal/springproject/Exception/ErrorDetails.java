package com.example.kunal.springproject.Exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetails {


    private Date date;
    private String message;
    private String details;

    public ErrorDetails(Date date, String message, String details){
        super();
        this.date = date;
        this.message = message;
        this.details = details;
    }
}
