package com.ganeshmsk.cursorbasedpagination.util;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cursor {
    private LocalDateTime createdAt;
    private Long id;
}
