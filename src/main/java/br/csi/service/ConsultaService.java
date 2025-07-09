package br.csi.service;

import br.csi.dao.ConsultaDAO;
import br.csi.model.Consulta;

import java.util.List;

public class ConsultaService {
    private final ConsultaDAO dao;

    public ConsultaService() {
        this.dao = new ConsultaDAO();
    }

    public void cadastrar(Consulta consulta) {
        dao.adicionar(consulta);
    }

    public List<Consulta> listarPorPaciente(int pacienteId) {
        return dao.listarPorPaciente(pacienteId);
    }

    public List<Consulta> listarPorPsicologo(int psicologoId) {
        return dao.listarPorPsicologo(psicologoId);
    }

    public void excluir(int idConsulta) {
        dao.excluir(idConsulta);
    }
}

