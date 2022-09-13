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
    public ResponseEntity<String> removeCommentByEmail(@PathVariable String email){
        return commentService.removeCommentByEmail(email);
    }

    @PostMapping("/save-comment")
    public ResponseEntity<CommentDTO> saveComment(@RequestBody CommentDTO commentDTO){
        return commentService.saveComment(commentDTO);
    }

    @GetMapping("/find-comments/{email}")
    public ResponseEntity<List<CommentDTO>> findCommentByEmail(@RequestParam String email){
        return commentService.findCommentByEmail(email);
    }

    @GetMapping("/find-comments/{date}")
     public ResponseEntity<List<CommentDTO>> findCommentByDate(@RequestParam String date){
        return commentService.findCommentsByDate(date);
    }



}
