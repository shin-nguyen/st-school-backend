package com.stschools.service;

import com.stschools.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAll();

    ReviewDTO addReview(ReviewDTO reviewDTO);

    void deleteReview(Long id);

    List<ReviewDTO> findByCourse(Long id);
}
