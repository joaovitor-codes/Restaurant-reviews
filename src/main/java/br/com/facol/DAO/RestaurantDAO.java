package br.com.facol.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.facol.model.ENUM.Review;
import br.com.facol.util.DatabaseConnection;
import br.com.facol.model.Restaurant;

public class RestaurantDAO {
    public void addRestaurant(Restaurant restaurant) throws SQLException {
        String sql = "INSERT INTO restaurante (nome, cidade, bairro, cep, email, CNPJ, tag) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, restaurant.getNome());
            stmt.setString(2, restaurant.getCidade());
            stmt.setString(3, restaurant.getBairro());
            stmt.setString(4, restaurant.getCep());
            stmt.setString(5, restaurant.getEmail());
            stmt.setString(6, restaurant.getCNPJ());
            stmt.setString(7, restaurant.getTag());
            stmt.executeUpdate();
        }catch(SQLException e) {
            System.err.println("Erro ao adicionar restaurante: " + e.getMessage());
            throw e;
        }
    }

    public void removeRestaurant(int id) throws SQLException {
        String sql = "DELETE FROM restaurante WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("Erro ao remover restaurante: " + e.getMessage());
            throw e;
        }
    }

    public void updateRestaurant(int id, Restaurant restaurant) throws SQLException {
        String sql = "UPDATE restaurante SET nome = ?, cidade = ?, bairro = ?, cep = ?, email = ?, CNPJ = ?, tag = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, restaurant.getNome());
            stmt.setString(2, restaurant.getCidade());
            stmt.setString(3, restaurant.getBairro());
            stmt.setString(4, restaurant.getCep());
            stmt.setString(5, restaurant.getEmail());
            stmt.setString(6, restaurant.getCNPJ());
            stmt.setString(7, restaurant.getTag());
            stmt.setInt(8, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("Erro ao atualizar restaurante: " + e.getMessage());
            throw e;
        }
    }

    public ArrayList<Restaurant> getAllRestaurants() throws SQLException {
        String sql = "SELECT * FROM restaurante";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<Restaurant> restaurants = new ArrayList<>();
            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getInt("id"));
                restaurant.setNome(rs.getString("nome"));
                restaurant.setCidade(rs.getString("cidade"));
                restaurant.setBairro(rs.getString("bairro"));
                restaurant.setCep(rs.getString("cep"));
                restaurant.setEmail(rs.getString("email"));
                restaurant.setCNPJ(rs.getString("CNPJ"));
                restaurant.setTag(Review.valueOf(rs.getString("tag")));
                restaurants.add(restaurant);
            }
            return restaurants;
        }catch (SQLException e){
            System.err.println("Erro ao buscar restaurantes: " + e.getMessage());
            throw e;
        }
    }

    public Restaurant findRestaurant(int id) throws SQLException {
        String sql = "SELECT * FROM restaurante WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getInt("id"));
                restaurant.setNome(rs.getString("nome"));
                restaurant.setCidade(rs.getString("cidade"));
                restaurant.setBairro(rs.getString("bairro"));
                restaurant.setCep(rs.getString("cep"));
                restaurant.setEmail(rs.getString("email"));
                restaurant.setCNPJ(rs.getString("CNPJ"));
                restaurant.setTag(Review.valueOf(rs.getString("tag")));
                return restaurant;
            }else{
                return null;
            }
        }catch (SQLException e){
            System.err.println("Erro ao buscar restaurante: " + e.getMessage());
            throw e;
        }
    }

    public List<Restaurant> TopRatedRestaurants() throws SQLException {
        String sql = """
            SELECT r.*, 
                   COALESCE(AVG(ur.score), 0) AS avg_score
            FROM restaurante AS r 
            LEFT JOIN user_review AS ur ON r.id = ur.restaurant_id 
            GROUP BY r.id
            ORDER BY avg_score DESC; 
            """;


        List<Restaurant> topRestaurants = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getInt("id"));
                restaurant.setNome(rs.getString("nome"));
                restaurant.setCidade(rs.getString("cidade"));
                restaurant.setBairro(rs.getString("bairro"));
                restaurant.setCep(rs.getString("cep"));
                restaurant.setCNPJ(rs.getString("CNPJ"));
                restaurant.setEmail(rs.getString("email"));
                restaurant.setTag(Review.valueOf(rs.getString("tag")));
                topRestaurants.add(restaurant);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar restaurantes: " + e.getMessage(), e);
        }

        return topRestaurants;

    }
}
