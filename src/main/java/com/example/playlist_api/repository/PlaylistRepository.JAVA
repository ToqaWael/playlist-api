package com.example.playlist_api.repository;

import com.example.playlist_api.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    
     // Custom query method to find a playlist by its name as user isnt expected to know the id of the playlist
     
}