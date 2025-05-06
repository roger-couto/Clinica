package br.csi.controller;


import br.csi.model.Usuario;
import br.csi.service.LoginService;
import br.csi.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        System.out.println("E-mail"+email+"-senha"+senha);


        RequestDispatcher dispatcher;
        if(new LoginService().autenticar(email, senha)) {
            dispatcher = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
            dispatcher.forward(req, resp);
        }else{
            dispatcher = req.getRequestDispatcher("index.jsp");
            req.setAttribute("msg", "Login ou senha incorretos");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet");

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Servlet LoginServlet</h1>");

        for(Usuario u : new UsuarioService().getUsuarios()){
            out.println("<h2>"+u.getEmail()+"</h2>");


        }

        out.println("</body>");
        out.println("</html>");

    }
}
