package com.deshmukhamit.udemyspringbootrest.todos;

import java.util.Date;

public class Todo {
    private long id;
    private String username;
    private String description;

    public Todo(long id, String username, String description, Date targetDate, boolean isDone) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    private Date targetDate;
    private boolean isDone;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
}
