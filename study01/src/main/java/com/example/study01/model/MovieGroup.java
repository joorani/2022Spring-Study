package com.example.study01.model;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MovieGroup {

    private final List<Movie> MovieGroup;

    public MovieGroup(List<Movie> MovieGroup) {
        this.MovieGroup = MovieGroup;
    }

    //평점 순 정렬
    public List<Movie> sortMovieList() {
        return MovieGroup.stream()
                .filter(m -> m.getUserRating() != 0.0f)
                .sorted((a, b) -> b.getUserRating() > a.getUserRating() ? 1: -1)
                .collect(Collectors.toList());

    }

}
