package br.csi.controller;

import br.csi.model.Psicologo;
import br.csi.service.PsicologoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final PsicologoService service = new PsicologoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Psicologo psicologo = service.autenticar(email, senha);

        if(psicologo != null) {
            HttpSession session = req.getSession();
            session.setAttribute("psicologoLogado", psicologo);

            resp.sendRedirect(req.getContextPath() + "/pacientes");
        } else {
            req.setAttribute("mensagemErro", "E-mail ou senha incorretos");
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
        }
    }
}
