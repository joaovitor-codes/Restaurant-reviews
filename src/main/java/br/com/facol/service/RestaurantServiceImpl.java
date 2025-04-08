package br.com.facol.service;

import br.com.facol.model.Restaurant;
import br.com.facol.model.UserReview;
import br.com.facol.model.ENUM.Review;
import br.com.facol.repository.RestaurantRepository;
import br.com.facol.repository.UserReviewRepository;

import java.util.ArrayList;
import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserReviewRepository userReviewRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, UserReviewRepository userReviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userReviewRepository = userReviewRepository;
    }


    public Review calcMedia(int restaurantId){
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
            double totalScore = 0;
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

    public void melhoresClassificados() {
        List<Restaurant> lista = restaurantRepository.getTopRatedRestaurants();
        if (lista.isEmpty()) {
            System.out.println("Nenhum restaurante classificado como 'MUITO BOM' foi encontrado.");
        } else {
            for (Restaurant restaurant : lista) {
                System.out.println(restaurant.getNome());
                System.out.println(restaurant.getCidade());
                System.out.println(restaurant.getTag());
            }
        }
    }

}
