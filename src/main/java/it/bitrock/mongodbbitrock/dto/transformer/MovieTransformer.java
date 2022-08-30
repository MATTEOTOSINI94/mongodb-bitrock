package it.bitrock.mongodbbitrock.dto.transformer;

import it.bitrock.mongodbbitrock.dto.MovieDTO;
import it.bitrock.mongodbbitrock.model.Movie;


public class MovieTransformer {

    private MovieTransformer(){

    }

    public static MovieDTO fromMovieToMovieDTO(Movie movie){
        return new MovieDTO(
                movie.getId().toHexString(),
                movie.getPlot(),
                movie.getFullPlot(),
                movie.getGenres(),
                movie.getTitle(),
                movie.getRunTime(),
                movie.getCast(),
                movie.getNumMflixComments(),
                movie.getCountries(),
                movie.getReleased(),
                movie.getDirectors(),
                movie.getRated(),
                AwardsTransformer.fromAwardsToAwardsDTO(movie.getAwards()),
                movie.getLastUpdated(),
                movie.getImdb(),
               TomatoesTransformer.fromTomatoesToTomatoesDTO(movie.getTomatoes()));
    }
}
