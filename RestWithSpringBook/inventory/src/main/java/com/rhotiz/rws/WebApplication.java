package com.rhotiz.rws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}


//to do: configure json message converted not to serialize nulls
//to do: add error handling
//to do: postman test findAll for room
//to do: add apiResponse to room-category
//to do: postman test findAll for room-category
//todo: add pagination functionality to get rooms
//todo: add pagination functionality to get room-categories
//todo: add unit tests
//todo: add integration tests
//todo: add error handling to room post with validation
//todo: add error handling to room-category post with validation

//todo: refer to page 32 of `spring boot in action` to run application with spring boot maven plugin
