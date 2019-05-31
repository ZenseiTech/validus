package com.zensei.validus.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping("/albums")
    private List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/albums/{id}")
    private Album getAlbum(@PathVariable("id") long id) {
        return albumService.getAlbumById(id);
    }

    @DeleteMapping("/albums/{id}")
    private void deleteAlbum(@PathVariable("id") long id) {
        albumService.delete(id);
    }

    @PostMapping("/albums")
    private long saveAlbum(@RequestBody Album album) {
        albumService.saveOrUpdate(album);
        return album.getId();
    }
}
