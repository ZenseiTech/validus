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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

        Album album = new Album();
        album.setCreated(new Date());
        album.setLastModified(new Date());
        album.setName("The Last album");
        album.setYearReleased(1982);
        Album album1 = entityManager.persistAndFlush(album);
        assertThat(album1.getName()).isEqualTo(album.getName());

        Artist artist = new Artist();
        artist.setName("Elton John");
        artist.setCreated(new Date());
        artist.setLastModified(new Date());
        Set<Album> albumList = new HashSet<>();
        albumList.add(album1);
        artist.setAlbums(albumList);
        Artist artist1 = entityManager.persistAndFlush(artist);
        assertThat(artistRepository.findById(artist1.getId()).get()).isEqualTo(artist);

        Song song = new Song();
        song.setName("Song1");
        song.setTrack(1);
        song.setCreated(new Date());
        song.setLastModified(new Date());
        song.setAlbum(album1);
        Song song1 = entityManager.persistAndFlush(song);
        assertThat(songRepository.findById(song1.getId()).get()).isEqualTo(song);

        Album album2 = albumRepository.findById(album1.getId()).get();
        assertThat(album2.getName()).isEqualTo(album1.getName());
    }
}