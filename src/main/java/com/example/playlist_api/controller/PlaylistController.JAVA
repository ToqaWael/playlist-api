package com.example.playlist_api.controller;

import com.example.playlist_api.model.Playlist;
import com.example.playlist_api.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.playlist_api.model.Song;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public Playlist createPlaylist(@RequestBody Playlist playlist) {
        return playlistService.createPlaylist(playlist);
    }

    @PostMapping("/{playlistName}/songs")
    public void addSongToPlaylist(@PathVariable String playlistName, @RequestBody Song song) {
        playlistService.addSongToPlaylist(playlistName, song);
    }

    @GetMapping("/{name}")
    public Playlist getPlaylistByName(@PathVariable Long Id) {
        return playlistService.getPlaylistById(Id);
    }

    @GetMapping("/{Id}")
    public Playlist getPlaylistById(@PathVariable Long Id) {
        return playlistService.getPlaylistById(Id);
    }

    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @PutMapping("/{Id}")
    public Playlist updatePlaylist(@PathVariable Long Id, @RequestBody Playlist updatedPlaylist) {
        return playlistService.updatePlaylist(Id, updatedPlaylist);
    }

    @DeleteMapping("/{Id}")
    public void deletePlaylistById(@PathVariable Long Id) {
        playlistService.deletePlaylistById(Id);
    }

}