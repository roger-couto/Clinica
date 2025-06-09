package br.csi.model;

import java.util.Date;

public class Paciente {
    private int id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private String status;
    private int psicologoId;

    public Paciente() {}

    public Paciente(String nome, String email, Date dataNascimento, int psicologoId) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.psicologoId = psicologoId;
        this.status = "Ativo";
    }

    public Paciente(int id, String nome, String email, Date dataNascimento, String status, int psicologoId) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.status = status;
        this.psicologoId = psicologoId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getPsicologoId() { return psicologoId; }
    public void setPsicologoId(int psicologoId) { this.psicologoId = psicologoId; }
}