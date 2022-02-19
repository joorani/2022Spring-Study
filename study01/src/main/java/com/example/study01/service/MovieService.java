package com.example.study01.service;

import com.example.study01.model.Movie;
import com.example.study01.dto.MovieResponseDto;
import com.example.study01.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieResponseDto> search(final String title) {

        List<Movie> movieList = movieRepository.findByQuery(title);

        //예외처리 test 위해 의도적으로 에러발생시키기
        if(movieList.isEmpty()) {
            throw new RuntimeException("검색 결과가 없습니다.");
        }

        return movieList.stream()
                .filter(m -> m.getUserRating() != 0.0f)
                .sorted((a, b) -> b.getUserRating() > a.getUserRating()? 1: -1)
                .map(m -> MovieResponseDto.builder() //modelMapper 사용
                        .title(m.getTitle())
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .image(m.getImage())
                        .subtitle(m.getSubtitle())
                        .director(m.getDirector())
                        .latestMovie(m.isThisYearMovie(m.getPubDate()))
                        .build())
                .collect(Collectors.toList());
    }


}
