package it.bitrock.mongodbbitrock.repository;


import it.bitrock.mongodbbitrock.model.Comment;

public interface CommentCustomRepository {

         void findAndUpdateComment(String id, String text);
         void findAndRemoveComment(String id);
         void findAndRemoveAll(String data);
         void upsertComment(String id, String text);
         long removeCommentsByEmail(String email);

         Comment saveComment(Comment comment);
}
