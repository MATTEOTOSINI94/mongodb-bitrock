package it.bitrock.mongodbbitrock.controller;

import it.bitrock.mongodbbitrock.dto.CommentDTO;
import it.bitrock.mongodbbitrock.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {


    @Autowired
    private CommentService commentService;



    @GetMapping("/delete-comment/{id}")
    public ResponseEntity<String> findMovieById(@PathVariable String id){
        return commentService.removeCommentById(id);
    }

    @PostMapping("/save-comment")
    public ResponseEntity<CommentDTO> saveComment(@RequestBody CommentDTO commentDTO){
        return commentService.saveComment(commentDTO);
    }


    @GetMapping("/comments")
    public ResponseEntity<List<CommentDTO>> getCommentByEmail(@RequestParam String email){
        return commentService.getCommentByEmail(email);
    }


}
