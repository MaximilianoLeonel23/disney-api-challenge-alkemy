package com.springboot.disney_api.controller;

import com.springboot.disney_api.dto.character.CharacterDetailedResponseDTO;
import com.springboot.disney_api.dto.character.CharacterRequestDTO;
import com.springboot.disney_api.dto.character.CharacterResponseDTO;
import com.springboot.disney_api.dto.character.CharacterUpdateDTO;
import com.springboot.disney_api.service.CharacterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    public ResponseEntity<CharacterResponseDTO> createNewCharacter(@RequestBody @Valid CharacterRequestDTO body) {
        CharacterResponseDTO newCharacter = characterService.createNewCharacter(body);
        URI uri = UriComponentsBuilder.fromPath("/api/characters/{id}").buildAndExpand(newCharacter.id()).toUri();
        return ResponseEntity.created(uri).body(newCharacter);
    }

    @GetMapping()
    public ResponseEntity<List<CharacterResponseDTO>> getAllCharacters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) List<Long> movies,
            @RequestParam(required = false) List<Long> series
    ) {
        List<CharacterResponseDTO> characters = characterService.getAllCharacters(
                name, age, weight, movies, series
        );
        if (characters.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(characters);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDetailedResponseDTO> getCharacterById(@PathVariable Long id) {
        CharacterDetailedResponseDTO character = characterService.getCharacterById(id);
        return ResponseEntity.ok(character);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterResponseDTO> updateCharacter(@PathVariable Long id, @RequestBody @Valid CharacterUpdateDTO characterUpdated) {
        CharacterResponseDTO character = characterService.updateCharacter(id, characterUpdated);
        return ResponseEntity.ok(character);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }
}
