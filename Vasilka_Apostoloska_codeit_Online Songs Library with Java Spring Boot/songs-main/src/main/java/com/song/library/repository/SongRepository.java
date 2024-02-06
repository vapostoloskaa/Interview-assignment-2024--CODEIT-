package com.song.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.song.library.domain.Song;

@Repository
public interface SongRepository extends SongRepositoryWithBagRelationships, JpaRepository<Song, Long>, JpaSpecificationExecutor<Song> {
    default Optional<Song> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findOneWithToOneRelationships(id));
    }

    default List<Song> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAllWithToOneRelationships());
    }

    default Page<Song> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAllWithToOneRelationships(pageable));
    }

    @Query(value = "select song from Song song left join fetch song.artist", countQuery = "select count(song) from Song song")
    Page<Song> findAllWithToOneRelationships(Pageable pageable);

    @Query("select song from Song song left join fetch song.artist")
    List<Song> findAllWithToOneRelationships();

    @Query("select song from Song song left join fetch song.artist where song.id =:id")
    Optional<Song> findOneWithToOneRelationships(@Param("id") Long id);
}
