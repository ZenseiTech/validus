package com.zensei.validus.artist;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zensei.validus.album.Album;
import com.zensei.validus.domain.BaseModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artist extends BaseModel {

    @Column
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "artist_albums",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "albums_id") }
    )
    @JsonBackReference
    Set<Album> albums = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

}
