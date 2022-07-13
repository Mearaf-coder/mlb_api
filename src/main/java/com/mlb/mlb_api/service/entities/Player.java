package com.mlb.mlb_api.service.entities;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    private String name;

    private Integer age;

    @Column (name = "years_of_experience")
    private Double yearsOfExperience;

    private Double rating;


    public Player() {
    }

    public Player(PlayerDTO playerDTO) {
        this.name = playerDTO.getName();
        this.age = playerDTO.getAge();
        this.yearsOfExperience= playerDTO.getYearsOfExperience();
        this.rating = playerDTO.getRating();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

