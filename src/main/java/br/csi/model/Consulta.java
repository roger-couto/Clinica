package br.csi.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Consulta {
    private int id;
    private LocalDateTime dataHora;
    private int duracaoMinutos;
    private String observacoes;
    private int pacienteId;
    private int psicologoId;

    private Paciente paciente;

    public Consulta() {}

    public Consulta(int id, LocalDateTime dataHora, int duracaoMinutos, String observacoes, int pacienteId, int psicologoId) {
        this.id = id;
        this.dataHora = dataHora;
        this.duracaoMinutos = duracaoMinutos;
        this.observacoes = observacoes;
        this.pacienteId = pacienteId;
        this.psicologoId = psicologoId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }

    public int getPsicologoId() { return psicologoId; }
    public void setPsicologoId(int psicologoId) { this.psicologoId = psicologoId; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }


    public Date getDataHoraAsDate() {
        if (this.dataHora == null) return null;
        return Date.from(this.dataHora.atZone(ZoneId.systemDefault()).toInstant());
    }
}
