package com.kagwisoftwares.nyt;

public class PopularArticleModel {

    private String title;
    private String abstractTxt;
    private String byLine;

    public PopularArticleModel(String title, String abstractTxt, String byLine) {
        this.title = title;
        this.abstractTxt = abstractTxt;
        this.byLine = byLine;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractTxt() {
        return abstractTxt;
    }

    public String getByLine() {
        return byLine;
    }
}
