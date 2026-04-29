package com.ganeshmsk.cursorbasedpagination.service;

import com.ganeshmsk.cursorbasedpagination.dto.CursorPageResponseDto;
import com.ganeshmsk.cursorbasedpagination.dto.PostResponseDto;

public interface PostService {
    CursorPageResponseDto<PostResponseDto> getPosts(String cursor, int limit);
}
