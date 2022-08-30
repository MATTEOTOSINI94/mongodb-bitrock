package it.bitrock.mongodbbitrock.repository.repositorycustomimpl;

import it.bitrock.mongodbbitrock.model.Movie;
import it.bitrock.mongodbbitrock.repository.MovieCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Movie> findMoviesByCountry(List<String> country){
        return mongoTemplate.find(new Query(Criteria.where("cast").in(country)).limit(5),Movie.class);
    }

    @Override
    public List<Movie> joinMovieByEmailComment(String email) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("email").is(email));
        LimitOperation limitOperation = Aggregation.limit(10);
        ProjectionOperation projectionOperation = Aggregation.project("movie_id").andExclude("_id");
        LookupOperation lookupOperation = Aggregation.lookup("movies", "movie_id", "_id", "movies");
        UnwindOperation unwindOperation = Aggregation.unwind("movies");
        ReplaceRootOperation replaceRootOperation = Aggregation.replaceRoot("movies");
        Aggregation aggregation = Aggregation.newAggregation(
                matchOperation,
                limitOperation,
                projectionOperation,
                lookupOperation,
                unwindOperation,
                replaceRootOperation
        );
        return mongoTemplate.aggregate(aggregation, "comments", Movie.class).getMappedResults();
    }

    @Override
    public List<Movie> findMovieByRatingNumReviews(Double rating, Double numReviews) {
        Query query = new Query();
        if (rating != null) {
            query.addCriteria(Criteria.where("tomatoes.viewer.rating")
                    .gte(rating));
        }
        if (numReviews != null) {
            query.addCriteria(Criteria.where("tomatoes.viewer.numReviews")
                    .gte(rating));
        }
        return mongoTemplate
                .find(query.limit(5), Movie.class);
    }


}
