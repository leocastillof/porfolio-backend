package com.porfolio.LeoCastillo.Security.Controller;

public class Message {
    private String messager;
    
    // Constructor
    public Message()
    {
        
    }
    
    public Message(String messager)
    {
        this.messager = messager;
    }
    
    // Getter & Setter
    public String getMessager()
    {
        return messager;
    }
    
    public void setMessager(String messager)
    {
        this.messager = messager;
    }
}
