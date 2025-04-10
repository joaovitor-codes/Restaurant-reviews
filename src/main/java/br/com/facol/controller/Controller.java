package br.com.facol.controller;

import br.com.facol.model.ENUM.Review;
import br.com.facol.model.User;
import br.com.facol.service.RestaurantService;
import br.com.facol.service.UserReviewService;

public class Controller {
    private final RestaurantService restaurantService;
    private final UserReviewService userReviewService;

    public Controller(RestaurantService restaurantService, UserReviewService userReviewService) {
        this.restaurantService = restaurantService;
        this.userReviewService = userReviewService;
    }

    // Exibe os melhores restaurantes
    public void displayTopRatedRestaurants() {
        restaurantService.melhoresClassificados();
    }

    // Adiciona uma nova avaliação
    public void addReview(int restaurantId, double score, String reviewText, int userId) {
        userReviewService.newReview(restaurantId, score, reviewText, userId);
    }

    // Calcula a média de avaliação de um restaurante
    public Review getAverageRating(int restaurantId, double score) {
        return userReviewService.calcMedia(restaurantId, score);
    }

    // Método para realizar login
    public User loginUser(String email, String senha) {
        return userReviewService.login(email, senha);
    }

    // Método para realizar registro
    public boolean registerUser(String nome, String email, String senha) {
        return userReviewService.register(nome, email, senha);
    }
}