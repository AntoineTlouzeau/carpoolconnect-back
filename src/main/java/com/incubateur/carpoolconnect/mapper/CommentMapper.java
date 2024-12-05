package com.incubateur.carpoolconnect.mapper;

import com.incubateur.carpoolconnect.dto.CommentDto;
import com.incubateur.carpoolconnect.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment commentDtoToComment(CommentDto commentDto);

    CommentDto commentToCommentDto(Comment comment);
}
