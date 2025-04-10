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
        RestaurantRepository repository = new RestaurantRepository(restaurantDAO);
        return new RestaurantServiceImpl(repository);
    }

    public static UserReviewServiceImpl createUserReviewService() {
        UserReviewDAO userReviewDAO = new UserReviewDAO();
        UserReviewRepository userReviewRepository = new UserReviewRepository(userReviewDAO);

        RestaurantDAO restaurantDAO = new RestaurantDAO();
        RestaurantRepository restaurantRepository = new RestaurantRepository(restaurantDAO);

        UserDAO userDAO = new UserDAO();
        UserRepository userRepository = new UserRepository(userDAO);


        return new UserReviewServiceImpl(restaurantRepository, userReviewRepository, userRepository);
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
