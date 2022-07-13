package com.mlb.mlb_api.controllers.dto;

import javax.persistence.*;
import java.util.List;

public class PlayerDTO {


    private String name;
    private Integer age;
    private Double yearsOfExperience;
    private Double rating;


    public PlayerDTO() {
    }

    public PlayerDTO(String name, Integer age, Double yearsOfExperience, Double rating) {
        this.name = name;
        this.age = age;
        this.yearsOfExperience= yearsOfExperience;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

