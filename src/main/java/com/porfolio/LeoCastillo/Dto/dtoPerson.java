package com.porfolio.LeoCastillo.Dto;

import javax.validation.constraints.NotBlank;

public class dtoPerson {
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    private String description;
    @NotBlank
    private String img;

    public dtoPerson() {
    }

    public dtoPerson(String name, String lastname, String description, String img) {
        this.name = name;
        this.lastname = lastname;
        this.description = description;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}
