package com.porfolio.LeoCastillo.Dto;

import javax.validation.constraints.NotBlank;

public class dtoExperience {
    @NotBlank
    private String nameE;
    @NotBlank
    private String descriptionE;
    
    // Constructors
    public dtoExperience()
    {
    }
    
    public dtoExperience(String nameE, String descriptionE)
    {
        this.nameE = nameE;
        this.descriptionE = descriptionE;
    }
    
    // Getters & Setters
    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE;
    }

    public String getDescriptionE() {
        return descriptionE;
    }

    public void setDescriptionE(String descriptionE) {
        this.descriptionE = descriptionE;
    }
}