package br.com.facol.controller;

import br.com.facol.model.ENUM.Review;
import br.com.facol.service.RestaurantService;
import br.com.facol.service.UserReviewService;

public class Controller {
    private final RestaurantService restaurantService;
    private final UserReviewService userReviewService;

    public Controller(RestaurantService restaurantService, UserReviewService userReviewService) {
        this.restaurantService = restaurantService;
        this.userReviewService = userReviewService;
    }

    public void displayTopRatedRestaurants() {
        restaurantService.melhoresClassificados();
    }

    public void addReview(int restaurantId, double score, String reviewText, int userId) {
        userReviewService.newReview(restaurantId, score, reviewText, userId);
    }

    public Review getAverageRating(int restaurantId, double score) {
        return userReviewService.calcMedia(restaurantId, score);
    }
}