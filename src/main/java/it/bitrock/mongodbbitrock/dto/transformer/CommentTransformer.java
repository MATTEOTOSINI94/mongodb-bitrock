package it.bitrock.mongodbbitrock.dto.transformer;


import it.bitrock.mongodbbitrock.dto.CommentDTO;
import it.bitrock.mongodbbitrock.model.Comment;
import org.bson.types.ObjectId;

public class CommentTransformer {

    private CommentTransformer(){

    }

    public static CommentDTO fromCommentToCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getName(),
                comment.getEmail(),
                comment.getMovieId().toHexString(),
                comment.getDate(),
                comment.getText());
    }


    public static Comment fromCommentDTOToComment(CommentDTO commentDTO){
        Comment comment = new Comment();
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setMovieId(new ObjectId(commentDTO.getMovie_id()));
        comment.setDate(commentDTO.getDate());
        comment.setText(commentDTO.getText());
        return comment;
    }
}
