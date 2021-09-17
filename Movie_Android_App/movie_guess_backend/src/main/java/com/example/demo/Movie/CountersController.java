package com.example.demo.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountersController {

    @Autowired
    CountersRepository rep;


    @GetMapping("/counters")
    public List<Counter> getCounters() {
        List<Counter> cList = rep.findAll();
        return cList;
    }
}
