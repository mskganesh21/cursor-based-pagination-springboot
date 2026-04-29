package com.ganeshmsk.cursorbasedpagination.service;

import com.ganeshmsk.cursorbasedpagination.dto.*;
import com.ganeshmsk.cursorbasedpagination.entity.Post;
import com.ganeshmsk.cursorbasedpagination.mapper.PostMapper;
import com.ganeshmsk.cursorbasedpagination.repository.PostRepository;
import com.ganeshmsk.cursorbasedpagination.util.Cursor;
import com.ganeshmsk.cursorbasedpagination.util.CursorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public CursorPageResponseDto<PostResponseDto> getPosts(String cursorStr, int limit) {

        Cursor cursor = null;

        // ✅ Decode cursor if present
        if (cursorStr != null && !cursorStr.isEmpty()) {
            cursor = CursorUtil.decode(cursorStr);
        }

        List<Post> posts;

        // ✅ Decide which query to run
        if (cursor == null) {
            posts = postRepository.findFirstPage(PageRequest.of(0, limit + 1));
        } else {
            posts = postRepository.findPostsWithCursor(
                    cursor.getCreatedAt(),
                    cursor.getId(),
                    PageRequest.of(0, limit + 1)
            );
        }

        // ✅ Check if next page exists
        boolean hasNext = posts.size() > limit;

        if (hasNext) {
            posts = posts.subList(0, limit);
        }

        // ✅ Generate next cursor
        String nextCursor = null;

        if (!posts.isEmpty()) {
            Post last = posts.get(posts.size() - 1);
            Cursor next = new Cursor(last.getCreatedAt(), last.getId());
            nextCursor = CursorUtil.encode(next);
        }

        // ✅ Convert to DTO
        List<PostResponseDto> data = posts.stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toList());

        return CursorPageResponseDto.<PostResponseDto>builder()
                .data(data)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}
