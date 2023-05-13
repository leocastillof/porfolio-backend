package com.porfolio.LeoCastillo.Dto;

import javax.validation.constraints.NotBlank;

public class dtoHyS {
    @NotBlank
    private String name;
    @NotBlank
    private int percentage;

    public dtoHyS() {
    }

    public dtoHyS(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
