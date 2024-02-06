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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "song")
public class Song implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
   @SequenceGenerator(name = "sequenceGenerator")
   @Column(name = "id")
   private Long id;

   @Column(name = "title", nullable = false)
   private String title;

   @Column(name = "duration", nullable = false)
   private Integer duration;

   @Column(name = "release_date", nullable = false)
   private LocalDate releaseDate;

   @Enumerated(EnumType.STRING)
   @Column(name = "genre", nullable = false)
   private Genre genre;

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(
         name = "rel_song__playlist",
         joinColumns = @JoinColumn(name = "song_id"),
         inverseJoinColumns = @JoinColumn(name = "playlist_id")
   )
   @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
   @JsonIgnoreProperties(value = { "songs" }, allowSetters = true)
   private Set<Playlist> playlists = new HashSet<>();

   @ManyToOne(optional = false)
   @JsonIgnoreProperties(value = { "songs" }, allowSetters = true)
   private Artist artist;

   public Song id(Long id) {
      this.setId(id);
      return this;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Song title(String title) {
      this.setTitle(title);
      return this;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Song duration(Integer duration) {
      this.setDuration(duration);
      return this;
   }

   public Integer getDuration() {
      return duration;
   }

   public void setDuration(Integer duration) {
      this.duration = duration;
   }

   public Song releaseDate(LocalDate releaseDate) {
      this.setReleaseDate(releaseDate);
      return this;
   }

   public LocalDate getReleaseDate() {
      return releaseDate;
   }

   public void setReleaseDate(LocalDate releaseDate) {
      this.releaseDate = releaseDate;
   }

   public Song genre(Genre genre) {
      this.setGenre(genre);
      return this;
   }

   public Genre getGenre() {
      return genre;
   }

   public void setGenre(Genre genre) {
      this.genre = genre;
   }

   public Song playlists(Set<Playlist> playlists) {
      this.setPlaylists(playlists);
      return this;
   }

   public Set<Playlist> getPlaylists() {
      return playlists;
   }

   public Song addPlaylist(Playlist playlist) {
      this.playlists.add(playlist);
      return this;
   }

   public Song removePlaylist(Playlist playlist) {
      this.playlists.remove(playlist);
      return this;
   }

   public void setPlaylists(Set<Playlist> playlists) {
      this.playlists = playlists;
   }

   public Song artist(Artist artist) {
      this.setArtist(artist);
      return this;
   }

   public Artist getArtist() {
      return artist;
   }

   public void setArtist(Artist artist) {
      this.artist = artist;
   }
}
