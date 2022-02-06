package com.example.study01;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByQuery(String query);
}
