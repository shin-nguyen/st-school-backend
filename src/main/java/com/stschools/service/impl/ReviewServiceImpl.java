package com.stschools.service.impl;

import com.stschools.dto.ReviewDTO;
import com.stschools.entity.Review;
import com.stschools.repository.ReviewRepository;
import com.stschools.service.ReviewService;
import com.stschools.util.ModelMapperControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getAll() {
        return ModelMapperControl.mapAll(reviewRepository.findAll(), ReviewDTO.class);
    }

    @Override
    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        reviewRepository.save(ModelMapperControl.map(reviewDTO, Review.class));
        return reviewDTO;
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewDTO> findByCourse(Long id) {
        return ModelMapperControl.mapAll(reviewRepository.findByCourseId(id), ReviewDTO.class);
    }
}
