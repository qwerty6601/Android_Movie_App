package com.example.demo.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// this class will have all of the resources for API
@RestController // shows this class is a controller

public class MovieController {

    @Autowired
   public MovieService movieService;

   @Autowired
   private Main main;


    public MovieController(Main main, MovieService ms) {
        this.main = main;
        movieService=ms;
    }

   // @RequestMapping(path="api/v1/movie")  // can change to other address
    @GetMapping(value="api/v1/movie", produces = "application/json") // getting back json
    // gets App input (genre input (type, z.B. 0 or 1)) is from user
    public List<Movie> getMovie(@RequestParam(value="genre", required=true, defaultValue = "Fantasy") String genre ){
        return movieService.getMovieByGenre(genre);
    }

    @GetMapping(value="api/v1/test1", produces = "application/json")
    public String getFirstMovieTitle() {
        main.getAllMovies();
        return "hi";
    }

    @GetMapping(value="api/v1/test2", produces = "application/json")
    public List<Movie> getMovieList() {
        main.getAllMovies();
        return main.movies;
    }




    /* @GetMapping(value="api/v1/movie", produces = "application/json") // getting back json
    public List<Movie> getMovie(@RequestParam(value="duration", required=true, defaultValue = "200") int duration ){
        return movieService.getMovieByGenre(duration);
    } */

}
