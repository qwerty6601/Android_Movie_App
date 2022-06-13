package com.example.demo.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public List<Movie> getMovieByGenre(String genre) {
        System.out.println( "##########################" + genre);
                return movieRepository.findByGenresContaining(genre);
    }

    public void addNewMovie(Movie movie) {
        Optional<Movie> movieOptional = movieRepository
                .findMovieByMovieTitle(movie.getMovie_title());
        if (movieOptional.isPresent())
            throw new IllegalStateException("movie already exists!");
        movieRepository.save(movie);
    }

    public void deleteMovie(String movieId) {
        boolean exists = movieRepository.existsById(movieId);
        if (!exists){
            throw new IllegalStateException(
                    "movie with id " + movieId + " does not exist" );
        }
        movieRepository.deleteById(movieId);
    }


}