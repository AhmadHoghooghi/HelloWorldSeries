package com.rhotiz.rws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}


//todo: configure json message converted not to serialize nulls
//todo: add error handling
//todo: postman test getAll for room
//todo: postman test getAll for room-category
//todo: add pagination functionality to get rooms
//todo: add apiResponse to room-category
//todo: add unit tests
//todo: add integration tests

//todo: refer to page 32 of `spring boot in action` to run application with spring boot maven plugin
