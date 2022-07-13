package com.mlb.mlb_api.service;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.service.entities.Player;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayerService{
    Player save (PlayerDTO playerDTO);
    Player update(PlayerDTO playerDTO, Integer playerId);
    void delete(Integer playerId);
    void delete(String name);
    void delete(Double rating);
    Iterable<Player> findAll(Sort name);
    Player findById(Integer playerId);

    Player findByName(String name);
    Player findByNameAndAge(String name, Integer age);

    List<Player> findByAgeLessThan(Integer age);
    List<Player> findByRatingLessThan(Double rating);

    List<Player> findByAgeGreaterThan(Integer age);
    List<Player> findByYearsOfExperienceLessThan(Double yearsOfExperience);

    Player findByRating(Double rating);


}
