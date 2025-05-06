package br.csi.model;

public class Cliente {
    private int idCliente;
    private String nome;
    private String telefone;
    private String endereco;
    private String cpf;
    private int idUsuario;

    // Construtor padrão
    public Cliente() {}

    // Construtor completo
    public Cliente(int idCliente, String nome, String telefone, String endereco, String cpf, int idUsuario) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.idUsuario = idUsuario;
    }

    // Construtor sem idCliente (útil para inserção)
    public Cliente(String nome, String telefone, String endereco, String cpf, int idUsuario) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
