package com.mlb.mlb_api.controllers;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.service.PlayerService;
import com.mlb.mlb_api.service.entities.Player;
import com.mlb.mlb_api.repositories.PlayerRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;


    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/addPlayer")
    public Player createPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.save(playerDTO);
    }


    @GetMapping("/all")
    public Iterable<Player> getAllPlayers() {
        return playerService.findAll((Sort.by(Sort.Direction.ASC, "name")));
    }

    @GetMapping("/getByName")
    public Player getPlayerByName(@RequestParam String name) {
        return this.playerService.findByName(name);
    }

    @GetMapping("/getByNameAndAge")
    public Player getPlayerByNameAndAge(@RequestParam String name, @RequestParam Integer age) {
        return this.playerService.findByNameAndAge(name, age);
    }


    @GetMapping("/getById/{id}")
    public Player getById(@PathVariable Integer id) {
        return playerService.findById(id);
    }


    //Get players younger than the given age
    @GetMapping("/getWithLessAge")
    public List<Player> getPlayersLessThanMaxAge(
            @RequestParam(name = "maxAge") Integer maxAge
    ) {
        return playerService.findByAgeLessThan(maxAge);
    }


    //Get players older than the given age, having less rating than the given rating
    @GetMapping("/search")
    public List<Player> searchPlayers(
            @RequestParam(name = "minAge", required = false) Integer minAge,
            @RequestParam(name = "maxRating", required = false) Double maxRating
    ) {
        if (minAge != null) {
            return this.playerService.findByAgeGreaterThan(minAge);
        } else if (maxRating != null) {
            return this.playerService.findByRatingLessThan(maxRating);
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/getWithLessExperience")
    public List<Player> getPlayersHavingLessExperienceThanTheYear(
            @RequestParam(name = "maxYearsOfExperience", required = false) Double maxYearsOfExperience
    ) {
        return playerService.findByYearsOfExperienceLessThan(maxYearsOfExperience);
    }


    @PutMapping("/update/{id}")
    public Player updatePlayer(@RequestBody PlayerDTO playerDTO, @PathVariable("id") Integer playerId) {
        return playerService.update(playerDTO, playerId);
    }



    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Integer id) {
        playerService.delete(id);

    }


    @DeleteMapping("/deleteByName")
    public void deletePlayerByName(@RequestParam String name) {
        playerService.delete(name);

    }

    //  It just deletes the first entity, but not the list
    @DeleteMapping("/deleteCustom")
    public void deleteCustom(@RequestParam(name = "rating") Double rating) {
        playerService.delete(rating);
    }
}







