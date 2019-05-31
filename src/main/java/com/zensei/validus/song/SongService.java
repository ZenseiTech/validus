package com.zensei.validus.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SongService {
    @Autowired
    SongRepository songRepository;

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        songRepository.findAll().forEach(song -> songs.add(song));
        return songs;
    }

    public Song getSongById(long id) {
        return songRepository.findById(id).get();
    }

    public Song saveOrUpdate(Song song) {
        if(song.getId() == null) {
            song.setCreated(new Date());
        }
        song.setLastModified(new Date());
        return songRepository.save(song);
    }

    public void delete(long id) {
        songRepository.deleteById(id);
    }
}
