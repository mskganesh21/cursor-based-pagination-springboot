package com.ganeshmsk.cursorbasedpagination.repository;

import com.ganeshmsk.cursorbasedpagination.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("""
    SELECT p FROM Post p
    ORDER BY p.createdAt DESC, p.id DESC
""")
    List<Post> findFirstPage(Pageable pageable);

    @Query("""
    SELECT p FROM Post p
    WHERE (
        p.createdAt < :createdAt OR
        (p.createdAt = :createdAt AND p.id < :id)
    )
    ORDER BY p.createdAt DESC, p.id DESC
""")
    List<Post> findPostsWithCursor(
            @Param("createdAt") LocalDateTime createdAt,
            @Param("id") Long id,
            Pageable pageable
    );

}
