package br.com.facol.repository;

import br.com.facol.DAO.UserDAO;
import br.com.facol.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository {
    UserDAO userDAO;

    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int insertUser(User user) {
        try {
            int generateId = userDAO.addUser(user);
            user.setId(generateId);
            return generateId;
        } catch (Exception e) {
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void removeUser(int id) {
        try {
            userDAO.removeUser(id);
        } catch (Exception e) {
            System.out.println("Erro ao remover usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user, int id) {
        try {
            userDAO.updateUser(id, user);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getAllUsers() {
        try {
            ArrayList<User> users = userDAO.getAllUsers();
            if (!users.isEmpty()) {
                return users;
            }else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuarios: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public User getUser(int id) {
        try {
            return userDAO.findUser(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
