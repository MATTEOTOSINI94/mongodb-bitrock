package it.bitrock.mongodbbitrock.repository;

import it.bitrock.mongodbbitrock.model.Movie;

import java.util.List;

public interface MovieCustomRepository {
     List<Movie> findMoviesByCountry(List<String> country);

     List<Movie> joinMovieByEmailComment(String email);

     List<Movie> findMovieByRatingNumReviews(Double rating,Double numReviews);

}
