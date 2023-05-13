package com.porfolio.LeoCastillo.Dto;

import javax.validation.constraints.NotBlank;

public class dtoContact {
    @NotBlank
    private String description;
    private String facebook;
    private String twitter;
    private String google;
    private String linkedin;
    private String instagram;
    private String whatsapp;
    
    public dtoContact() {
    }

    public dtoContact(String description, String facebook, String twitter , String google, String linkedin, String instagram,
            String whatsapp) {
        this.description = description;
        this.facebook = facebook;
        this.twitter = twitter;
        this.google = google;
        this.linkedin = linkedin;
        this.instagram = instagram;
        this.whatsapp = whatsapp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }
    
    

}
