package com.example.playlist_api.service;

import java.util.List;
import com.example.playlist_api.model.Playlist;
import com.example.playlist_api.model.Song;
import com.example.playlist_api.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired; // this import  is necessary for the @Autowired annotation
import org.springframework.stereotype.Service; // this import is necessary for the @Service annotation

@Service
public class PlaylistService {

    @Autowired
    private  PlaylistRepository playlistRepository;

    // fetch all playlists
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistByName(String name) {
        return playlistRepository.findByName(name);
    }

    public void addSongToPlaylist(String playlistName, Song song) {
        Playlist playlist = playlistRepository.findByName(playlistName);
        if (playlist != null) {
            playlist.getSongs().add(song);
            playlistRepository.save(playlist);
        }
    }

    // create a new playlist
    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    // update an existing playlist
    public Playlist updatePlaylist(String name, Playlist updatedPlaylist) {
        Playlist playlist = playlistRepository.findByName(name);
        if (playlist != null) {
            playlist.setName(updatedPlaylist.getName());
            playlist.setSongs(updatedPlaylist.getSongs());
            return playlistRepository.save(playlist);
        }
        return null;
    }

    // delete a playlist by name
    public void deletePlaylistByName(String name) {
        playlistRepository.deleteByName(name);
    }
}