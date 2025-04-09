package br.com.facol.service;

import br.com.facol.model.ENUM.Review;
import br.com.facol.model.Restaurant;
import br.com.facol.model.UserReview;
import br.com.facol.repository.RestaurantRepository;
import br.com.facol.repository.UserReviewRepository;

import java.util.ArrayList;

public class UserReviewServiceImpl implements UserReviewService {
    private final UserReviewRepository userReviewRepository;
    private final RestaurantRepository restaurantRepository;

    public UserReviewServiceImpl(RestaurantRepository restaurantRepository, UserReviewRepository userReviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userReviewRepository = userReviewRepository;
    }

    public Review calcMedia(int restaurantId, double score){
        ArrayList<UserReview> reviews = userReviewRepository.getAllUserReviews();
        ArrayList<UserReview> reviewsFilter = new ArrayList<>();
        for (UserReview userReview : reviews) {
            if (userReview.getRestaurantId() == restaurantId) {
                reviewsFilter.add(userReview);
            }
        }

        if (reviewsFilter.isEmpty()) {
            return null;
        }else{
            double totalScore = score;
            for (UserReview userReview : reviewsFilter) {
                totalScore += userReview.getScore();
            }
            double media = totalScore / reviewsFilter.size();
            if (media <= 4){
                return Review.RUIM;
            }else if (media >= 5 && media < 8){
                return Review.BOM;
            }else if (media >= 8){
                return Review.MUITO_BOM;
            }
        }
        return Review.SEM_CLASSIFICACAO;
    }

    public void newReview(int restaurantId, double score, String review_text, int user_id){
        UserReview userReview = new UserReview(review_text, restaurantId, user_id, score);
        userReviewRepository.insertUserReview(userReview);
        Restaurant restaurant = restaurantRepository.getRestaurantById(restaurantId);
        restaurant.setTag(calcMedia(restaurantId, score));
        restaurantRepository.updateRestaurant(restaurant, restaurantId);
    }
}
