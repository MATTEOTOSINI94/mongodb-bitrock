package it.bitrock.mongodbbitrock.service;

import io.vavr.control.Option;
import io.vavr.control.Try;
import it.bitrock.mongodbbitrock.dto.MovieDTO;
import it.bitrock.mongodbbitrock.dto.transformer.MovieTransformer;
import it.bitrock.mongodbbitrock.model.Movie;
import it.bitrock.mongodbbitrock.model.queryutils.MovieQueryUtils;
import it.bitrock.mongodbbitrock.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;


@Service
public class MovieService {


    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieQueryUtils movieQueryUtils;

    @Value("${moviePageSize}")
    private Integer pageSize;

    private BiPredicate<Double,Double> checkRatingNumReviews = (rating,numReviews)-> rating != null && numReviews != null;


    public ResponseEntity<Page<MovieDTO>> findAllMoviePaginate(Integer page){
    return Option.of(page).map(p->ResponseEntity
            .ok(movieRepository.findAllMoviesPaginate(PageRequest.of(Math.abs(p - 1),pageSize))
            .map(MovieTransformer::fromMovieToMovieDTO)))
            .getOrElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }


    public ResponseEntity<MovieDTO> findMovieByCommentId(String id){
      return Option.of(id)
              .map(idNotNull->
                      ResponseEntity.ok(Try.of(()->MovieTransformer.fromMovieToMovieDTO(movieQueryUtils.findMovieByComment(idNotNull)))
                      .getOrElse(new MovieDTO()))
        ).getOrElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    public ResponseEntity<MovieDTO> findMovieById(String id) {
     return Option.of(id).map(idNotNull->Option.of(movieRepository.findMovieById(idNotNull))
                .map(movie -> ResponseEntity.ok().body(MovieTransformer.fromMovieToMovieDTO(movie)))
                     .getOrElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .getOrElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

    public ResponseEntity<MovieDTO> findMovieByTitle(String title) {
       return Option.of(title)
                .map(titleNotNull-> Option.of(movieRepository.findMovieByTitleIgnoreCase(titleNotNull)).map(movie ->
                        ResponseEntity.ok().body(MovieTransformer.fromMovieToMovieDTO(movie))
                        ).getOrElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build()))
                .getOrElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }


    public ResponseEntity<List<MovieDTO>> findMoviesByCommentEmail(String email) {
        return (ResponseEntity<List<MovieDTO>>) Option.of(email)
                .map(emailNotNull-> movieRepository.joinMovieByEmailComment(emailNotNull)).map(
                listMoviesByCommentEmail-> !ObjectUtils.isEmpty(listMoviesByCommentEmail) ?
                        ResponseEntity.ok().body(listMoviesByCommentEmail.stream().map(MovieTransformer::fromMovieToMovieDTO).toList())
                        : ResponseEntity.ok().body(new ArrayList<>())
        ).getOrElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

       public ResponseEntity<List<MovieDTO>> findMovieByRatingNumReviews(Double rating, Double numReviews) {
        if (checkRatingNumReviews.test(rating,numReviews)) {
            List<Movie> movieByRatingNumReviews = movieRepository.findMovieByRatingNumReviews(rating,numReviews);
            return !ObjectUtils.isEmpty(movieByRatingNumReviews) ? ResponseEntity.ok().body(movieByRatingNumReviews.stream().map(MovieTransformer::fromMovieToMovieDTO).toList()): ResponseEntity.ok().body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
