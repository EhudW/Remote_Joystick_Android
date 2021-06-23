package com.example.remotejoystick;

public class ErrorEventArgs {
    public String description;
    public Throwable error;
    public ErrorEventArgs(String description, Throwable error){
        if (description != null)
            this.description = description;
        else
            this.description = "";
        this.error = error;
    }
}
