package br.com.facol.application;

import br.com.facol.containerIoc.appConfig;
import br.com.facol.model.User;
import br.com.facol.repository.UserRepository;
import br.com.facol.service.RestaurantServiceImpl;
import br.com.facol.service.UserReviewServiceImpl;
import br.com.facol.util.JettyServer;
import java.util.Scanner;

public class Review {
    static {
        // Configuração inicial para reduzir logs
        System.setProperty("org.eclipse.jetty.util.log.class", "org.eclipse.jetty.util.log.StdErrLog");
        System.setProperty("org.eclipse.jetty.LEVEL", "WARN");
    }

    public static void main(String[] args) {
        // Inicia o Jetty em uma thread separada
        new Thread(() -> {
            try {
                System.out.println("[SERVIDOR] Iniciando Jetty na porta 8080...");
                new JettyServer().jetty();
            } catch (Exception e) {
                System.err.println("[SERVIDOR] Erro: " + e.getMessage());
            }
        }).start();

        // Configura serviços/repositórios
        RestaurantServiceImpl restaurantService = appConfig.createRestaurantService();
        UserReviewServiceImpl reviewService = appConfig.createUserReviewService();
        UserRepository userRepo = appConfig.createUserRepository();

        Scanner scanner = new Scanner(System.in);

        // 1. Cadastro do usuário
        System.out.println("\n=== CADASTRO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        User user = new User(senha, email, nome);
        int userId = userRepo.insertUser(user);
        System.out.println("Usuário criado! ID: " + userId);

        // 2. Menu interativo
        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Avaliar restaurante");
            System.out.println("2 - Ver ranking de restaurantes");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer

            switch (opcao) {
                case 1:
                    // Mostra todos os restaurantes antes de avaliar
                    System.out.println("\n=== RESTAURANTES DISPONÍVEIS ===");
                    restaurantService.melhoresClassificados();

                    System.out.print("\nDigite o ID do restaurante que deseja avaliar: ");
                    int idRestaurante = scanner.nextInt();
                    System.out.print("Nota (0-10): ");
                    double nota = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Comentário: ");
                    String comentario = scanner.nextLine();

                    reviewService.newReview(idRestaurante, nota, comentario, userId);
                    System.out.println("Avaliação registrada!");
                    break;

                case 2:
                    restaurantService.melhoresClassificados();
                    break;
            }
        } while (opcao != 0);

        scanner.close();
        System.out.println("Sistema encerrado.");
    }
}