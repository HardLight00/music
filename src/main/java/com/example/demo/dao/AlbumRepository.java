package com.example.demo.dao;

import com.example.demo.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album getFirstByTitle(String title);

    List<Album> getByTitle(String title);
}
