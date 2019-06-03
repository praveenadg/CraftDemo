package com.mycompany.craftdemo.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Tweet {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int tweetId;
    private String message;
    private int userId;
    //@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

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
        preUpdate();
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime= updateTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime= createTime;
    }
    @PreUpdate
    private void preUpdate() {
        this.updateTime = new Date();
    }
    @PreRemove
    protected void preRemove() {
        this.updateTime = new Date();
    }
    
}
