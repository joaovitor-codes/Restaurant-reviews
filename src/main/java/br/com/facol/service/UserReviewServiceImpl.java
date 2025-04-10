package br.com.facol.service;

import br.com.facol.model.ENUM.Review;
import br.com.facol.model.Restaurant;
import br.com.facol.model.User;
import br.com.facol.model.UserReview;
import br.com.facol.repository.UserRepository;
import br.com.facol.repository.RestaurantRepository;
import br.com.facol.repository.UserReviewRepository;

import java.util.ArrayList;

public class UserReviewServiceImpl implements UserReviewService {
    private final UserReviewRepository userReviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public UserReviewServiceImpl(RestaurantRepository restaurantRepository, UserReviewRepository userReviewRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userReviewRepository = userReviewRepository;
        this.userRepository = userRepository;
    }

    // metodo calcular score
    public Review calcMedia(int restaurantId, double score) {
        ArrayList<UserReview> reviews = userReviewRepository.getAllUserReviews();
        ArrayList<UserReview> reviewsFilter = new ArrayList<>();

        for (UserReview userReview : reviews) {
            if (userReview.getRestaurantId() == restaurantId) {
                reviewsFilter.add(userReview);
            }
        }

        double totalScore = score;
        int totalReviews = 1; // Conta a nova avaliação

        for (UserReview userReview : reviewsFilter) {
            totalScore += userReview.getScore();
            totalReviews++;
        }

        double media = totalScore / totalReviews;

        if (media > 0 && media <= 4) {
            return Review.RUIM;
        } else if (media > 4 && media < 8) {
            return Review.BOM;
        } else if (media >= 8) {
            return Review.MUITO_BOM;
        }

        return Review.SEM_CLASSIFICACAO;
    }

    public void newReview(int restaurantId, double score, String review_text, int user_id) {

        UserReview userReview = new UserReview(review_text, restaurantId, user_id, score);
        userReviewRepository.insertUserReview(userReview);

        Restaurant restaurant = restaurantRepository.getRestaurantById(restaurantId);
        Review newTag = calcMedia(restaurantId, score);
        restaurant.setTag(newTag);
        restaurantRepository.updateRestaurant(restaurant, restaurantId);
    }

    // Metodo pegar comentarios
    public ArrayList<String> getReviews_texts(int restaurantId) {
        ArrayList<String> reviewTexts = new ArrayList<>();
        try {
            ArrayList<UserReview> reviews = userReviewRepository.getReviewsByRestaurantId(restaurantId);
            if (reviews != null && !reviews.isEmpty()) {
                for (UserReview review : reviews) {
                    reviewTexts.add(review.getReview_text());
                }
            } else {
                System.out.println("Nenhum comentário encontrado para o restaurante com ID: " + restaurantId);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar textos das reviews: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return reviewTexts;
    }

    // Metodo para Registro
    public boolean register(String nome, String email, String senha) {
        try {
            // Verifica se o usuário já existe no repositório
            ArrayList<User> users = userRepository.getAllUsers();
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    System.out.println("Erro: Este email já está registrado.");
                    return false;
                }
            }
            // Cria um novo usuário e insere pelo repositório
            User newUser = new User(senha, email, nome);
            userRepository.insertUser(newUser);
            System.out.println("Registro realizado com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao realizar registro: " + e.getMessage());
            return false;
        }
    }

    // Metodo para login
    public User login(String email, String senha) {
        try {
            // Busca o usuário no repositório (indiretamente acessando o DAO)
            ArrayList<User> users = userRepository.getAllUsers();
            for (User user : users) {
                if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                    System.out.println("Login realizado com sucesso!");
                    return user;
                }
            }
            System.out.println("Erro: Email ou senha inválidos.");
        } catch (Exception e) {
            System.out.println("Erro ao realizar login: " + e.getMessage());
        }
        return null;
    }
}
