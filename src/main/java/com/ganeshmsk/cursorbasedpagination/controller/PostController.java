package com.ganeshmsk.cursorbasedpagination.controller;

import com.ganeshmsk.cursorbasedpagination.dto.CursorPageResponseDto;
import com.ganeshmsk.cursorbasedpagination.dto.PostResponseDto;

import com.ganeshmsk.cursorbasedpagination.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public CursorPageResponseDto<PostResponseDto> getPosts(
            @RequestParam(required=false) String cursor,
            @RequestParam(defaultValue = "10") int limit
    ){
        return postService.getPosts(cursor,limit);
    }
}
