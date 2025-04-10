package br.com.facol.service;

import br.com.facol.model.ENUM.Review;
import br.com.facol.model.User;

import java.util.ArrayList;

public interface UserReviewService {
    void newReview(int restaurantId, double score, String review_text, int user_id);
    Review calcMedia(int restaurantId, double score);
    ArrayList<String> getReviews_texts(int restaurantId);
    boolean register(String nome, String email, String senha);
    User login(String email, String senha);
}
