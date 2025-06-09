package br.csi.dao;

import br.csi.model.Psicologo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PsicologoDAO {

    public PsicologoDAO() {

    }

    public void adicionar(Psicologo psicologo) throws SQLException {
        String sql = "INSERT INTO psicologo (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = new ConectarBancoDados().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            System.out.println("Executando INSERT para: " + psicologo.getEmail()); // Log

            stmt.setString(1, psicologo.getNome());
            stmt.setString(2, psicologo.getEmail());
            stmt.setString(3, psicologo.getSenha());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar usuário, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    psicologo.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO NO INSERT: " + e.getMessage());
            throw e;
        }
    }

    public Psicologo buscarPorEmail(String email) {
        String sql = "SELECT * FROM psicologo WHERE email = ?";
        try (Connection conn = new ConectarBancoDados().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Psicologo(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBoolean("ativo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Psicologo> listar() {
        List<Psicologo> psicologos = new ArrayList<>();
        String sql = "SELECT * FROM psicologo";

        try (Connection conn = new ConectarBancoDados().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Psicologo psicologo = new Psicologo(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBoolean("ativo")
                );
                psicologos.add(psicologo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return psicologos;
    }

    public Psicologo buscarPorId(int id) {
        String sql = "SELECT * FROM psicologo WHERE id = ?";
        try (Connection conn = new ConectarBancoDados().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Psicologo(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBoolean("ativo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizar(Psicologo psicologo) {
        String sql = "UPDATE psicologo SET nome = ?, email = ?, senha = ?, ativo = ? WHERE id = ?";
        try (Connection conn = new ConectarBancoDados().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, psicologo.getNome());
            stmt.setString(2, psicologo.getEmail());
            stmt.setString(3, psicologo.getSenha());
            stmt.setBoolean(4, psicologo.isAtivo());
            stmt.setInt(5, psicologo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM psicologo WHERE id = ?";
        try (Connection conn = new ConectarBancoDados().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}