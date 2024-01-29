package com.example.travelling;

public class Item {

    String cityName;
    String country;
    int image;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Item(String cityName, String country, int image) {
        this.cityName = cityName;
        this.country = country;
        this.image = image;
    }


}
