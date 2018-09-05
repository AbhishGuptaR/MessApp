package com.example.android.sociohack;

class MyData {
    private int id,price;
    private String name,imgurl;

    public MyData(int id, String name, String imgurl, int price) {
        this.id = id;
        this.name = name;
        this.imgurl = imgurl;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
