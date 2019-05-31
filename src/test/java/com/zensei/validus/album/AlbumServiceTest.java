package com.zensei.validus.album;

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
public class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    public void getAllAlbums() {
        when(albumRepository.findAll()).thenReturn(getAlbums());
        List<Album> albums = albumService.getAllAlbums();
        assertThat(albums.size()).isEqualTo(2);
    }

    @Test
    public void getAlbumById() {
        Album album = new Album();
        album.setId(new Long(2));
        album.setName("Album 2");
        when(albumRepository.findById(any(Long.class))).thenReturn(Optional.of(album));
        Album result = albumService.getAlbumById(new Long(1));
        assertThat(result.getName()).isEqualTo(album.getName());
        assertThat(result.getId()).isEqualTo(album.getId());
    }

    @Test
    public void saveOrUpdate() {
        Album album = new Album();
        album.setId(new Long(2));
        album.setName("Album 2");
        when(albumRepository.save(any(Album.class))).thenReturn(album);
        Album result = albumService.saveOrUpdate(album);
        assertThat(result.getName()).isEqualTo(album.getName());
    }

    @Test
    public void delete() {
        albumService.delete(new Long(1));
        Album album = new Album();
        album.setId(new Long(1));
        verify(albumRepository, times(1)).deleteById(album.getId());
    }

    private List<Album> getAlbums() {
        List<Album> albums = new ArrayList<>();
        Album album1 = new Album();
        album1.setId(new Long(1));

        Album album2 = new Album();
        album2.setId(new Long(2));

        albums.add(album1);
        albums.add(album2);

        return albums;
    }
}