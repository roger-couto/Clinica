package br.csi.dao;

import br.csi.model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    private Connection conexao;

    public PacienteDAO() {
        this.conexao = new ConectarBancoDados().getConexao();
    }

    public void adicionar(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, email, data_nascimento, status, psicologo_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEmail());
            stmt.setDate(3, new java.sql.Date(paciente.getDataNascimento().getTime()));
            stmt.setString(4, paciente.getStatus());
            stmt.setInt(5, paciente.getPsicologoId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                paciente.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Paciente> listarPorPsicologo(int psicologoId) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente WHERE psicologo_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, psicologoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        new java.util.Date(rs.getDate("data_nascimento").getTime()),
                        rs.getString("status"),
                        rs.getInt("psicologo_id")
                );
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes por psicologo: " + e.getMessage());
            e.printStackTrace();
        }
        return pacientes;
    }

    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        new java.util.Date(rs.getDate("data_nascimento").getTime()), // Conversão para java.util.Date
                        rs.getString("status"),
                        rs.getInt("psicologo_id")
                );
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
            e.printStackTrace();
        }
        return pacientes;
    }

    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM paciente WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        new java.util.Date(rs.getDate("data_nascimento").getTime()), // Conversão para java.util.Date
                        rs.getString("status"),
                        rs.getInt("psicologo_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar paciente por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void atualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET nome = ?, email = ?, data_nascimento = ?, status = ?, psicologo_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEmail());
            stmt.setDate(3, new java.sql.Date(paciente.getDataNascimento().getTime()));
            stmt.setString(4, paciente.getStatus());
            stmt.setInt(5, paciente.getPsicologoId());
            stmt.setInt(6, paciente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM paciente WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}