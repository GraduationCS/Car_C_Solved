package com.example.car_s;

public class Uploadinfo {

    public String imageName;
    public String imageURL;
    public Uploadinfo(){}

    public Uploadinfo(String url) {
        this.imageURL = url;
    }

    public String getImageName() {
        return imageName;
    }
    public String getImageURL() {
        return imageURL;
    }
}