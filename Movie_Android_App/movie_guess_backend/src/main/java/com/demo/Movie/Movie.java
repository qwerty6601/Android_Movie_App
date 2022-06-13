package com.example.demo.Movie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

@Entity // map Movie class to database
@Table // map to table (already mapped to Database thru @Entity)

public class Movie {

    @Id
    // Sequences generate unique value for each row
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue( // assigns entity identifiers automatically (using seq. generator)
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )

    @Column(name="movie_title")
    private String movie_title;

    @Column(name="duration")
    private String duration;

    @Column(name="genres")
    private String genres;

    @Column(name="actor_1_name")
    private String actor_1_name;

    @Column(name="actor_2_name")
    private String actor_2_name;

    @Column(name="actor_3_name")
    private String actor_3_name;

    @Column(name="director_name")
    private String director_name;

    @Column(name="plot_keywords")
    private String plot_keywords;

    @Column(name="movie_imdb_link")  // take this out?
    private String movie_imdb_link;

    @Column(name="m_language")
    private String m_language;

    @Column(name="country")
    private String country;

    @Column(name="content_rating")
    private String content_rating;

    @Column(name="title_year")
    public String title_year;

    @Column(name="imdb_score")
    private String imdb_score;

    @Column(name="counter")
    String counter;

    // movieCol --> function which when input movie, gets candidate
    public final static HashMap<String, Function<Movie, String>> movieAttributes = new HashMap<>();

    // run each time Movie class loaded (for each movie class)
    static {
        movieAttributes.put("movie_title", m-> m.movie_title);
        movieAttributes.put("duration", m-> m.duration);
        movieAttributes.put("genre", m->m.genres);
        movieAttributes.put("actor_1_name", m-> m.actor_1_name);
        movieAttributes.put("actor_2_name", m-> m.actor_2_name);
        movieAttributes.put("actor_3_name", m->m.actor_3_name);
        movieAttributes.put("director_name", m-> m.director_name);
       // movieAttributes.put("plot_keywords", m-> m.plot_keywords);
        movieAttributes.put("actor_3_name", m->m.actor_3_name);
    //    movieAttributes.put("m_language", m-> m.m_language);
    //    movieAttributes.put("country", m-> m.country);
        movieAttributes.put("content_rating", m->m.content_rating);
        movieAttributes.put("title_year", m-> m.title_year);
        movieAttributes.put("imdb_score", m-> m.imdb_score);
    }

    public Movie(){

    }

    public Movie(String movie_title, String duration, String genres, String actor_1_name, String actor_2_name, String actor_3_name, String director_name, String plot_keywords, String movie_imdb_link, String m_language, String country, String content_rating, String title_year, String imdb_score, String counter) {
        this.movie_title = movie_title;
        this.duration = duration;
        this.genres = genres;
        this.actor_1_name = actor_1_name;
        this.actor_2_name = actor_2_name;
        this.actor_3_name = actor_3_name;
        this.director_name = director_name;
        this.plot_keywords = plot_keywords;
        this.movie_imdb_link = movie_imdb_link;
        this.m_language = m_language;
        this.country = country;
        this.content_rating = content_rating;
        this.title_year = title_year;
        this.imdb_score = imdb_score;
        this.imdb_score = imdb_score;
        this.counter = counter;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getActor_1_name() {
        return actor_1_name;
    }

    public void setActor_1_name(String actor_1_name) {
        this.actor_1_name = actor_1_name;
    }

    public String getActor_2_name() {
        return actor_2_name;
    }

    public void setActor_2_name(String actor_2_name) {
        this.actor_2_name = actor_2_name;
    }

    public String getActor_3_name() {
        return actor_3_name;
    }

    public void setActor_3_name(String actor_3_name) {
        this.actor_3_name = actor_3_name;
    }

    public String getDirector_name() {
        return director_name;
    }

    public void setDirector_name(String director_name) {
        this.director_name = director_name;
    }

    public String getPlot_keywords() {
        return plot_keywords;
    }

    public void setPlot_keywords(String plot_keywords) {
        this.plot_keywords = plot_keywords;
    }

    public String getMovie_imdb_link() {
        return movie_imdb_link;
    }

    public void setMovie_imdb_link(String movie_imdb_link) {
        this.movie_imdb_link = movie_imdb_link;
    }

    public String getM_language() {
        return m_language;
    }

    public void setM_language(String language) {
        this.m_language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContent_rating() {
        return content_rating;
    }

    public void setContent_rating(String content_rating) {
        this.content_rating = content_rating;
    }

    public String getTitle_year() {
        return title_year;
    }

    public void setTitle_year(String title_year) {
        this.title_year = title_year;
    }

    public String getImdb_score() {
        return imdb_score;
    }

    public void setImdb_score(String imdb_score) {
        this.imdb_score = imdb_score;
    }
}
