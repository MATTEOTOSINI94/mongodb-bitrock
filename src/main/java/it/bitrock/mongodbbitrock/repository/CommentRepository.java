package it.bitrock.mongodbbitrock.repository;

import it.bitrock.mongodbbitrock.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,String>,CommentCustomRepository {
}
