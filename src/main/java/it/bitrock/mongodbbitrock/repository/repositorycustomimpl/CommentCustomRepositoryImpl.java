package it.bitrock.mongodbbitrock.repository.repositorycustomimpl;

import it.bitrock.mongodbbitrock.model.Comment;
import it.bitrock.mongodbbitrock.repository.CommentCustomRepository;
import it.bitrock.mongodbbitrock.service.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;




    @Override
    public void findAndUpdateComment(String id, String text) {
        Query query= new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("text",text);
        mongoTemplate.findAndModify(query,update, Comment.class);
    }

    @Override
    public void findAndRemoveComment(String id) {
        mongoTemplate.findAndRemove(new Query(Criteria.where("_id").is(id)),Comment.class);
    }

    @Override
    public long removeCommentsByEmail(String email) {
       return mongoTemplate.remove(new Query(Criteria.where("email").is(email)),Comment.class).getDeletedCount();
    }

    @Override
    public Comment saveComment(Comment comment) {
        return mongoTemplate.save(comment);
    }

    @Override
    public void findAndRemoveAll(String date) {
        Query query= new Query();
        query.addCriteria(Criteria.where("data").gte(ServiceUtils.transformStringToLocalDateTime(date)));
        mongoTemplate.findAllAndRemove(query, Comment.class);
    }

    @Override
    public void upsertComment(String id, String text) {
    Query query= new Query();
    query.addCriteria(Criteria.where("id").is(510));
    Update update = new Update();
    update.set("text", text );
    mongoTemplate.upsert(query, update, Comment.class);
    }
}
