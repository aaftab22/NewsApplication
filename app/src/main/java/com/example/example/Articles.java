package com.example.example;

import java.util.ArrayList;

public class Articles {

    private String title,description,url,urlToImage,content;

    public Articles(String title, String description, String url, String urlToImage, String content) {

        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
