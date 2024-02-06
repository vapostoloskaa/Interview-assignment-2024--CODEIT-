package com.song.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.song.library.domain.Song;

public interface SongRepositoryWithBagRelationships {
    Optional<Song> fetchBagRelationships(Optional<Song> song);

    List<Song> fetchBagRelationships(List<Song> songs);

    Page<Song> fetchBagRelationships(Page<Song> songs);
}
