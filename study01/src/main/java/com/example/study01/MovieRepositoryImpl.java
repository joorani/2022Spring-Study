package com.example.study01;

import com.example.study01.config.NaverProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieRepositoryImpl implements MovieRepository {

    private final RestTemplate restTemplate;
    private final NaverProperties naverProperties;

    public MovieRepositoryImpl(RestTemplate restTemplate, NaverProperties naverProperties) {
        this.restTemplate = restTemplate;
        this.naverProperties = naverProperties;
    }


    @Override
    public List<Movie> findByQuery(String query) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverProperties.getClientId());
        httpHeaders.add("X-Naver-Client-Secret", naverProperties.getClientSecret());

        String url = naverProperties.getMovieUrl() + "?query=" + query;

        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), ResponseMovie.class)
                .getBody().getItems()
                .stream()
                .sorted((a, b) -> b.getUserRating() > a.getUserRating()? 1: -1)
                .map(m -> Movie.builder()
                        .title(m.title)
                        .link(m.link)
                        .userRating(m.userRating).build())
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
        }
    }
}
