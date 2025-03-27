package br.com.facol.DAO;

import java.sql.*;
import java.util.ArrayList;
import br.com.facol.model.User;
import br.com.facol.util.DatabaseConnection;


public class UserDAO {
    public void addUser(User user ) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3,user.getSenha());
            stmt.executeUpdate();
        }catch(SQLException e) {
            System.err.println("Erro ao executar operação: " + e.getMessage());
            throw e;
        }
    }

    public void removeUser(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(SQLException e) {
            System.err.println("Erro ao executar operação: " + e.getMessage());
            throw e;
        }
    }

    public void updateUser(int id, User user) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSenha());
            stmt.executeUpdate();
        }catch(SQLException e) {
            System.err.println("Erro ao executar operação: " + e.getMessage());
            throw e;
        }
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM usuario";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                users.add(user);
            }
            return users;
        }catch(SQLException e) {
            System.err.println("Erro ao buscar usuarios: " + e.getMessage());
            throw e;
        }
    }

    public User findUser(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                User user = new User();
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                return user;
            }else {
                return null;
            }
        }catch(SQLException e) {
            System.err.println("Erro ao buscar usuario: " + e.getMessage());
            throw e;
        }
    }
}
