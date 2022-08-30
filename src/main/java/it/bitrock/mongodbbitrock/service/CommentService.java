package it.bitrock.mongodbbitrock.service;

import it.bitrock.mongodbbitrock.dto.CommentDTO;
import it.bitrock.mongodbbitrock.dto.transformer.CommentTransformer;
import it.bitrock.mongodbbitrock.model.Comment;
import it.bitrock.mongodbbitrock.model.queryutils.CommentQueryUtils;
import it.bitrock.mongodbbitrock.repository.CommentRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentQueryUtils commentQueryUtils;

    public ResponseEntity<String> removeCommentById(String email){
        if(email!=null) {
            Long countDeleted = commentRepository.removeCommentsByEmail(email);
            return ResponseEntity.ok().body("hai cancellato "+ countDeleted +" commenti");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }


    public ResponseEntity<CommentDTO> saveComment(CommentDTO commentDTO){
        if(commentDTO!=null){
            Comment comment = commentRepository.saveComment(CommentTransformer.fromCommentDTOToComment(commentDTO));
            return ResponseEntity.ok().body(CommentTransformer.fromCommentToCommentDTO(comment));
        }return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    public ResponseEntity<List<CommentDTO>> getCommentByEmail(String email){
        if(email!=null){
            List<Comment> listComments = commentQueryUtils.getCommentByEmail(email);
            if(!ObjectUtils.isEmpty(listComments)){
                return ResponseEntity.ok().body(listComments.stream().map(CommentTransformer::fromCommentToCommentDTO).toList());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
