package br.com.facol.controller;

import br.com.facol.DAO.RestaurantDAO;
import br.com.facol.model.Restaurant;
import br.com.facol.repository.RestaurantRepository;
import br.com.facol.containerIoc.appConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/restaurants")
public class RestaurantServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    RestaurantRepository restaurantRepository;

    @Override
    public void init() {
        this.restaurantRepository = appConfig.createRestaurantRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(restaurantRepository.getAllRestaurants()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Restaurant restaurant = mapper.readValue(req.getReader(), Restaurant.class);
        restaurantRepository.insertRestaurant(restaurant);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write(mapper.writeValueAsString(restaurant));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Restaurant restaurant = mapper.readValue(req.getReader(), Restaurant.class);
        restaurantRepository.updateRestaurant(restaurant, restaurant.getId());
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(mapper.writeValueAsString(restaurant));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Restaurant restaurant = mapper.readValue(req.getReader(), Restaurant.class);
            restaurantRepository.removeRestaurant(restaurant.getId());
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"message\":\"Restaurante removido com sucesso.\"}");
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Erro ao remover restaurante: " + e.getMessage() + "\"}");
        }
    }
}