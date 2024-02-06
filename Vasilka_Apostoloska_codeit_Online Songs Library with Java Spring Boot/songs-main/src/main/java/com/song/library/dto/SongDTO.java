package com.song.library.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.song.library.domain.Genre;

public class SongDTO implements Serializable {

    private Long id;

    private String title;

    private Integer duration;

    private LocalDate releaseDate;

    private Genre genre;

    private Set<PlaylistDTO> playlists = new HashSet<>();

    private ArtistDTO artist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SongDTO)) {
            return false;
        }

        SongDTO songDTO = (SongDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, songDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SongDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", duration=" + getDuration() +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", genre='" + getGenre() + "'" +
            ", playlists=" + getPlaylists() +
            ", artist=" + getArtist() +
            "}";
    }
}
