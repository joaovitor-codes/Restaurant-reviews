package br.com.facol.service;

public interface UserReviewService {
    void newReview(int restaurantId, double score, String review_text, int user_id);
}
