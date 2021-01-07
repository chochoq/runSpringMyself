package com.example.ex01;

public class ItemVO {
    private String name;
    //이미지의 주소 저장이되서 INT
    private int image;
    private int price;

    public ItemVO(String name, int image, int price) {
        this.name = name;
        this.image = image;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
