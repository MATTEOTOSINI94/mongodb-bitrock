package it.bitrock.mongodbbitrock.model.queryutils;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import it.bitrock.mongodbbitrock.model.Movie;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;

@Service
public class MovieQueryUtils {

    @Autowired
    private MongoDatabase mongoDatabase;

    public Movie findMovieByComment(String id){
        List<Bson> pipelineMatch = Arrays.asList(
                match(Filters.eq("_id",new ObjectId(id)))
                ,lookup("movies", "movie_id", "_id", "movies")
                ,project(new Document("movies", 1L).append("_id", 0L))
                ,unwind("$movies")
                ,replaceRoot("$movies")
        );
        return mongoDatabase.getCollection("comments",Movie.class).aggregate(pipelineMatch).first();
    }
}
