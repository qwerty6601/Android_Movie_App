package com.example.demo.Movie;

import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity // map Counters class to database
@Table(name = "counter")
public class Counter {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int gender;

    @NotNull
    private String movieCol;

    @NotNull
    private String cand;

    @NotNull
    public int value;


    public Counter() {

    }

    public Counter(int gender, String movieCol, String cand, int counter) {
        this.gender = gender;
        this.movieCol = movieCol;
        this.cand = cand;
        this.value = counter;
    }

    public int getId() {
        return id;
    }

    public int getGender() {
        return gender;
    }

    public String getColname() {
        return movieCol;
    }

    public String getColcand() {
        return cand;
    }

    public int getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setColname(String colname) {
        this.movieCol = colname;
    }

    public void setColcand(String colcand) {
        this.cand = colcand;
    }

    public void setValue(int counter) {
        this.value = counter;
    }
}

