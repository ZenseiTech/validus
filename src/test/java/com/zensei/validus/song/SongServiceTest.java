package com.zensei.validus.song;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongService songService;

    @Test
    public void getAllSongs() {
        when(songRepository.findAll()).thenReturn(getSongs());
        List<Song> songs = songService.getAllSongs();
        assertThat(songs.size()).isEqualTo(2);
    }

    @Test
    public void getSongById() {
        Song song = new Song();
        song.setId(new Long(2));
        song.setName("Song 2");
        when(songRepository.findById(any(Long.class))).thenReturn(Optional.of(song));
        Song result = songService.getSongById(new Long(1));
        assertThat(result.getName()).isEqualTo(song.getName());
        assertThat(result.getId()).isEqualTo(song.getId());
    }

    @Test
    public void saveOrUpdate() {
        Song song = new Song();
        song.setId(new Long(2));
        song.setName("Song 2");
        when(songRepository.save(any(Song.class))).thenReturn(song);
        Song result = songService.saveOrUpdate(song);
        assertThat(result.getName()).isEqualTo(song.getName());
    }

    @Test
    public void delete() {
        songService.delete(new Long(1));
        Song song = new Song();
        song.setId(new Long(1));
        verify(songRepository, times(1)).deleteById(song.getId());
    }

    private List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        Song song1 = new Song();
        song1.setId(new Long(1));

        Song song2 = new Song();
        song2.setId(new Long(2));

        songs.add(song1);
        songs.add(song2);

        return songs;
    }
}