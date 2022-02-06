package com.example.study01.service;

import com.example.study01.model.Movie;
import com.example.study01.dto.MovieResponseDto;
import com.example.study01.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponseDto> search(final String title) {
        List<Movie> movieList = movieRepository.findByQuery(title);

        return movieList.stream()
                .filter(m -> m.getUserRating() != 0.0f)
                .sorted((a, b) -> b.getUserRating() > a.getUserRating()? 1: -1)
                .map(m -> MovieResponseDto.builder()
                        .title(m.getTitle())
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .build())
                .collect(Collectors.toList());
    }
}
