package it.bitrock.mongodbbitrock.model.queryutils;


import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import it.bitrock.mongodbbitrock.model.Comment;
import it.bitrock.mongodbbitrock.service.ServiceUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class CommentQueryUtils {

    @Autowired
    private MongoDatabase mongoDatabase;

    public List<Comment> getCommentByEmail(String email) {
        return mongoDatabase.getCollection("comments", Comment.class)
                .find(new Document("email", email)).into(new ArrayList<>());
    }


    public List<Comment> findCommentByDate(String date) {
            return mongoDatabase.getCollection("comments", Comment.class)
                    .find(Filters.gte("date", ServiceUtils.transformStringToLocalDateTime(date)))
                    .limit(50).into(new ArrayList<>());
    }


}
