package com.mlb.mlb_api.service;

import com.mlb.mlb_api.controllers.dto.PlayerDTO;
import com.mlb.mlb_api.repositories.PlayerRepository;
import com.mlb.mlb_api.service.entities.Player;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player save(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO);
        return playerRepository.save(player);
    }


    @Override
    public Player update(PlayerDTO playerDTO, Integer playerId) {
        Player playerToUpdate = findById(playerId);

        playerToUpdate.setName(playerDTO.getName() ==null || playerDTO.getName().isEmpty() ?  playerToUpdate.getName() :playerDTO.getName());
        playerToUpdate.setAge(playerDTO.getAge() != null ? playerDTO.getAge() : playerToUpdate.getAge());
        playerToUpdate.setRating(playerDTO.getRating() != null ? playerDTO.getRating() : playerToUpdate.getRating());
        playerToUpdate.setYearsOfExperience(playerDTO.getYearsOfExperience() != null ? playerDTO.getYearsOfExperience() : playerToUpdate.getYearsOfExperience());

        return this.playerRepository.save(playerToUpdate);    }

    @Override
    public void delete(Integer playerId) {
        Optional<Player> optionalPlayer = this.playerRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            Player playerToDelete = optionalPlayer.get();
            this.playerRepository.delete(playerToDelete);
            throw new ResponseStatusException(HttpStatus.OK, "The player with name: " + playerToDelete.getName() + " and with id: " + playerId + " was deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player found with id: " + playerId);


    }

    @Override
    public void delete(String name) {
        Optional<Player> optionalPlayer = Optional.ofNullable(this.playerRepository.findByName(name));
        if (optionalPlayer.isPresent()) {
            Player playerToDelete = optionalPlayer.get();
            this.playerRepository.delete(playerToDelete);
            throw new ResponseStatusException(HttpStatus.OK, "The player with name: " + playerToDelete.getName() + " and with id: " + playerToDelete.getId() + " was deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player found with name: " + name);
    }

    @Override
    public void delete(Double rating) {
        if (rating != null) {
            List<Player> playersToDelete = this.playerRepository.findByRatingGreaterThan(rating);
            if (!playersToDelete.isEmpty()) {
                playerRepository.deleteAll(playersToDelete);
             }
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player found with rating: " + rating);


    }

    @Override
    public Iterable<Player> findAll(Sort playerName) {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(Integer playerId) {
        Optional<Player> optionalPlayer = this.playerRepository.findById(playerId);
        if (optionalPlayer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player found with id " + playerId);
        }

        return optionalPlayer.get();

    }

    @Override
    public Player findByName(String name) {
        return playerRepository.findByName(name);
    }

    @Override
    public Player findByNameAndAge(String name, Integer age) {
        return playerRepository.findByNameAndAge(name, age);
    }

    @Override
    public List<Player> findByAgeLessThan(Integer age) {
            return this.playerRepository.findByAgeLessThan(age);
    }

    @Override
    public List<Player> findByRatingLessThan(Double rating) {
            return this.playerRepository.findByRatingLessThan(rating);

        }

    @Override
    public List<Player> findByAgeGreaterThan(Integer age) {
        return this.playerRepository.findByAgeGreaterThan(age);
    }

    @Override
    public List<Player> findByYearsOfExperienceLessThan(Double yearsOfExperience) {
        if (yearsOfExperience != null) {
            return this.playerRepository.findByYearsOfExperienceLessThan(yearsOfExperience);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Player findByRating(Double rating) {
        return null;
    }


}
