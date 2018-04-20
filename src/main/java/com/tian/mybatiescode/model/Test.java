package com.tian.mybatiescode.model;

import java.io.Serializable;

public class Test implements Serializable{
    private static final long serialVersionUID = 5853743993871061222L;
    private Long id;

    private String test;

    private Long userId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test == null ? null : test.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", test='" + test + '\'' +
                ", userId=" + userId +
                '}';
    }
}