package br.csi.model;

public class Usuario {


    public Usuario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    private int id;

    private String nome;

    private String email;

    private String senha;

    public Usuario(int id, String nome, String email, String senha, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    private boolean ativo;


    public int getIdUsuario() {
        return this.id;
    }

    public void setIdUsuario(int idUsuario) {
        this.id = idUsuario;
    }

}
