package br.com.facol.service;

import br.com.facol.model.ENUM.Review;

public interface UserReviewService {
    void newReview(int restaurantId, double score, String review_text, int user_id);
    Review calcMedia(int restaurantId, double score);
}
