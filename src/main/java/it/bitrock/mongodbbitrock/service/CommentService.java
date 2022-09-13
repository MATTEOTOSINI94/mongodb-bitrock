package it.bitrock.mongodbbitrock.service;

import io.vavr.control.Option;
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
import java.util.function.Predicate;

@Service
public class CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentQueryUtils commentQueryUtils;

    Predicate<CommentDTO> commentDTOPredicate = commentDTO -> commentDTO != null
            && commentDTO.getName() != null
            && commentDTO.getEmail() != null
            && commentDTO.getMovie_id() != null
            && commentDTO.getDate()!=null;

    public ResponseEntity<String> removeCommentByEmail(String email) {
        return Option.of(email)
                .map(emailNotNull -> ResponseEntity
                        .ok().body("Hai cancellato " + commentRepository.removeCommentsByEmail(email) + " commenti"))
                .getOrElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }


    public ResponseEntity<CommentDTO> saveComment(CommentDTO commentDTO) {
        return commentDTOPredicate.test(commentDTO) ? ResponseEntity.ok()
                .body(CommentTransformer.fromCommentToCommentDTO( commentRepository.saveComment(CommentTransformer.fromCommentDTOToComment(commentDTO))))
              : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    public ResponseEntity<List<CommentDTO>> findCommentByEmail(String email) {
        return (ResponseEntity<List<CommentDTO>>) Option.of(email)
                .map(emailNotNull -> commentQueryUtils.getCommentByEmail(emailNotNull))
                .map(listComments ->
                        !ObjectUtils.isEmpty(listComments) ? ResponseEntity.ok().body(listComments.stream().map(CommentTransformer::fromCommentToCommentDTO).toList())
                                : ResponseEntity.status(HttpStatus.NOT_FOUND).build()).getOrElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

      public ResponseEntity<List<CommentDTO>> findCommentsByDate(String date) {
        return ServiceUtils.isValidDate(date) ? ResponseEntity.ok(commentQueryUtils.findCommentByDate(date)
                .stream().map(CommentTransformer::fromCommentToCommentDTO).toList()) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
