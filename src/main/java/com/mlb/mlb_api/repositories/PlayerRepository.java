package com.mlb.mlb_api.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.mlb.mlb_api.service.entities.Player;

import java.util.List;

public interface PlayerRepository extends CrudRepository  <Player, Integer> {

    Player findByName(String name);
    Player findByNameAndAge(String name, Integer age);
    List<Player> findByAgeLessThan(Integer age);
    List<Player> findByRatingLessThan(Double rating);

    List<Player> findByAgeGreaterThan(Integer age);

    List<Player> findByYearsOfExperienceLessThan(Double yearsOfExperience);

    Player findByRating(Double rating);

    List<Player> findByRatingGreaterThan(Double rating);

}

















