package com.yjx.solr.model;

import org.apache.solr.client.solrj.beans.Field;

/** 
 * <p> 
 *
 * </p> 
 *
 * @author yejx 
 * @date 2019/9/17 16:09
 */ 
public class Person {

    @Field(value = "id")
    private String id;

    @Field(value = "name")
    private String name;

    @Field(value = "description")
    private String description;

    public Person() {
    }

    public Person(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
