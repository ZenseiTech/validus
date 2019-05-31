package com.zensei.validus.song;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zensei.validus.album.Album;
import com.zensei.validus.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Song extends BaseModel {

    @Column
    private int track;

    @Column
    private String  name;

    @ManyToOne
    @JoinColumn(name="album_id", nullable=false)
    @JsonBackReference
    private Album album;

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
