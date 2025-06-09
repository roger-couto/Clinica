package br.csi.controller;

import br.csi.model.Paciente;
import br.csi.model.Psicologo;
import br.csi.service.PacienteService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/pacientes")
public class PacienteController extends HttpServlet {
    private final PacienteService service = new PacienteService();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Psicologo psicologo = (Psicologo) session.getAttribute("psicologoLogado");

        if(psicologo == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String id = req.getParameter("id");
        String action = req.getParameter("action");

        try {
            if("new".equals(action)) {
                // Passa o ID do psicólogo para o formulário
                req.setAttribute("psicologoId", psicologo.getId());
                RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
                rd.forward(req, resp);
            }
            else if(id != null && "delete".equals(action)) {
                service.excluir(Integer.parseInt(id));
                resp.sendRedirect(req.getContextPath() + "/pacientes");
            }
            else if(id != null) {
                Paciente paciente = service.buscarPorId(Integer.parseInt(id));
                if(paciente != null && paciente.getPsicologoId() == psicologo.getId()) {
                    req.setAttribute("paciente", paciente);
                    RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
                    rd.forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/pacientes");
                }
            }
            else {
                // Lista apenas pacientes do psicólogo logado
                req.setAttribute("pacientes", service.listarPorPsicologo(psicologo.getId()));
                RequestDispatcher rd = req.getRequestDispatcher("/listar.jsp");
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("mensagemErro", "Erro: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Psicologo psicologo = (Psicologo) session.getAttribute("psicologoLogado");

        if(psicologo == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String id = req.getParameter("id");

        try {
            Paciente paciente = new Paciente();
            paciente.setNome(req.getParameter("nome"));
            paciente.setEmail(req.getParameter("email"));
            paciente.setDataNascimento(dateFormat.parse(req.getParameter("dataNascimento")));
            paciente.setStatus(req.getParameter("status"));
            paciente.setPsicologoId(psicologo.getId());

            if(id != null && !id.isEmpty()) {
                paciente.setId(Integer.parseInt(id));
                service.atualizar(paciente);
            } else {
                service.cadastrar(paciente);
            }

            resp.sendRedirect(req.getContextPath() + "/pacientes");
        } catch (ParseException e) {
            req.setAttribute("mensagemErro", "Data de nascimento inválida");
            RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("mensagemErro", "Erro ao salvar paciente: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
            rd.forward(req, resp);
        }
    }
}