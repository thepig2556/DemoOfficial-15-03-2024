package com.example.demo;

public class Model {
    String id;
    String title;
    String image;
    String author;
    public Model() {
    }

    public Model(String id, String title, String image, String author) {
        this.id=id;
        this.title = title;
        this.image = image;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
