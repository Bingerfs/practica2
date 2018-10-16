package com.practica2.practica2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private String category;

    @Min(value = 0, message ="El valor debe ser mayor a 0")
    private Integer likes;

    public Integer getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getCategory(){
        return category;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setCategory(String cat){
        this.category=cat;
    }

    public void setLikes(Integer likes){
        this.likes=likes;
    }

    public Integer getLikes(){
        return likes;
    }
}
