package com.porfolio.LeoCastillo.Security.Controller;

public class Message {
    private String message;
    
    // Constructor
    public Message()
    {
        
    }
    
    public Message(String messager)
    {
        this.message = messager;
    }
    
    // Getter & Setter
    public String getMessager()
    {
        return message;
    }
    
    public void setMessager(String messager)
    {
        this.message = messager;
    }
}
