package com.example.demo.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


// ID of type String
@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

// SELECT * FROM movie WHERE movieId = ? ; SAMPLE query
    @Query("SELECT m FROM Movie m WHERE m.movie_title=?1") // (another, better way for comment above)
    Optional<Movie> findMovieByMovieTitle(String movieId);

    @Query("SELECT m FROM Movie m WHERE m.duration=?1")
    Optional<Movie> findMovieByDurationGreaterThanEqual(int duration);

    @Query("SELECT m FROM Movie m WHERE m.duration=?1")
    Optional<Movie> findMovieByDurationLessThan(int duration);

    @Transactional
    @Modifying
    @Query("UPDATE Movie SET counter = counter + 1 WHERE movie_title =?1")
    void increaseCounterValue(String title);


   //@Query("SELECT m FROM Movie m WHERE m.genres like '%:genres:%'") // (another, better way for comment above)

   List<Movie> findByGenresContaining(String genres);

   Iterable<Movie> findAll();
}


