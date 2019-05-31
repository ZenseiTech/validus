package com.zensei.validus.album;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zensei.validus.artist.Artist;
import com.zensei.validus.domain.BaseModel;
import com.zensei.validus.song.Song;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Album extends BaseModel {

    @Column(name = "name")
    private String name;

    @Column(name = "year_released")
    private int yearReleased;

    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artists;

    @OneToMany(mappedBy = "album")
    @JsonManagedReference
    private Set<Song> songs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

}
