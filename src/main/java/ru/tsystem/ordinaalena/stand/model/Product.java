package ru.tsystem.ordinaalena.stand.model;

public class Product {
    private Long id;
    private String title;
    private String price;
    private String image;
    private String numberOfSales;

    public Product() {
    }

    public Product(String title, String price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public Product(Long id, String title, String price, String image, String numberOfSales) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.numberOfSales = numberOfSales;
    }
    public Product(Long id, String title, String price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(String numberOfSales) {
        this.numberOfSales = numberOfSales;
    }
}
