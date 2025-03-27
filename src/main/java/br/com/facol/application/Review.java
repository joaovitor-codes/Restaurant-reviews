package br.com.facol.application;

import java.sql.SQLException;
import br.com.facol.util.JettyServer;

public class Review {
    public static void main(String[] args) throws SQLException {
        JettyServer server = new JettyServer();
        try {
            server.jetty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
