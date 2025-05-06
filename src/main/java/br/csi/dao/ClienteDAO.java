package br.csi.dao;

import br.csi.model.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    public String inserir(Cliente cliente) {
        try {
            Connection conn = ConectarBancoDados.ConectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO cliente (nome, telefone, endereco, cpf, id_usuario) VALUES (?, ?, ?, ?, ?)"
            );

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCpf());
            stmt.setInt(5, cliente.getIdUsuario());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir cliente");
        }

        return "Cliente inserido com sucesso";
    }

    public String alterar(Cliente cliente) {
        try {
            Connection conn = ConectarBancoDados.ConectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE cliente SET nome = ?, telefone = ?, endereco = ?, cpf = ?, id_usuario = ? WHERE id_cliente = ?"
            );

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCpf());
            stmt.setInt(5, cliente.getIdUsuario());
            stmt.setInt(6, cliente.getIdCliente());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar cliente");
        }

        return "Cliente alterado com sucesso";
    }

    public String excluir(int idCliente) {
        try {
            Connection conn = ConectarBancoDados.ConectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM cliente WHERE id_cliente = ?"
            );

            stmt.setInt(1, idCliente);
            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return "Nenhum cliente excluído";
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir cliente");
        }

        return "Cliente excluído com sucesso";
    }

    public ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.ConectarBancoPostgres();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                c.setCpf(rs.getString("cpf"));
                c.setIdUsuario(rs.getInt("id_usuario"));

                clientes.add(c);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar clientes");
            e.printStackTrace();
        }

        return clientes;
    }

    public Cliente getClienteById(int idCliente) {
        Cliente cliente = null;

        try {
            Connection conn = ConectarBancoDados.ConectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");
            stmt.setInt(1, idCliente);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setIdUsuario(rs.getInt("id_usuario"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar cliente por ID");
            e.printStackTrace();
        }

        return cliente;
    }

}
