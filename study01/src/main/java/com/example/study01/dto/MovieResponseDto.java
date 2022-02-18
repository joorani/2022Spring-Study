package com.example.study01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieResponseDto {

    private String title;
    private String link;
    private float userRating;
    private String image;
    private String subtitle;
    private String director;
    private Date pubDate;

}
