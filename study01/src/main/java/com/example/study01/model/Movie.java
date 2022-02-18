package com.example.study01.model;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Movie {

    private String title;
    private String link;
    private float userRating;
    private String image;
    private String subtitle;
    private String director;
    private Date pubDate;
}
