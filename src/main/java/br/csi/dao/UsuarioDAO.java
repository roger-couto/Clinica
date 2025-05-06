package br.csi.dao;

import br.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    public String alterar(Usuario usuario) {
        try {
            Connection conn = ConectarBancoDados.ConectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE usuario SET email = ?, senha = ?, ativo = ? WHERE id_usuario = ?"
            );

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setBoolean(3, usuario.isAtivo());
            stmt.setInt(4, usuario.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar usuario");
        }

        return "Usuario alterado com sucesso";
    }



   public String excluir(int id_usuario) {
        try {
            Connection conn = ConectarBancoDados.ConectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM usuario WHERE id_usuario = ?"
            );

            stmt.setInt(1, id_usuario);

            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return "Nenhum usuario excluido";
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir usuario");
        }

        return "Usuario excluido com sucesso";
    }

    public String inserir(Usuario usuario) {
        try {
            Connection conn = ConectarBancoDados.ConectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO usuario (email, senha, ativo) VALUES (?, ?, ?)"
            );

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setBoolean(3, usuario.isAtivo());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir usuario");
        }

        return "Inserido com sucesso";
    }

    public ArrayList<Usuario> getUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.ConectarBancoPostgres();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from usuario");
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario")); // <- alterado aqui
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setAtivo(rs.getBoolean("ativo"));

                usuarios.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não carregou");
            ex.printStackTrace();
        }

        return usuarios;
    }


}
