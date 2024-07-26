package com.springboot.disney_api.service;

import com.springboot.disney_api.dto.character.CharacterDetailedResponseDTO;
import com.springboot.disney_api.dto.character.CharacterRequestDTO;
import com.springboot.disney_api.dto.character.CharacterResponseDTO;
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
        Optional<Character> characterFound = characterRepository.findById(id);
        if (characterFound.isPresent()) {
            Character character = characterFound.get();
            return new CharacterDetailedResponseDTO(
                    character.getId(),
                    character.getName(),
                    character.getImage(),
                    character.getAge(),
                    character.getWeight(),
                    character.getHistory());
        } else return null;
    }
}
