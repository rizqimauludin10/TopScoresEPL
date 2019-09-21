package com.example.topscoresepl.model;

public class PictPlayer {
    private Integer id;
    private Integer image;

    public PictPlayer(Integer id, Integer image) {
        this.id = id;
        this.image = image;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }



}
