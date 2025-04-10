package br.com.facol.application;

import br.com.facol.containerIoc.appConfig;
import br.com.facol.controller.Controller;
import br.com.facol.model.User;
import br.com.facol.repository.UserRepository;
import br.com.facol.service.RestaurantServiceImpl;
import br.com.facol.service.UserReviewServiceImpl;
import br.com.facol.util.JettyServer;

import java.util.ArrayList;
import java.util.Scanner;

public class Review {
    static {
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


        RestaurantServiceImpl restaurantService = appConfig.createRestaurantService();
        UserReviewServiceImpl reviewService = appConfig.createUserReviewService();
        UserRepository userRepo = appConfig.createUserRepository();

        Controller controller = new Controller(restaurantService, reviewService);

        Scanner scanner = new Scanner(System.in);
        User loggedUser = null;

        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Avaliar restaurante");
            System.out.println("2 - Ver ranking de restaurantes");
            System.out.println("3 - Ver comentários de um restaurante");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Verifica se o usuário está autenticado
                    while (loggedUser == null) {
                        System.out.println("\n=== AUTENTICAÇÃO ===");
                        System.out.println("1 - Login");
                        System.out.println("2 - Registrar");
                        System.out.print("Escolha: ");
                        int authOption = scanner.nextInt();
                        scanner.nextLine();

                        if (authOption == 1) {
                            System.out.print("Email: ");
                            String email = scanner.nextLine();
                            System.out.print("Senha: ");
                            String senha = scanner.nextLine();

                            loggedUser = controller.loginUser(email, senha);
                        } else if (authOption == 2) {
                            System.out.println("\n=== Cadastro ===");
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("Email: ");
                            String email = scanner.nextLine();
                            System.out.print("Senha: ");
                            String senha = scanner.nextLine();

                            if (controller.registerUser(nome, email, senha)) {
                                System.out.println("Agora você pode fazer login.");
                            }
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    }

                    // Usuário autenticado pode avaliar
                    System.out.println("\n=== Avaliação ===");
                    controller.displayTopRatedRestaurants();
                    System.out.print("\nDigite o ID do restaurante que deseja avaliar: ");
                    int idRestaurante = scanner.nextInt();
                    System.out.print("Nota (0-10): ");
                    double nota = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Comentário: ");
                    String comentario = scanner.nextLine();

                    controller.addReview(idRestaurante, nota, comentario, loggedUser.getUser_id());
                    System.out.println("Avaliação registrada!");
                    break;

                case 2:
                    System.out.println("\n=== RANKING DE RESTAURANTES ===");
                    controller.displayTopRatedRestaurants();
                    break;

                case 3:
                    System.out.println("\n=== RESTAURANTES DISPONÍVEIS ===");
                    controller.displayTopRatedRestaurants();

                    System.out.print("\nDigite o ID do restaurante que deseja visualizar os comentários: ");
                    int restaurantId = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner

                    System.out.println("\n=== SESSÃO DE COMENTÁRIOS ===");
                    System.out.println("Restaurante ID: " + restaurantId);

                    try {
                        ArrayList<String> comentarios = reviewService.getReviews_texts(restaurantId);
                        if (comentarios != null && !comentarios.isEmpty()) {
                            for (int i = 0; i < comentarios.size(); i++) {
                                System.out.println("Comentário " + (i + 1) + ": " + comentarios.get(i));
                            }
                            System.out.println("---------------------------");
                        } else {
                            System.out.println("Nenhum comentário encontrado para este restaurante.");
                            System.out.println("---------------------------");
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao buscar os comentários: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}