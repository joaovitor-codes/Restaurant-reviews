package br.com.facol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.com.facol.DAO.UserDAO;
import br.com.facol.model.User;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final UserDAO userDAO = new UserDAO();
    private final User user = new User();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(userDAO));
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            userDAO.addUser(user);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write(mapper.writeValueAsString(user));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
