package com.zensei.validus.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {

    @Autowired
    SongService songService;

    @GetMapping("/songs")
    private List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/songs/{id}")
    private Song getSong(@PathVariable("id") long id) {
        return songService.getSongById(id);
    }

    @DeleteMapping("/songs/{id}")
    private void deleteSong(@PathVariable("id") long id) {
        songService.delete(id);
    }

    @PostMapping("/songs")
    private long saveSong(@RequestBody Song song) {
        Song resultSong = songService.saveOrUpdate(song);
        return resultSong.getId();
    }
}