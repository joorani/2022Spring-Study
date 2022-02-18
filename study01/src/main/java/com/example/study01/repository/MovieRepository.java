package com.example.study01.repository;

import com.example.study01.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    List<Movie> findByQuery(String query);
}
