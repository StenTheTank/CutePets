package com.example.backend;

import com.example.backend.model.Pet;
import com.example.backend.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringBootTest
@ActiveProfiles({"h2"})
@Tag("integration")
class PetRepositoryTest {
    @Autowired
    PetRepository petRepository;

    @Test
    void tryStuff() {
        long before = petRepository.count();
        List<Pet>pets=petRepository.findAll();
        petRepository.findAll().forEach(System.out::println);
        Assertions.assertEquals(before, petRepository.count());
    }

}