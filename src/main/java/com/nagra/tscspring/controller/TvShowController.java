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
}

