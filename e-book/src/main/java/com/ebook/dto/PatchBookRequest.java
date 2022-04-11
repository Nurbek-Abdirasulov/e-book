package com.ebook.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatchBookRequest {
    private Long userId;
    private Long languageId;
    private Long typeId;
    private Long genreId;
}
