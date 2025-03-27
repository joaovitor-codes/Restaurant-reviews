package br.com.facol.controller;

import br.com.facol.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.com.facol.DAO.UserDAO;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final UserDAO userDAO = new UserDAO();
    //colocar que o controller faça uma instanciação de um objeto especificado
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(userDAO));
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = mapper.readValue(req.getReader(), User.class);
        try {
            userDAO.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write(mapper.writeValueAsString(user.toString()));
    }
}
