package br.com.facol.controller;

import br.com.facol.model.UserReview;
import br.com.facol.repository.UserReviewRepository;
import br.com.facol.containerIoc.appConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-reviews")
public class UserReviewServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    UserReviewRepository userReviewRepository;

    @Override
    public void init() {
        this.userReviewRepository = appConfig.createUserReviewRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(userReviewRepository.getAllUserReviews()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserReview userReview = mapper.readValue(req.getReader(), UserReview.class);
        userReviewRepository.insertUserReview(userReview);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write(mapper.writeValueAsString(userReview));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserReview userReview = mapper.readValue(req.getReader(), UserReview.class);
        userReviewRepository.updateUserReview(userReview, userReview.getReview_id());
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(mapper.writeValueAsString(userReview));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.setContentType("application/json");
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"O parâmetro 'id' não pode ser nulo ou vazio.\"}");
                return;
            }
            try {
                int userReviewId = Integer.parseInt(idParam);
                userReviewRepository.removeUserReview(userReviewId);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"message\":\"Review removido com sucesso.\"}");
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"O parâmetro 'id' deve ser um número válido.\"}");
            }
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Erro ao remover o review: " + e.getMessage() + "\"}");
        }
    }
}