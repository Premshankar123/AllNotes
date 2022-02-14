package com.example.androidtask;

public class NotesDetails {
    String imageurl,title,description;

    public NotesDetails(String imageurl, String title, String description) {
        this.imageurl = imageurl;
        this.title = title;
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
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
}
