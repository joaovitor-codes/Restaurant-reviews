package br.com.facol.DAO;

import java.sql.*;

import br.com.facol.model.User;
import br.com.facol.util.DatabaseConnection;

public class UserDAO {
    public void addUser( User user ) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3,user.getSenha());
            stmt.executeUpdate();
        }catch(SQLException e) {
            System.err.println("Erro ao executar operação: " + e.getMessage());
            throw e;
        }
    }
}
