package com.song.library.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.song.library.domain.Status;

public class PlaylistDTO implements Serializable {

    private Long id;

    private String name;

    private LocalDate creationDate;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlaylistDTO)) {
            return false;
        }

        PlaylistDTO playlistDTO = (PlaylistDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, playlistDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlaylistDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
