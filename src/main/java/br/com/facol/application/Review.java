package br.com.facol.application;

import br.com.facol.model.Restaurant;
import br.com.facol.DAO.UserDAO;
import br.com.facol.model.User;
import java.sql.SQLException;

public class Review {
    public static void main(String[] args) throws SQLException {
        User user = new User("JOAO", "JOAO@gmail.com", "ADMIN");
        UserDAO userDAO = new UserDAO();
        userDAO.addUser(user);
    }
}
