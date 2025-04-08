package br.com.facol.controller;

import br.com.facol.DAO.UserDAO;
import br.com.facol.model.User;
import br.com.facol.repository.UserRepository;
import br.com.facol.containerIoc.appConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    UserRepository userRepository;

    @Override
    public void init(){
        this.userRepository = appConfig.createUserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(userRepository));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = mapper.readValue(req.getReader(), User.class);
        userRepository.insertUser(user);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write(mapper.writeValueAsString(user.toString()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = mapper.readValue(req.getReader(), User.class);
        userRepository.updateUser(user, user.getUser_id());
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("{\"message\":\"Usuário removido com sucesso.\"}");
        resp.getWriter().write(mapper.writeValueAsString(user));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            User user = mapper.readValue(req.getReader(), User.class);
            userRepository.removeUser(user.getUser_id());
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"message\":\"Usuário removido com sucesso.\"}");
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Erro ao remover usuário: " + e.getMessage() + "\"}");
        }
    }

}
