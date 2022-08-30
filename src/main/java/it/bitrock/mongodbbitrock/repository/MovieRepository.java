package it.bitrock.mongodbbitrock.repository;

import it.bitrock.mongodbbitrock.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends MongoRepository<Movie, String>,MovieCustomRepository{
    Movie findMovieById(String id);
    Movie findMovieByTitleIgnoreCase(String title);


}
