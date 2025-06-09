package br.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBancoDados {
    public static Connection getConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/clinica";
            String user = "postgres";
            String password = "";
            return DriverManager.getConnection(url, user, null);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados:");
            System.err.println("URL: jdbc:postgresql://localhost:5432/clinica");
            System.err.println("Usuário: postgres (sem senha)");
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }
}