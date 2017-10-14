package com.example.shivang.myapplication;

/**
 * Created by shivang on 14/10/17.
 */

public class ArticleModel {

    String author;
    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;

    public ArticleModel(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

}