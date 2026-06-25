package com.example.playlist_api.controller;

import com.example.playlist_api.model.Playlist;
import com.example.playlist_api.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.playlist_api.model.Song;
import com.example.playlist_api.model.Playlist;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/playlists")
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        return playlistService.createPlaylist(playlist);
    }

    @GetMapping("/playlists/{name}")
    public Playlist getPlaylistByName(@PathVariable String name) {
        return playlistService.getPlaylistByName(name);
    }

    @GetMapping("/playlists")
    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @PutMapping("/playlists/{name}")
    public Playlist updatePlaylist(@PathVariable String name, @RequestBody Playlist playlist) {
        return playlistService.updatePlaylist(name, playlist);
    }

    @DeleteMapping("/playlists/{name}")
    public void deletePlaylist(@PathVariable String name) {
        playlistService.deletePlaylist(name);
    }

}