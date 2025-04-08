package br.com.facol.repository;

import br.com.facol.model.Restaurant;
import br.com.facol.DAO.RestaurantDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {
    RestaurantDAO restaurantDAO;

    public RestaurantRepository(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    public void insertRestaurant(Restaurant restaurant) {
        try {
            restaurantDAO.addRestaurant(restaurant);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir restaurante: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void removeRestaurant(int id) {
        try {
            restaurantDAO.removeRestaurant(id);
        }catch (SQLException e) {
            System.out.println("Erro ao remover restaurante: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateRestaurant(Restaurant restaurant, int id) {
        try {
            restaurantDAO.updateRestaurant(id, restaurant);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar restaurante: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Restaurant> getAllRestaurants(){
        try{
            ArrayList<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
            if(!restaurants.isEmpty()){
                return restaurants;
            }else{
                return null;
            }
        }catch (SQLException e) {
            System.out.println("Erro ao listar restaurantes: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Restaurant getRestaurantById(int id) {
        try {
            return restaurantDAO.findRestaurant(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar restaurante: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Restaurant> getTopRatedRestaurants() {
        try{
            return restaurantDAO.TopRatedRestaurants();
        }catch (SQLException e) {
            System.out.println("Erro ao buscar restaurante: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
