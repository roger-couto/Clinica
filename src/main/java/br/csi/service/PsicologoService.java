package br.csi.service;

import br.csi.dao.ConectarBancoDados;  // Importação adicionada
import br.csi.dao.PsicologoDAO;
import br.csi.model.Psicologo;
import java.sql.Connection;            // Importação do JDBC
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PsicologoService {
    private final PsicologoDAO dao;

    public PsicologoService() {
        this.dao = new PsicologoDAO();
    }

    public Psicologo autenticar(String email, String senha) {
        Psicologo psicologo = dao.buscarPorEmail(email);
        if (psicologo != null && psicologo.getSenha().equals(senha) && psicologo.isAtivo()) {
            return psicologo;
        }
        return null;
    }

    public void cadastrar(Psicologo psicologo) throws Exception {
        if (psicologo.getNome() == null || psicologo.getNome().trim().isEmpty()) {
            throw new Exception("O nome é obrigatório");
        }
        if (psicologo.getEmail() == null || psicologo.getEmail().trim().isEmpty()) {
            throw new Exception("O email é obrigatório");
        }
        if (psicologo.getSenha() == null || psicologo.getSenha().trim().isEmpty()) {
            throw new Exception("A senha é obrigatória");
        }

        if (dao.buscarPorEmail(psicologo.getEmail()) != null) {
            throw new Exception("Email já cadastrado");
        }

        try {
            dao.adicionar(psicologo);
        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar no banco de dados: " + e.getMessage());
        }
    }

    public List<Psicologo> listar() {
        return dao.listar();
    }

    public Psicologo buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public void atualizar(Psicologo psicologo) {
        dao.atualizar(psicologo);
    }

    public void excluir(int id) {
        dao.excluir(id);
    }
}