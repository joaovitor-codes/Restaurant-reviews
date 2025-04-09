package br.com.facol.view;

import br.com.facol.controller.Controller;

public class ReviewView {
    private final Controller controller;

    public ReviewView(Controller controller) {
        this.controller = controller;
    }

    public void submitReview(int restaurantId, double score, String reviewText, int userId) {
        controller.addReview(restaurantId, score, reviewText, userId);
        System.out.println("✅ Avaliação enviada!");
    }
}