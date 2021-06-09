package com.kagwisoftwares.nyt;

public class ArticleModel {

    public String articleName;
    public String articleAbstract;
    public String articleImage;
    public String articleByline;

    public ArticleModel(String articleName, String articleAbstract, String articleImage, String articleByline) {
        this.articleName = articleName;
        this.articleAbstract = articleAbstract;
        this.articleImage = articleImage;
        this.articleByline = articleByline;
    }
}
