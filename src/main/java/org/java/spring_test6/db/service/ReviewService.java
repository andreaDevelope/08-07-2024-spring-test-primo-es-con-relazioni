package org.java.spring_test6.db.service;


import java.util.List;
import java.util.Optional;

import org.java.spring_test6.db.pojo.Recensioni;
import org.java.spring_test6.db.repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    public List<Recensioni> getAllReviews() {

        return reviewRepo.findAll();
    }

    public Optional<Recensioni> getReviewById(int id) {

        return reviewRepo.findById(id);
    }

    public void save(Recensioni review) {

        reviewRepo.save(review);
    }

    public void delete(Recensioni review) {

        reviewRepo.delete(review);
    }
}
