package com.example.study01.repository;

import com.example.study01.model.Movie;
import com.example.study01.config.NaverProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    private final RestTemplate restTemplate;
    private final NaverProperties naverProperties;

    @Override
    public List<Movie> findByQuery(String query) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverProperties.getClientId());
        httpHeaders.add("X-Naver-Client-Secret", naverProperties.getClientSecret());

        String url = naverProperties.getMovieUrl() + "?query=" + query;

        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), ResponseMovie.class)
                .getBody().getItems()
                .stream()
                .map(m -> Movie.builder()
                        .title(m.title)
                        .link(m.link)
                        .userRating(m.userRating)
                        .image(m.image)
                        .subtitle(m.subtitle)
                        .director(m.director)
                        .pubDate(m.pubDate)
                        .build())
                .collect(Collectors.toList());

    }

    @Getter
    @NoArgsConstructor
    static class ResponseMovie implements Serializable {

        private List<Item> items;

        @Getter
        @NoArgsConstructor
        static class Item {

            private String title;
            private String link;
            private float userRating;
            private String image;
            private String subtitle;
            private String director;
            private Date pubDate;

        }
    }
}
