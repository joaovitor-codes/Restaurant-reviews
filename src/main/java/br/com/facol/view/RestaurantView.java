package br.com.facol.view;

import br.com.facol.controller.Controller;

public class RestaurantView {
    private final Controller controller;

    public RestaurantView(Controller controller) {
        this.controller = controller;
    }

    public void showTopRestaurants() {
        System.out.println("=== TOP RESTAURANTES ===");
        controller.displayTopRatedRestaurants();
    }
}