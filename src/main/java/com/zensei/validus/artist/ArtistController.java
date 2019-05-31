package com.zensei.validus.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @GetMapping("/artists")
    private List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/artists/{id}")
    private Artist getArtist(@PathVariable("id") long id) {
        return artistService.getArtistById(id);
    }

    @DeleteMapping("/artists/{id}")
    private void deleteArtist(@PathVariable("id") long id) {
        artistService.delete(id);
    }

    @PostMapping("/artists")
    private long saveArtist(@RequestBody Artist artist) {
        artistService.saveOrUpdate(artist);
        return artist.getId();
    }
}
