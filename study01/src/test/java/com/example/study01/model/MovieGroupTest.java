package com.example.study01.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MovieGroupTest {

    @DisplayName("영화 평점 내림차순으로 정렬되는지")
    @Test
    void sortByUserRating_orderByDesc() {
        float expectUserRating = 9.5f;

        List<Movie> movies = movieGroup.sortMovieList();
        assertThat(movies.get(0).getUserRating()).isEqualTo(expectUserRating);
    }

    @DisplayName("평점이 0인 경우 제외되는지")
    @Test
    void sortByUserRating_excludeZeroRating() {

        int expectListSize = 3;
        List<Movie> movies = movieGroup.sortMovieList();

        assertThat(movies.size()).isEqualTo(expectListSize);
    }

    MovieGroup movieGroup = new MovieGroup(
            Arrays.asList(
                    Movie.builder().userRating(4.5f).build(),
                    Movie.builder().userRating(9.5f).build(),
                    Movie.builder().userRating(5.5f).build(),
                    Movie.builder().userRating(0.0f).build()
            )

    );
}