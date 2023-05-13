package com.porfolio.LeoCastillo.Dto;

import javax.validation.constraints.NotBlank;

public class dtoAboutMe {
    @NotBlank
    private String description;

    public dtoAboutMe() {
    }

    public dtoAboutMe(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
