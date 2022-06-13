package com.example.demo.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;


public class Question {
    String movieCol;
    String crit;
    Function<Movie, String> getCrit;
    int id;

    public Question(String movieCol, String crit, int id){
        this.movieCol=movieCol;
        this.crit= crit;
        this.getCrit = Movie.movieAttributes.get(movieCol);
        this.id = id;
    }

    public boolean answersYes(Movie m){
        // wenn in der Spalte not_given steht soll der Film nicht removed werden //TODO
        if (crit.equals("not_given")) return false;

        return getCrit.apply(m).equals(crit);
    }

    public boolean answersNo(Movie m){
        // wenn in der Spalte not_given steht soll der Film nicht removed werden //TODO
        if (crit.equals("not_given")) return false;

            return !getCrit.apply(m).equals(crit);
    }
    /***
     * movie probabilities
     * @param probabilities For each movie, gives the prob(product of the counter ratios)
     *         P(m) = product_col(counter(col, cand(m, col)) / sum_x(counter(col, x) )
     *         x: any attribute value of column
     *                      old way:
     *                      P(m) = counter(m)/sum_x(counter x)
     *
     *  Example
     *                      counter(genre, fantasy)=2
     *                      counter(genre, action=3
     *                      counter(duration, 60)=1
     *                      counter(duration, 90)=4
     *               m={genre:action, duration:90}
     *                      P(m) = 3/5 * 4/5
     * @param totalProb
     * @return
     */
    public double yesProbability(
            HashMap<Movie, Double> probabilities,
            HashMap<String, HashMap<String, List<Movie>>> attributeLookup, double totalProb) {

        List<Movie> similarMovies = attributeLookup.get(movieCol).get(crit);
        if (similarMovies==null)
            return 0;
        double prob = 0;
        for (Movie m:similarMovies) {
            prob+= probabilities.get(m);
        }
        return prob/totalProb;
    }

    public String getMovieCol() {
        return movieCol;
    }

    public void setMovieCol(String movieCol) {
        this.movieCol = movieCol;
    }


} 
