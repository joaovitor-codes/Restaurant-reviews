package br.com.facol.application;


import br.com.facol.containerIoc.appConfig;
import br.com.facol.service.RestaurantService;
import br.com.facol.service.RestaurantServiceImpl;
import br.com.facol.service.UserReviewService;
import br.com.facol.service.UserReviewServiceImpl;
import br.com.facol.util.JettyServer;

public class Review {
    public static void main(String[] args) {
        UserReviewService review = new UserReviewServiceImpl(appConfig.createRestaurantRepository(), appConfig.createUserReviewRepository());
        int restaurantId = 2;
        double score = 10.0;
        String review_text = "Lugar perfeito, adorei, recomendo";
        int user_id = 3;

        review.newReview(restaurantId, score, review_text, user_id);
        RestaurantService service = new RestaurantServiceImpl(appConfig.createRestaurantRepository());
        service.melhoresClassificados();

        JettyServer server = new JettyServer();
        try {
            server.jetty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
