package com.example.study01;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/api/search/movies")
    public ResponseEntity<List<MovieResponseDto>> searchMovie(@RequestParam(name = "q") String title) {
        return ResponseEntity.ok().body(movieService.search(title));
    }
}
