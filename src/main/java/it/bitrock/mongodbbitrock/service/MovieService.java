package it.bitrock.mongodbbitrock.service;

import it.bitrock.mongodbbitrock.dto.MovieDTO;
import it.bitrock.mongodbbitrock.dto.transformer.MovieTransformer;
import it.bitrock.mongodbbitrock.model.Movie;
import it.bitrock.mongodbbitrock.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {


    @Autowired
    private MovieRepository movieRepository;

    public ResponseEntity<MovieDTO> findMovieById(String id) {
        if (id != null) {
            Movie movie = movieRepository.findMovieById(id);
            if(movie!=null) {
                return ResponseEntity.ok().body(MovieTransformer.fromMovieToMovieDTO(movie));
            }else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<MovieDTO> findMovieByTitle(String title) {
        if (title != null) {
            Movie movie = movieRepository.findMovieByTitleIgnoreCase(title);
            if(movie!=null) {
                return ResponseEntity.ok().body(MovieTransformer.fromMovieToMovieDTO(movie));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<List<MovieDTO>> findMoviesByCommentEmail(String email) {
        if (email != null) {
            List<Movie> moviesByCommentEmail = movieRepository.joinMovieByEmailComment(email);
            if (!ObjectUtils.isEmpty(moviesByCommentEmail)) {
                return ResponseEntity.ok().body(moviesByCommentEmail.stream().map(MovieTransformer::fromMovieToMovieDTO).toList());
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

       public ResponseEntity<List<MovieDTO>> findMovieByRatingNumReviews(Double rating, Double numReviews) {
        if (rating != null && numReviews !=null) {
            List<Movie> movieByRatingNumReviews = movieRepository.findMovieByRatingNumReviews(rating,numReviews);
            if (!ObjectUtils.isEmpty(movieByRatingNumReviews)) {
                return ResponseEntity.ok().body(movieByRatingNumReviews.stream().map(MovieTransformer::fromMovieToMovieDTO).toList());
            }  return ResponseEntity.ok().body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
