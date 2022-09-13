package it.bitrock.mongodbbitrock.repository;

import it.bitrock.mongodbbitrock.model.Movie;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieCustomRepository {
     List<Movie> findMoviesByCountry(List<String> country);

     List<Movie> joinMovieByEmailComment(String email);

     List<Movie> findMovieByRatingNumReviews(Double rating,Double numReviews);

     Page<Movie> findAllMoviesPaginate(Pageable pageable);

     List<Document> findMovieWithComments();

}
