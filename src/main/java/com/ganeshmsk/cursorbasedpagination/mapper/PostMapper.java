package com.ganeshmsk.cursorbasedpagination.mapper;

import  com.ganeshmsk.cursorbasedpagination.dto.PostResponseDto;
import  com.ganeshmsk.cursorbasedpagination.entity.Post;

public class PostMapper {

    public static PostResponseDto toDto(Post post){
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
