package com.example.study01;

import com.example.study01.model.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MovieTest {

    @DisplayName("성공- 이번 년도 개봉 영화")
    @Test
    void ThisYearMovie1() {

        Movie movie = Movie.builder()
                .title("킹메이커")
                .pubDate(2022)
                .build();

        assertThat(movie.isThisYearMovie(movie.getPubDate())).isEqualTo(true);
    }

    @DisplayName("실패 - 이번 년도 영화 아님")
    @Test
    void thisYearMovie2() {

        Movie movie = Movie.builder()
                .title("킹메이커")
                .pubDate(2020)
                .build();

        assertThat(movie.isThisYearMovie(movie.getPubDate())).isEqualTo(false);
    }
}
