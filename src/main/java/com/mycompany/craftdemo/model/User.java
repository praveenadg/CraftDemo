package com.mycompany.craftdemo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int userId;
    private String name;
    private int age;
    private Date createTime;
    private Date updatedDate;
    private boolean active;

    protected  User (){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
    
}
