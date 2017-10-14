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

    boolean is_fake;
    String unique_id;

    public boolean is_fake() {
        return is_fake;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setIs_fake(boolean is_fake) {
        this.is_fake = is_fake;
    }

    public ArticleModel(String author, String title, String description, String url, String urlToImage, String publishedAt, String unique_id) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.unique_id = unique_id;
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

    public String getUrl() {return url;}

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

}
