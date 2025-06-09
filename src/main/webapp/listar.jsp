<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
  <title>Meus Pacientes</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/listar.css?v=${System.currentTimeMillis()}">
</head>
<body>
<div class="container">
  <h1>Meus Pacientes</h1>

  <a href="pacientes?action=new" class="add-btn">Novo Paciente</a>
  <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Sair</a>

  <table>
    <thead>
    <tr>
      <th>Nome</th>
      <th>E-mail</th>
      <th>Data Nascimento</th>
      <th>Status</th>
      <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pacientes}" var="paciente">
      <tr>
        <td>${paciente.nome}</td>
        <td>${paciente.email}</td>
        <td>
          <fmt:formatDate value="${paciente.dataNascimento}" pattern="dd-MM-yyyy"/>
        </td>
        <td>${paciente.status}</td>
        <td class="actions">
          <a href="pacientes?id=${paciente.id}" class="edit-btn">Editar</a>
          <a href="pacientes?id=${paciente.id}&action=delete"
             onclick="return confirm('Tem certeza que deseja excluir?')" class="delete-btn">Excluir</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>