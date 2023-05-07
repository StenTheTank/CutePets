package com.example.backend.controller;

import com.example.backend.model.Pet;
import com.example.backend.model.Response;
import com.example.backend.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/list")
    public ResponseEntity<Response> getPets(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("pets",petService.getAllPets()))
                        .message("Pets retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> savePet(@RequestBody @Valid Pet pet){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("pet", petService.create(pet)))
                        .message("Pet saved")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getPet(@PathVariable("id")Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("pet", petService.getPet(id)))
                        .message("Pet retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deletePet(@PathVariable("id")Long id){
        return ResponseEntity.ok(
          Response.builder()
                  .timeStamp(now())
                  .data(of("deleted", petService.delete(id)))
                  .message("Pet deleted")
                  .status(OK)
                  .statusCode(OK.value())
                  .build()
        );
    }
    /*
    @GetMapping(path = "/image/{filename}", produces = IMAGE_PNG_VALUE)
    public byte[] getPetImage(@PathVariable("filename")String filename) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Desktop/wisercat/" + filename));
    }*/

    @GetMapping(path = "/image/{filename}", produces = IMAGE_PNG_VALUE)
    public byte[] getPetImage(@PathVariable("filename") String filename) throws URISyntaxException, IOException {
        URL resourceUrl = getClass().getResource("/animals/" + filename);
        Path filePath = Paths.get(resourceUrl.toURI());
        return Files.readAllBytes(filePath);
    }
}

