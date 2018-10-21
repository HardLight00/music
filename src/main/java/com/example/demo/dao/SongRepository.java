package com.example.demo.dao;

import com.example.demo.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Song getFirstByTitle(String title);

    List<Song> getByTitle(String Title);
}
