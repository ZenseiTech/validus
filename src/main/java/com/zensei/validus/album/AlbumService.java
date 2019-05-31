package com.zensei.validus.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AlbumService {
    @Autowired
    AlbumRepository albumRepository;

    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(album -> albums.add(album));
        return albums;
    }

    public Album getAlbumById(long id) {
        return albumRepository.findById(id).get();
    }

    public Album saveOrUpdate(Album album) {
        if(album.getId() == null) {
            album.setCreated(new Date());
        }
        album.setLastModified(new Date());
        return albumRepository.save(album);
    }

    public void delete(long id) {
        albumRepository.deleteById(id);
    }
}
