package br.com.facol.containerIoc;

import br.com.facol.DAO.RestaurantDAO;
import br.com.facol.DAO.UserDAO;
import br.com.facol.DAO.UserReviewDAO;
import br.com.facol.repository.RestaurantRepository;
import br.com.facol.repository.UserRepository;
import br.com.facol.repository.UserReviewRepository;
import br.com.facol.service.RestaurantServiceImpl;
import br.com.facol.service.UserReviewServiceImpl;

public class appConfig {

    public static RestaurantServiceImpl createRestaurantService() {
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        RestaurantRepository restaurantRepository = new RestaurantRepository(restaurantDAO);
        return new RestaurantServiceImpl(restaurantRepository);
    }

    public static UserReviewServiceImpl createUserReviewService() {
        UserReviewDAO userReviewDAO = new UserReviewDAO();
        UserReviewRepository userReviewRepository = new UserReviewRepository(userReviewDAO);

        RestaurantDAO restaurantDAO = new RestaurantDAO();
        RestaurantRepository restaurantRepository = new RestaurantRepository(restaurantDAO);

        return new UserReviewServiceImpl(restaurantRepository, userReviewRepository);
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
