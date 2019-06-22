package com.zensei.validus.album;

import com.zensei.validus.artist.Artist;
import com.zensei.validus.artist.ArtistRepository;
import com.zensei.validus.song.Song;
import com.zensei.validus.song.SongRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IntegrationRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    SongRepository songRepository;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    AlbumRepository albumRepository;
    
    @Test
    public void it_should_save_album() {

        Album album = this.getAlbum("The Last album", 1982);
        Album album1 = entityManager.persistAndFlush(album);
        assertThat(album1.getName()).isEqualTo(album.getName());

        Artist artist = getArtist("Elton John", album1);
        Artist artist1 = entityManager.persistAndFlush(artist);
        assertThat(artistRepository.findById(artist1.getId()).get()).isEqualTo(artist);

        Song song = getSong("Song1", album1);
        Song song1 = entityManager.persistAndFlush(song);
        assertThat(songRepository.findById(song1.getId()).get()).isEqualTo(song);

        Set<Artist> artists = new HashSet<>();
        artists.add(artist1);
        album1.setArtists(artists);
        Set<Song> songs = new HashSet<>();
        songs.add(song);
        album1.setSongs(songs);
        entityManager.persistAndFlush(album1);

        Album album2 = albumRepository.findById(album1.getId()).get();
        assertThat(album2.getName()).isEqualTo(album1.getName());
        assertThat(album2.getArtists().size()).isEqualTo(1);
        assertThat(album2.getSongs().size()).isEqualTo(1);

        albumRepository.delete(album2);
        Optional<Album> album3 = albumRepository.findById(album2.getId());
        assertThat(album3.isPresent()).isFalse();
    }

    private Album getAlbum(String name, int year) {
        Album album = new Album();
        album.setCreated(new Date());
        album.setLastModified(new Date());
        album.setName(name);
        album.setYearReleased(year);
        return album;
    }

    private Artist getArtist(String name, Album album) {
        Artist artist = new Artist();
        artist.setName(name);
        artist.setCreated(new Date());
        artist.setLastModified(new Date());
        Set<Album> albumList = new HashSet<>();
        albumList.add(album);
        artist.setAlbums(albumList);
        return artist;
    }

    private Song getSong(String name, Album album) {
        Song song = new Song();
        song.setName(name);
        song.setTrack(1);
        song.setCreated(new Date());
        song.setLastModified(new Date());
        song.setAlbum(album);
        return song;
    }
}