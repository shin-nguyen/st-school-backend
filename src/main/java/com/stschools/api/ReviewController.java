package com.stschools.api;

import com.stschools.dto.ReviewDTO;
import com.stschools.exception.ApiRequestException;
import com.stschools.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getReviewOfCourse(@PathVariable(name = "id") Long id){
        try{
            return new ResponseEntity<>(reviewService.findByCourse(id), HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO){
        try{
            return new ResponseEntity<>(reviewService.addReview(reviewDTO), HttpStatus.CREATED);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
