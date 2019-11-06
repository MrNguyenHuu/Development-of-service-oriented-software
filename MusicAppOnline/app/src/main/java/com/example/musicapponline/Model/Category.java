package com.example.musicapponline.Model;

import java.io.Serializable;

public class Category implements Serializable {
    private long id;
    private long topic_id;
    private String name;
    private String image;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTopic_id() {
        return topic_id;
    }
    public void setTopic_id(long topic_id) {
        this.topic_id = topic_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
