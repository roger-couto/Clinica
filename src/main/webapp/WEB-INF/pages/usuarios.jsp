<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="br.csi.model.Usuario" %>
<%@ page import="br.csi.dao.UsuarioDAO" %>
<html>
<head>
    <title>Usuários - Dashboard</title>
</head>
<body>
<h1>Dashboard - Usuários</h1>

<h2>Adicionar Usuário</h2>
<form action="usuario-servlet" method="post">
    <input type="hidden" name="acao" value="inserir" />
    Email: <input type="email" name="email" required /><br/>
    Senha: <input type="password" name="senha" required /><br/>
    <input type="submit" value="Adicionar Usuário" />
</form>

<h2>Lista de Usuários</h2>
<table >
    <tr>
        <th>ID</th><th>Email</th><th>Senha</th><th>Ações</th>
    </tr>
    <%
        UsuarioDAO dao = new UsuarioDAO();
        ArrayList<Usuario> usuarios = dao.getUsuarios();
        for (Usuario u : usuarios) {
    %>
    <tr>
        <td><%= u.getIdUsuario() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getSenha() %></td>
        <td>
            <form action="usuario-servlet" method="post" style="display:inline;">
                <input type="hidden" name="acao" value="excluir" />
                <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>" />
                <input type="submit" value="Excluir" />
            </form>

            <form action="pages/editar-usuario.jsp" method="get" style="display:inline;">
                <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>" />
                <input type="submit" value="Editar" />
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
