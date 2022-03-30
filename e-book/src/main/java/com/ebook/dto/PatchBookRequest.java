package com.ebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatchBookRequest {
    private Long userId;
    private Long languageId;
    private Long typeId;
    private Long genreId;
}
