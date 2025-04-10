package br.com.facol.repository;

import br.com.facol.model.UserReview;
import br.com.facol.DAO.UserReviewDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserReviewRepository {
    UserReviewDAO userReviewDAO;

    public UserReviewRepository(UserReviewDAO userReviewDAO) {
        this.userReviewDAO = userReviewDAO;
    }

    // Insere um novo UserReview
    public void insertUserReview(UserReview userReview) {
        try {
            userReviewDAO.addReview(userReview);
        } catch (Exception e) {
            System.out.println("Erro ao inserir review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Remove um UserReview pelo ID
    public void removeUserReview(int id) {
        try {
            userReviewDAO.removerReview(id);
        } catch (Exception e) {
            System.out.println("Erro ao remover review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Atualiza um UserReview existente
    public void updateUserReview(UserReview userReview, int id) {
        try {
            userReviewDAO.updateReview(userReview, id);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Lista todos os UserReviews
    public ArrayList<UserReview> getAllUserReviews() {
        try {
            ArrayList<UserReview> reviews = userReviewDAO.getAllReviews();
            if (!reviews.isEmpty()) {
                return reviews;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar reviews: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Retorna um UserReview pelo ID
    public UserReview getUserReview(int id) {
        try {
            return userReviewDAO.findReviewById(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Busca os comentários de um restaurante específico pelo restaurantId
    public ArrayList<UserReview> getReviewsByRestaurantId(int restaurantId) {
        try {
            return userReviewDAO.findReviewsByRestaurantId(restaurantId);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar reviews pelo restaurant_id: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}