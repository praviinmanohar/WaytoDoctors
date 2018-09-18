package com.waytodoctors.way2doctors;

/**
 * Created by new on 15/3/17.
 */

public class HomeItem {

    private String name;
    private int image;


    public HomeItem() {
    }

    public HomeItem(String name , int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



}
