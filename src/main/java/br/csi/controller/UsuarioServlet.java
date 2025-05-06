package br.csi.controller;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;
import br.csi.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/usuario-servlet")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        UsuarioDAO dao = new UsuarioDAO();

        if (acao != null) {
            switch (acao) {
                case "inserir":
                    Usuario novoUsuario = new Usuario();
                    novoUsuario.setEmail(request.getParameter("email"));
                    novoUsuario.setSenha(request.getParameter("senha"));
                    dao.inserir(novoUsuario);
                    break;

                case "alterar":
                    Usuario usuarioEditado = new Usuario();
                    usuarioEditado.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                    usuarioEditado.setEmail(request.getParameter("email"));
                    usuarioEditado.setSenha(request.getParameter("senha"));
                    dao.alterar(usuarioEditado);
                    break;

                case "excluir":
                    int idExcluir = Integer.parseInt(request.getParameter("idUsuario"));
                    dao.excluir(idExcluir);
                    break;
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/usuarios.jsp");

        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Usuario> usuarios= new UsuarioService().getUsuarios();

        request.setAttribute("usuarios", usuarios);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/usuarios.jsp");

        rd.forward(request, response);

    }
}
