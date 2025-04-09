package br.com.facol.service;

import br.com.facol.model.Restaurant;
import br.com.facol.repository.RestaurantRepository;

import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public void melhoresClassificados() {
        List<Restaurant> lista = restaurantRepository.getTopRatedRestaurants();
        if (lista.isEmpty()) {
            System.out.println("Nenhum restaurante foi encontrado.");
        } else {
            for (Restaurant restaurant : lista) {
                System.out.println("Id: " + restaurant.getId());
                System.out.println(restaurant.getNome());
                System.out.println(restaurant.getCidade());
                System.out.println(restaurant.getTag() + "\n");
            }
        }
    }

}
