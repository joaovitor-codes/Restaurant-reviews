package br.com.facol.DAO;

import java.sql.*;
import java.util.ArrayList;
import br.com.facol.model.UserReview;
import br.com.facol.util.DatabaseConnection;

public class UserReviewDAO {
    public void addReview(UserReview review) throws SQLException {
        String sql = "insert into user_review (review_id, user_id, restaurant_id, review_text, tag)values(?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getReview_id());
            stmt.setInt(2, review.getUserId());
            stmt.setInt(3, review.getRestaurantId());
            stmt.setString(4, review.getReview_text());
            stmt.setString(5, review.getTag());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Erro ao inserir review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void removerReview(int id) throws SQLException {
        String sql = "delete from user_review where review_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(SQLException e) {
            System.err.println("Erro ao remover review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateReview(UserReview review) throws SQLException {
        String sql = "update user_review set user_id = ?, restaurant_id = ?, review_text = ?, tag = ? where review_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getUserId());
            stmt.setInt(2, review.getRestaurantId());
            stmt.setString(3, review.getReview_text());
            stmt.setString(4, review.getTag());
            stmt.executeUpdate();
        }catch(SQLException e) {
            System.err.println("Erro ao atualizar review: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
