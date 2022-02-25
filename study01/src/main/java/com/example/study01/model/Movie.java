package com.example.study01.model;

import lombok.*;
import java.util.Calendar;

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
    private Integer pubDate;

    public boolean isThisYearMovie() {
        Calendar cal = Calendar.getInstance();
        int thisYear = cal.get(Calendar.YEAR);

        return pubDate == thisYear;
    }
}
