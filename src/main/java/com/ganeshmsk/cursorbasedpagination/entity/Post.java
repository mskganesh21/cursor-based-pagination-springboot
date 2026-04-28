package com.ganeshmsk.cursorbasedpagination.entity;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="posts", indexes={
        @Index(name="idx_created_at_id",columnList = "created_at,id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;
}
