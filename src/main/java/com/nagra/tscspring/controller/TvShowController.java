package com.nagra.tscspring.controller;


import com.nagra.tscspring.entity.Character;
import com.nagra.tscspring.entity.TvShow;
import com.nagra.tscspring.repository.CharacterRepository;
import com.nagra.tscspring.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shows")
public class TvShowController {

    @Autowired
    private TvShowRepository tvShowRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    public List<TvShow> getAllTvShows() {
        return tvShowRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable(value = "id") Long tvShowId) {
        Optional<TvShow> optionalTvShow = tvShowRepository.findById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow tvShow = optionalTvShow.get();
            return ResponseEntity.ok().body(tvShow);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/characters")
    public ResponseEntity<List<Character>> getCharactersByTvShowId(@PathVariable(value = "id") Long tvShowId) {
        List<Character> characters = characterRepository.findByTvShowId(tvShowId);
        if (characters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(characters);
    }


    @PostMapping
    public ResponseEntity<TvShow> addTvShow(@RequestBody TvShow tvShow) {
        TvShow savedTvShow = tvShowRepository.save(tvShow);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTvShow);
    }

    @PostMapping("/{tvShowId}/characters")
    public ResponseEntity<Character> addCharacterToTvShow(
            @PathVariable(value = "tvShowId") Long tvShowId,
            @RequestBody Character character) {
        Optional<TvShow> optionalTvShow = tvShowRepository.findById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow tvShow = optionalTvShow.get();
            character.setTvShow(tvShow); // Set the TV show for the character
            Character savedCharacter = characterRepository.save(character);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}


