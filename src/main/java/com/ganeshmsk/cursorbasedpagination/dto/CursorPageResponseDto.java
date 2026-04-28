package com.ganeshmsk.cursorbasedpagination.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursorPageResponseDto<T> {

    private List<T> data;
    private String nextCursor;
    private boolean hasNext;

}
