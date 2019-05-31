package com.mycompany.craftdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.util.Date;

@Entity
public class Tweet {
    
    @Id
    @GeneratedValue
    private int tweetId;
    private String message;
    private int userId;
    private Date createTime;
    private Date updatedDate;

    protected Tweet() {}

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @PrePersist
    private void prePersist() {
        this.createTime = new Date();
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }
    @PreUpdate
    private void preUpdate() {
        this.updatedDate = new Date();
    }
    @PreRemove
    protected void preRemove() {
        this.updatedDate = new Date();
    }
    
}
