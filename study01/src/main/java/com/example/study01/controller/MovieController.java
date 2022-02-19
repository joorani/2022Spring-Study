package com.example.study01.controller;

import com.example.study01.dto.MovieResponseDto;
import com.example.study01.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/api/search/movies")
    public ResponseEntity<List<MovieResponseDto>> searchMovie(@RequestParam(name = "q") String title) {
        return ResponseEntity.ok().body(movieService.search(title));
    }


}
