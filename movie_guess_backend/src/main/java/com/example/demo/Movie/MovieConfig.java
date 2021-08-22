package com.example.demo.Movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class MovieConfig {
    /*@Bean // objects that form the backbone of your application and that are managed by the Spring IoC container
    CommandLineRunner commandLineRunner(MovieRepository repository) {
        return args -> {

            System.out.println(repository.findMovieByMovieTitle("Avatar"));
            // insert into table
            /*repository.saveAll(
                    List.of()
            ); */
  //      };
  //  }
}
