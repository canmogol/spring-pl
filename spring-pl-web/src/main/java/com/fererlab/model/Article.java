package com.fererlab.model;

import javax.persistence.Entity;

@Entity
public class Article extends BaseModel<Integer> {

    private String url;

    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + getId() +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
