package com.zensei.validus.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArtistService {
    @Autowired
    ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        artistRepository.findAll().forEach(artist -> artists.add(artist));
        return artists;
    }

    public Artist getArtistById(long id) {
        return artistRepository.findById(id).get();
    }

    public Artist saveOrUpdate(Artist artist) {
        if(artist.getId() == null) {
            artist.setCreated(new Date());
        }
        artist.setLastModified(new Date());
        return artistRepository.save(artist);
    }

    public void delete(long id) {
        artistRepository.deleteById(id);
    }
}
