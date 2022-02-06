package com.example.study01;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                .map(m -> MovieResponseDto.builder()
                        .title(m.getTitle())
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .build())
                .collect(Collectors.toList());
    }
}
