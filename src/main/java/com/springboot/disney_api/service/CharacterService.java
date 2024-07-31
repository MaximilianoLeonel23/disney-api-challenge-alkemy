package com.springboot.disney_api.service;

import com.springboot.disney_api.dto.character.CharacterDetailedResponseDTO;
import com.springboot.disney_api.dto.character.CharacterRequestDTO;
import com.springboot.disney_api.dto.character.CharacterResponseDTO;
import com.springboot.disney_api.dto.character.CharacterUpdateDTO;
import com.springboot.disney_api.dto.movie.MovieResponseDTO;
import com.springboot.disney_api.dto.series.SeriesResponseDTO;
import com.springboot.disney_api.model.Character;
import com.springboot.disney_api.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public CharacterResponseDTO createNewCharacter(CharacterRequestDTO body) {
        Character newCharacter = new Character(body);
        Character createdCharacter = characterRepository.save(newCharacter);
        return new CharacterResponseDTO(
                createdCharacter.getId(),
                createdCharacter.getName(),
                createdCharacter.getImage(),
                createdCharacter.getAge(),
                createdCharacter.getWeight(),
                createdCharacter.getHistory()
        );
    }

    public List<CharacterResponseDTO> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        return characters.stream().map(c -> new CharacterResponseDTO(
                c.getId(),
                c.getName(),
                c.getImage(),
                c.getAge(),
                c.getWeight(),
                c.getHistory()
        )).collect(Collectors.toList());
    }

    public CharacterDetailedResponseDTO getCharacterById(Long id) {
        Character character = characterRepository.findById(id).orElseThrow(() -> new RuntimeException("Character not found"));

        List<MovieResponseDTO> movies = character.getMovies().stream().map(m -> new MovieResponseDTO(
                m.getId(),
                m.getTitle(),
                m.getImage(),
                m.getCreationDate(),
                m.getRating()
        )).toList();
        List<SeriesResponseDTO> series = character.getSeries().stream().map(s -> new SeriesResponseDTO(
                s.getId(),
                s.getTitle(),
                s.getImage(),
                s.getCreationDate(),
                s.getRating(),
                s.getSeasons(),
                s.getEpisodes()
        )).toList();
       return new CharacterDetailedResponseDTO(
               character.getId(),
               character.getName(),
               character.getImage(),
               character.getAge(), 
               character.getWeight(),
               character.getHistory(),
               movies,
               series
       );

    }

    public CharacterResponseDTO updateCharacter(Long id, CharacterUpdateDTO characterUpdateDTO) {
        Optional<Character> characterFound = characterRepository.findById(id);
        if (characterFound.isPresent()) {
            Character character = characterFound.get();
            character.updateCharacter(characterUpdateDTO);
            Character updatedCharacter = characterRepository.save(character);
            return new CharacterResponseDTO(
                    character.getId(),
                    character.getName(),
                    character.getImage(),
                    character.getAge(),
                    character.getWeight(),
                    character.getHistory());

        } else return null;
    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }
}
