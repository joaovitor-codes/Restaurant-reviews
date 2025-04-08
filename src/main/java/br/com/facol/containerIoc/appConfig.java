package br.com.facol.containerIoc;

import br.com.facol.DAO.RestaurantDAO;
import br.com.facol.DAO.UserDAO;
import br.com.facol.DAO.UserReviewDAO;
import br.com.facol.repository.RestaurantRepository;
import br.com.facol.repository.UserRepository;
import br.com.facol.repository.UserReviewRepository;
import br.com.facol.service.RestaurantServiceImpl;

public class appConfig {

    public static RestaurantServiceImpl createRestaurantService() {
        // instanciação dos DAO
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        UserReviewDAO userReviewDAO = new UserReviewDAO();

        // instaciação dos repositorios
        RestaurantRepository restaurantRepository = new RestaurantRepository(restaurantDAO);
        UserReviewRepository userReviewRepository = new UserReviewRepository(userReviewDAO);

        // retorna nova instancia
        return new RestaurantServiceImpl(restaurantRepository, userReviewRepository);
    }

    public static UserRepository createUserRepository() {
        UserDAO userDAO = new UserDAO();
        return new UserRepository(userDAO);
    }

    public static UserReviewRepository createUserReviewRepository() {
        UserReviewDAO userReviewDAO = new UserReviewDAO();
        return new UserReviewRepository(userReviewDAO);
    }

    public static RestaurantRepository createRestaurantRepository() {
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        return new RestaurantRepository(restaurantDAO);
    }
}
