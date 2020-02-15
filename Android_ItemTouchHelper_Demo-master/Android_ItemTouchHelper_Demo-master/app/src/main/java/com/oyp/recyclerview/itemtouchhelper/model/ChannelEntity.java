package com.oyp.recyclerview.itemtouchhelper.model;

import java.io.Serializable;

/**
 * 频道实体类
 */
public class ChannelEntity implements Serializable {

    private String id;
    private String name;
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChannelEntity(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    //    @Override
//    public String toString() {
//        return "ChannelEntity{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}