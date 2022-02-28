package com.example.study01.service;

import com.example.study01.exception.ErrorCode;
import com.example.study01.exception.SearchNotFoundException;
import com.example.study01.model.Movie;
import com.example.study01.dto.MovieResponseDto;
import com.example.study01.model.MovieGroup;
import com.example.study01.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieResponseDto> search(final String title) {

        List<Movie> movieList = movieRepository.findByQuery(title);

        //일급컬렉션 적용해보기
        MovieGroup movieGroup = new MovieGroup(movieList);
        List<Movie> movieGroupList = movieGroup.sortMovieList();

        //예외처리 test 위해 의도적으로 에러발생시키기
        if(movieGroupList.isEmpty()) {
            throw new SearchNotFoundException(ErrorCode.NOT_FOUND_MOVIES);
        }

        return movieGroupList.stream()
                .map(m -> MovieResponseDto.builder()
                        .title(m.getTitle())
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .image(m.getImage())
                        .subtitle(m.getSubtitle())
                        .director(m.getDirector())
                        .latestMovie(m.isThisYearMovie())
                        .build())
                .collect(Collectors.toList());
    }


}
