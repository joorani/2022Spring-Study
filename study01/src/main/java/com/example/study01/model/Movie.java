package com.example.study01.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Movie {

    private String title;
    private String link;
    private float userRating;
}
