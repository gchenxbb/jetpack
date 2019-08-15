/**
 * Copyright 2019 bejson.com
 */
package com.livedata.app;

import java.io.Serializable;
import java.util.List;

/**
 */
public class Subjects implements Serializable {

    private String title;
    private String year;
    private String id;

    public Subjects() {
    }

    public Subjects(String title, String year, String id) {
        this.title = title;
        this.year = year;
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}