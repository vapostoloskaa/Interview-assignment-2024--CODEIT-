package com.song.library.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.song.library.domain.Playlist;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaylistMapper extends EntityMapper<PlaylistDTO, Playlist> {

   default void partialUpdate(@MappingTarget Playlist entity, PlaylistDTO dto) {
      if (dto == null) {
         return;
      }
      if (dto.getName() != null) {
         entity.setName(dto.getName());
      }
      if (dto.getCreationDate() != null) {
         entity.setCreationDate(dto.getCreationDate());
      }
      if (dto.getStatus() != null) {
         entity.setStatus(dto.getStatus());
      }
      // Note: Handling of the 'songs' collection or 'removeSong' method is context-specific
      // and would need custom logic if they are to be updated based on DTO changes.
   }
}
