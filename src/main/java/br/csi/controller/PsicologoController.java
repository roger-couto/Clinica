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
import java.util.List;

@WebServlet("/psicologos")
public class PsicologoController extends HttpServlet {
    private final PsicologoService service = new PsicologoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String action = req.getParameter("action");

        if ("logout".equals(action)) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        if (id != null) {
            Psicologo psicologo = service.buscarPorId(Integer.parseInt(id));
            req.setAttribute("psicologo", psicologo);
            RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
            rd.forward(req, resp);
        } else {
            List<Psicologo> psicologos = service.listar();
            req.setAttribute("psicologos", psicologos);
            RequestDispatcher rd = req.getRequestDispatcher("/listar.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        HttpSession session = req.getSession();

        Psicologo psicologo = new Psicologo();
        psicologo.setNome(req.getParameter("nome"));
        psicologo.setEmail(req.getParameter("email"));
        psicologo.setSenha(req.getParameter("senha"));

        try {
            if (id != null && !id.isEmpty()) {
                psicologo.setId(Integer.parseInt(id));
                service.atualizar(psicologo);
                session.setAttribute("mensagemSucesso", "Psicólogo atualizado com sucesso!");
                resp.sendRedirect(req.getContextPath() + "/psicologos");
            } else {
                service.cadastrar(psicologo);
                session.setAttribute("mensagemSucesso", "Cadastro realizado com sucesso! Faça login");

                String redirectPath = req.getContextPath() + "/index.jsp";
                System.out.println("Redirecionando para: " + redirectPath); // Log para debug
                resp.sendRedirect(redirectPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensagemErro", "Erro: " + e.getMessage());

            RequestDispatcher rd = req.getRequestDispatcher("/cadastro.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            service.excluir(Integer.parseInt(id));
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}