package it.bitrock.mongodbbitrock.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommentDTO {

    private String id;
    private String name;
    private String email;
    private String movie_id;
    private LocalDateTime date;
    private String text;

    public CommentDTO(){

    }

    public CommentDTO(String id, String name, String email, String movie_id, LocalDateTime date, String text) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.movie_id = movie_id;
        this.date = date;
        this.text = text;
    }

    public CommentDTO(String name, String email, String movie_id, LocalDateTime date, String text) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.movie_id = movie_id;
        this.date = date;
        this.text = text;
    }
}
