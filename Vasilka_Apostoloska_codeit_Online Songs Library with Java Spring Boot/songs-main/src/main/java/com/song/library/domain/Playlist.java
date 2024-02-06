package com.song.library.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "playlist")
public class Playlist implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
   @SequenceGenerator(name = "sequenceGenerator")
   @Column(name = "id")
   private Long id;

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "creation_date", nullable = false)
   private LocalDate creationDate;

   @Enumerated(EnumType.STRING)
   @Column(name = "status", nullable = false)
   private Status status;

   @ManyToMany(fetch = FetchType.LAZY, mappedBy = "playlists")
   @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
   @JsonIgnoreProperties(value = { "playlists", "artist" }, allowSetters = true)
   private Set<Song> songs = new HashSet<>();

   public Playlist id(Long id) {
      this.setId(id);
      return this;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Playlist name(String name) {
      this.setName(name);
      return this;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Playlist creationDate(LocalDate creationDate) {
      this.setCreationDate(creationDate);
      return this;
   }

   public LocalDate getCreationDate() {
      return creationDate;
   }

   public void setCreationDate(LocalDate creationDate) {
      this.creationDate = creationDate;
   }

   public Playlist status(Status status) {
      this.setStatus(status);
      return this;
   }

   public Status getStatus() {
      return status;
   }

   public void setStatus(Status status) {
      this.status = status;
   }

   public Set<Song> getSongs() {
      return songs;
   }

   public void setSongs(Set<Song> songs) {
      if (this.songs != null) {
         this.songs.forEach(i -> i.removePlaylist(this));
      }
      if (songs != null) {
         songs.forEach(i -> i.addPlaylist(this));
      }
      this.songs = songs;
   }

   public Playlist songs(Set<Song> songs) {
      this.setSongs(songs);
      return this;
   }

   public Playlist addSong(Song song) {
      this.songs.add(song);
      song.getPlaylists().add(this);
      return this;
   }

   public Playlist removeSong(Song song) {
      this.songs.remove(song);
      song.getPlaylists().remove(this);
      return this;
   }
}
