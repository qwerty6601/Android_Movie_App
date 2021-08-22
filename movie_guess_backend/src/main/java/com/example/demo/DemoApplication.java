package com.example.demo;

import com.example.demo.Movie.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@SpringBootApplication
@RestController // makes this class serve Rest endpoints
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	// actual REST endpoint has @GetMapping, @PutMapping etc (which is in MovieController class)
   // @GetMapping displays the method (below @GetMapping) output below
}
