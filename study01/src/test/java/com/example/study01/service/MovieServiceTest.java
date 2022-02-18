package com.example.study01.service;

import com.example.study01.dto.MovieResponseDto;
import com.example.study01.model.Movie;
import com.example.study01.repository.MovieRepository;
import com.example.study01.repository.MovieRepositoryImpl;
import com.example.study01.service.MovieService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepositoryImpl movieRepository;

    @DisplayName("평점 높은 순으로 정렬되는지")
    @Test
    void rate_desc() {

        //given
        float expectedRatings = 9.7f;
        when(movieRepository.findByQuery("해리포터")).thenReturn(getStubMovieList());
        MovieService movieService = new MovieService(movieRepository);

        //when
        List<MovieResponseDto> movieResponseDtos = movieService.search("해리포터");

        //then
        assertThat(movieResponseDtos.get(0).getUserRating()).isEqualTo(expectedRatings);
    }

    @DisplayName("평점 0인 경우 제외되는지")
    @Test
    void rate_zero() {

        //given
        int expectedSize = 3;
        when(movieRepository.findByQuery("해리포터")).thenReturn(getStubMovieList());
        MovieService movieService = new MovieService(movieRepository);

        //when
        List<MovieResponseDto> movieResponseDtos = movieService.search("해리포터");

        //then
        assertThat(movieResponseDtos.size()).isEqualTo(expectedSize);
    }

    @DisplayName("영화 검색 결과 없을 때 에러 발생하는지 여부")
    @Test
    void name() throws Exception {

        //given
        Mockito.when(movieRepository.findByQuery(any())).thenReturn(new ArrayList<>());

        //when, then
        assertThrows(RuntimeException.class, () -> movieService.search(any()), "검색 결과가 없습니다.");
    }

    private List<Movie> getStubMovieList() {

        return Arrays.asList(
                Movie.builder().title("해리포터 1").userRating(9.3f).build(),
                Movie.builder().title("해리포터 2").userRating(9.7f).build(),
                Movie.builder().title("해리포터 3").userRating(0.0f).build(),
                Movie.builder().title("해리포터 4").userRating(7.5f).build()
        );
    }
}