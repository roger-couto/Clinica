package br.csi.controller;

import br.csi.model.Consulta;
import br.csi.model.Psicologo;
import br.csi.service.ConsultaService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@WebServlet("/consulta")
public class ConsultaController extends HttpServlet {

    private final ConsultaService service = new ConsultaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Psicologo psicologo = (Psicologo) session.getAttribute("psicologoLogado");

        if (psicologo == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String idPaciente = req.getParameter("idPaciente");
        String action = req.getParameter("action");

        try {
            if ("form".equals(action) && idPaciente != null) {
                req.setAttribute("idPaciente", Integer.parseInt(idPaciente));
                RequestDispatcher rd = req.getRequestDispatcher("/consulta.jsp");
                rd.forward(req, resp);
            } else if ("listar".equals(action)) {
                req.setAttribute("consultas", service.listarPorPsicologo(psicologo.getId()));
                RequestDispatcher rd = req.getRequestDispatcher("/listar-consultas.jsp");
                rd.forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/pacientes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensagemErro", "Erro: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Psicologo psicologo = (Psicologo) session.getAttribute("psicologoLogado");

        if (psicologo == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String action = req.getParameter("action");

        try {
            if ("delete".equals(action)) {
                int idConsulta = Integer.parseInt(req.getParameter("idConsulta"));
                service.excluir(idConsulta);
                resp.sendRedirect(req.getContextPath() + "/consulta?action=listar");
            } else {
                int pacienteId = Integer.parseInt(req.getParameter("pacienteId"));
                String dataHoraStr = req.getParameter("dataHora");
                int duracaoMinutos = Integer.parseInt(req.getParameter("duracaoMinutos"));
                String observacoes = req.getParameter("observacoes");

                LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr); // formato ISO (yyyy-MM-ddTHH:mm)

                Consulta consulta = new Consulta();
                consulta.setPacienteId(pacienteId);
                consulta.setPsicologoId(psicologo.getId());
                consulta.setDataHora(dataHora);
                consulta.setDuracaoMinutos(duracaoMinutos);
                consulta.setObservacoes(observacoes);

                service.cadastrar(consulta);

                resp.sendRedirect(req.getContextPath() + "/pacientes");
            }
        } catch (DateTimeParseException e) {
            req.setAttribute("mensagemErro", "Data e hora em formato inválido");
            RequestDispatcher rd = req.getRequestDispatcher("/consulta.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("mensagemErro", "Erro ao processar consulta: " + e.getMessage());
            RequestDispatcher rd = req.getRequestDispatcher("/consulta.jsp");
            rd.forward(req, resp);
        }
    }
}
