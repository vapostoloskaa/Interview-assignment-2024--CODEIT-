package com.song.library.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "song")
public class Artist implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
   @SequenceGenerator(name = "sequenceGenerator")
   @Column(name = "id")
   private Long id;

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "artistic_name", nullable = false)
   private String artisticName;

   @Column(name = "date_of_birth", nullable = false)
   private LocalDate dateOfBirth;

   @Column(name = "nationality", nullable = false)
   private String nationality;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist")
   @JsonIgnoreProperties(value = { "playlists", "artist" }, allowSetters = true)
   private Set<Song> songs = new HashSet<>();

   public Artist id(Long id) {
      this.setId(id);
      return this;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Artist name(String name) {
      this.setName(name);
      return this;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Artist artisticName(String artisticName) {
      this.setArtisticName(artisticName);
      return this;
   }

   public String getArtisticName() {
      return artisticName;
   }

   public void setArtisticName(String artisticName) {
      this.artisticName = artisticName;
   }

   public Artist dateOfBirth(LocalDate dateOfBirth) {
      this.setDateOfBirth(dateOfBirth);
      return this;
   }

   public LocalDate getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public Artist nationality(String nationality) {
      this.setNationality(nationality);
      return this;
   }

   public String getNationality() {
      return nationality;
   }

   public void setNationality(String nationality) {
      this.nationality = nationality;
   }

   public Artist songs(Set<Song> songs) {
      this.setSongs(songs);
      return this;
   }

   public Artist addSong(Song song) {
      this.songs.add(song);
      song.setArtist(this);
      return this;
   }

   public Artist removeSong(Song song) {
      this.songs.remove(song);
      song.setArtist(null);
      return this;
   }

   public Set<Song> getSongs() {
      return songs;
   }

   public void setSongs(Set<Song> songs) {
      if (this.songs != null) {
         this.songs.forEach(i -> i.setArtist(null));
      }
      if (songs != null) {
         songs.forEach(i -> i.setArtist(this));
      }
      this.songs = songs;
   }
}
