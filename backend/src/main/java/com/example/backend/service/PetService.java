package com.example.backend.service;

import com.example.backend.model.enumeration.PetType;
import com.example.backend.repository.PetRepository;
import com.example.backend.model.Pet;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PetService {
    private final PetRepository petRepository;

    public Pet create(Pet pet) {
        log.info("Saving new pet: {}", pet.getName());
        pet.setImageUrl(setPetImageUrl(pet.getPetType()));
        return petRepository.save(pet);
    }
    public Pet update(Pet pet) {
        log.info("Updating pet: {}", pet.getName());
        return petRepository.save(pet);
    }
    public Collection<Pet> getAllPets() {
        log.info("Fething all pets");
        return petRepository.findAll();
    }
    public Pet getPet(Long id){
        log.info("Fetching pet with id: {}", id);
        return petRepository.findById(id).get();
    }
    public Boolean delete(Long id){
        log.info("Deleting pet with id: {}", id);
        petRepository.deleteById(id);

        return TRUE;
    }
    private String setPetImageUrl(PetType petType) {
        String image = switch (petType){
            case Cat -> "cat.png";
            case Dog -> "dog.png";
            case Horse -> "horse.png";
            case Rabbit -> "rabbit.png";
            case Parrot -> "parrot.png";
        };

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/pet/image/"+image).toUriString();
    }
}
