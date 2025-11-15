package com.sena.helloworld.Dto;

public class response {
 
 private   String message;

    public response(String message) {
        this.message = message;
    }

  public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}