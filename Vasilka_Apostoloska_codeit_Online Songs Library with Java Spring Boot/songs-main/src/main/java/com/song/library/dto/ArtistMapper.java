package com.song.library.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.song.library.domain.Artist;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArtistMapper extends EntityMapper<ArtistDTO, Artist> {

   @Mapping(target = "songs", ignore = true)
   Artist toEntity(ArtistDTO artistDTO);
}
