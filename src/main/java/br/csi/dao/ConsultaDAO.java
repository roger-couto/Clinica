package br.csi.dao;

import br.csi.model.Consulta;
import br.csi.model.Paciente;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {
    private final Connection conexao;

    public ConsultaDAO() {
        this.conexao = new ConectarBancoDados().getConexao();
    }

    public void adicionar(Consulta consulta) {
        String sql = "INSERT INTO consulta (data_hora, duracao_minutos, observacoes, paciente_id, psicologo_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(consulta.getDataHora()));
            stmt.setInt(2, consulta.getDuracaoMinutos());
            stmt.setString(3, consulta.getObservacoes());
            stmt.setInt(4, consulta.getPacienteId());
            stmt.setInt(5, consulta.getPsicologoId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                consulta.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar consulta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM consulta WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir consulta: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir consulta", e);
        }
    }

    public List<Consulta> listarPorPaciente(int pacienteId) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE paciente_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, pacienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta(
                        rs.getInt("id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        rs.getInt("duracao_minutos"),
                        rs.getString("observacoes"),
                        rs.getInt("paciente_id"),
                        rs.getInt("psicologo_id")
                );
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas por paciente: " + e.getMessage());
            e.printStackTrace();
        }
        return consultas;
    }

    public List<Consulta> listarPorPsicologo(int psicologoId) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT c.*, p.nome as paciente_nome FROM consulta c " +
                "LEFT JOIN paciente p ON c.paciente_id = p.id " +
                "WHERE c.psicologo_id = ?";

        try (Connection conexao = new ConectarBancoDados().getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, psicologoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta consulta = new Consulta(
                        rs.getInt("id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        rs.getInt("duracao_minutos"),
                        rs.getString("observacoes"),
                        rs.getInt("paciente_id"),
                        rs.getInt("psicologo_id")
                );

                String nomePaciente = rs.getString("paciente_nome");
                if (rs.getInt("paciente_id") > 0 && nomePaciente != null && !nomePaciente.isBlank()) {
                    Paciente paciente = new Paciente();
                    paciente.setId(rs.getInt("paciente_id"));
                    paciente.setNome(nomePaciente);
                    consulta.setPaciente(paciente);
                }

                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas por psicólogo: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar consultas", e);
        }
        return consultas;
    }
}
