package br.csi.service;
import br.csi.dao.ConectarBancoDados;
import br.csi.dao.PacienteDAO;
import br.csi.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    private final PacienteDAO dao;

    public PacienteService() {
        this.dao = new PacienteDAO();
    }

    public void cadastrar(Paciente paciente) {
        dao.adicionar(paciente);
    }

    public List<Paciente> listar() {
        return dao.listar();
    }

    public List<Paciente> listarPorPsicologo(int psicologoId) {
        return dao.listarPorPsicologo(psicologoId);
    }

    public Paciente buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public void atualizar(Paciente paciente) {
        dao.atualizar(paciente);
    }

    public void excluir(int id) {
        dao.excluir(id);
    }
}