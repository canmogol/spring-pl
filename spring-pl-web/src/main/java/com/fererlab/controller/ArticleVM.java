package com.fererlab.controller;

public class ArticleVM {
    private String title;

    public ArticleVM() {
    }

    public ArticleVM(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
