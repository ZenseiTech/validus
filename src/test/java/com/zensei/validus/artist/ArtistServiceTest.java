package com.zensei.validus.artist;

import com.zensei.validus.song.Song;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistService artistService;

    @Test
    public void getAllArtists() {
        when(artistRepository.findAll()).thenReturn(geArtits());
        List<Artist> artists = artistService.getAllArtists();
        assertThat(artists.size()).isEqualTo(2);
    }

    private List<Artist> geArtits() {
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist());
        artists.add(new Artist());
        return artists;
    }

    @Test
    public void getArtistById() {
        Artist artist = new Artist();
        artist.setId(new Long(2));
        when(artistRepository.findById(any(Long.class))).thenReturn(Optional.of(artist));
        Artist result = artistService.getArtistById(new Long(1));
        assertThat(result.getId()).isEqualTo(artist.getId());
    }

    @Test
    public void saveOrUpdate() {
        Artist artist = new Artist();
        artist.setId(new Long(2));
        when(artistRepository.save(any(Artist.class))).thenReturn(artist);
        Artist result = artistService.saveOrUpdate(artist);
        assertThat(result.getId()).isEqualTo(artist.getId());
    }

    @Test
    public void delete() {
        artistService.delete(new Long(1));
        Artist artist = new Artist();
        artist.setId(new Long(1));
        verify(artistRepository, times(1)).deleteById(artist.getId());
    }
}