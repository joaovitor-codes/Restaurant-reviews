package br.com.facol.DAO;

import java.sql.*;
import java.util.ArrayList;

import br.com.facol.model.ENUM.Review;
import br.com.facol.model.UserReview;
import br.com.facol.util.DatabaseConnection;

public class UserReviewDAO {

    public void addReview(UserReview review) throws SQLException {
        String sql = "INSERT INTO user_review (review_id, user_id, restaurant_id, review_text, score) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getReview_id());
            stmt.setInt(2, review.getUserId());
            stmt.setInt(3, review.getRestaurantId());
            stmt.setString(4, review.getReview_text());
            stmt.setDouble(5, review.getScore());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void removerReview(int id) throws SQLException {
        String sql = "DELETE FROM user_review WHERE review_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao remover review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateReview(UserReview review, int id) throws SQLException {
        String sql = "UPDATE user_review SET user_id = ?, restaurant_id = ?, review_text = ?,score = ? WHERE review_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getUserId());
            stmt.setInt(2, review.getRestaurantId());
            stmt.setString(3, review.getReview_text());
            stmt.setDouble(4, review.getScore());
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ArrayList<UserReview> getAllReviews() throws SQLException {
        String sql = "SELECT * FROM user_review";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ArrayList<UserReview> reviews = new ArrayList<>();
            while (rs.next()) {
                UserReview review = new UserReview();
                review.setReview_id(rs.getInt("review_id"));
                review.setUser_id(rs.getInt("user_id"));
                review.setRestaurant_id(rs.getInt("restaurant_id"));
                review.setReview_text(rs.getString("review_text"));
                review.setScore(rs.getDouble("score"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar todas as reviews: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public UserReview findReviewById(int id) throws SQLException {
        String sql = "SELECT * FROM user_review WHERE review_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UserReview review = new UserReview();
                review.setReview_id(rs.getInt("review_id"));
                review.setUser_id(rs.getInt("user_id"));
                review.setRestaurant_id(rs.getInt("restaurant_id"));
                review.setReview_text(rs.getString("review_text"));
                review.setScore(rs.getDouble("score"));
                return review;
            } else {
                return null; // Caso não encontre nenhuma review com o ID fornecido
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar review pelo ID: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ArrayList<UserReview> findReviewsByRestaurantId(int restaurantId) throws SQLException {
        String sql = "SELECT * FROM user_review WHERE restaurant_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, restaurantId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<UserReview> reviews = new ArrayList<>();
            while (rs.next()) {
                UserReview review = new UserReview();
                review.setReview_id(rs.getInt("review_id"));
                review.setUser_id(rs.getInt("user_id"));
                review.setRestaurant_id(rs.getInt("restaurant_id"));
                review.setReview_text(rs.getString("review_text"));
                review.setScore(rs.getDouble("score"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar reviews pelo restaurant_id: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}