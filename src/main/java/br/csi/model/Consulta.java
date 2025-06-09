package br.csi.model;

import java.util.Date;

public class Consulta {
    private int id;
    private Date dataHora;
    private int duracaoMinutos;
    private String observacoes;
    private int pacienteId;
    private int psicologoId;

    public Consulta() {}

    public Consulta(Date dataHora, int pacienteId, int psicologoId) {
        this.dataHora = dataHora;
        this.pacienteId = pacienteId;
        this.psicologoId = psicologoId;
        this.duracaoMinutos = 50;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getDataHora() { return dataHora; }
    public void setDataHora(Date dataHora) { this.dataHora = dataHora; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }
    public int getPsicologoId() { return psicologoId; }
    public void setPsicologoId(int psicologoId) { this.psicologoId = psicologoId; }
}