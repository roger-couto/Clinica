package br.csi.util;

import br.csi.dao.ClienteDAO;
import br.csi.dao.UsuarioDAO;
import br.csi.model.Cliente;
import br.csi.model.Usuario;

import java.util.ArrayList;


public class Testes {

    public static void main(String[] args) {

        /*try {

            Class.forName("org.postgresql.Driver");
            System.out.println("Driver carregado");

            String url = "jdbc:postgresql://localhost:5432/poow1";
            String user = "postgres";
            String senha = "1234";
            Connection conn =
                    DriverManager.getConnection(url, user, senha);
            System.out.println("Conectado com sucesso");


            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from usuario");
            while (rs.next()) {
                 System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("email"));
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setAtivo(rs.getBoolean("ativo"));

                imprimir(u);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Drive não carregou");
            ex.printStackTrace();
        }
*/

        // === USUARIO ===
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setEmail("usuario1@email.com");
        usuario.setSenha("novaSenha123");
        usuario.setAtivo(true);

        // Descomente o que quiser testar:
//        System.out.println(usuarioDAO.inserir(usuario));
//        System.out.println(usuarioDAO.alterar(usuario));
//        System.out.println(usuarioDAO.excluir(3));

        ArrayList<Usuario> usuarios = usuarioDAO.getUsuarios();
        for (Usuario u : usuarios) {
            imprimir(u);
        }

        // === CLIENTE ===
        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNome("Cliente Teste");
        cliente.setTelefone("51988887777");
        cliente.setEndereco("Rua Teste, 123");
        cliente.setCpf("11122233344");
        cliente.setIdUsuario(1); // o id_usuario precisa existir!

        // Descomente o que quiser testar:
//        System.out.println(clienteDAO.inserir(cliente));
//        System.out.println(clienteDAO.alterar(cliente));
//        System.out.println(clienteDAO.excluir(2));

        ArrayList<Cliente> clientes = clienteDAO.getClientes();
        for (Cliente c : clientes) {
            imprimir(c);
        }
    }

    public static void imprimir(Usuario usuario) {
        System.out.println("Usuario ID: " + usuario.getId() +
                ", Email: " + usuario.getEmail() +
                ", Senha: " + usuario.getSenha() +
                ", Ativo: " + usuario.isAtivo());
    }

    public static void imprimir(Cliente cliente) {
        System.out.println("Cliente ID: " + cliente.getIdCliente() +
                ", Nome: " + cliente.getNome() +
                ", Telefone: " + cliente.getTelefone() +
                ", Endereco: " + cliente.getEndereco() +
                ", CPF: " + cliente.getCpf() +
                ", ID Usuario: " + cliente.getIdUsuario());
    }
}


