package com.song.library.dto;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.BeanMapping;
import org.mapstruct.ReportingPolicy;

import com.song.library.domain.Artist;
import com.song.library.domain.Playlist;
import com.song.library.domain.Song;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ArtistMapper.class})
public interface SongMapper {

    @Mapping(target = "playlists", source = "playlists", qualifiedByName = "playlistNameSet")
    @Mapping(target = "artist", source = "artist", qualifiedByName = "artistName")
    SongDTO toDto(Song song);

    @Mapping(target = "removePlaylist", ignore = true)
    Song toEntity(SongDTO songDTO);

    @Named("playlistName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    default PlaylistDTO toDtoPlaylistName(Playlist playlist) {
        if (playlist == null) {
            return null;
        }
        PlaylistDTO dto = new PlaylistDTO();
        dto.setId(playlist.getId());
        dto.setName(playlist.getName());
        return dto;
    }

    @Named("playlistNameSet")
    default Set<PlaylistDTO> toDtoPlaylistNameSet(Set<Playlist> playlists) {
        if (playlists == null) {
            return null;
        }
        return playlists.stream().map(this::toDtoPlaylistName).collect(Collectors.toSet());
    }

    @Named("artistName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    default ArtistDTO toDtoArtistName(Artist artist) {
        if (artist == null) {
            return null;
        }
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setName(artist.getName());
        return dto;
    }
}
