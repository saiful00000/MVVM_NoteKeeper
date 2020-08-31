package com.example.mvvmnotekeeper.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ViewNote extends BaseObservable {
    private int id;
    private String title;
    private String body;
    private String priority;

    public ViewNote() {
    }

    public ViewNote(int id, String title, String body, String priority) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.priority = priority;
    }

    @Bindable
    public int getId() {
        return id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getBody() {
        return body;
    }

    @Bindable
    public String getPrioprity() {
        return priority;
    }
}
