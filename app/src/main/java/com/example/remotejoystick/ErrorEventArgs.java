package com.example.remotejoystick;

public class ErrorEventArgs {
    public final String description;
    public final Throwable error;
    public ErrorEventArgs(String description, Throwable error){
        if (description != null)
            this.description = description;
        else
            this.description = "";
        this.error = error;
    }
}
