package com.example.study01.repository;

import com.example.study01.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByQuery(String query);
}
