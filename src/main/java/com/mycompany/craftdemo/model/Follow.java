package com.mycompany.craftdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.util.Date;

@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int followId;
    private int userId;
    private int followingUser;
    private Date createTime;
    private Date updatedDate;

    public Follow() {}

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowingUser() {
        return followingUser;
    }

    public void setFollowingUser(int followingUser) {
        this.followingUser = followingUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

   
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @PrePersist
    private void prePersist() {
        this.createTime = new Date();
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
