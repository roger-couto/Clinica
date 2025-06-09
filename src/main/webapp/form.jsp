<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <title><c:choose>
    <c:when test="${paciente != null}">Editar Paciente</c:when>
    <c:otherwise>Novo3 Paciente</c:otherwise>
  </c:choose></title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css?v=${System.currentTimeMillis()}">
</head>
<body>
<div class="form-container">
  <h2>
    <c:choose>
      <c:when test="${paciente != null}">Editar Paciente</c:when>
      <c:otherwise>Novo Paciente</c:otherwise>
    </c:choose>
  </h2>

  <c:if test="${not empty mensagemErro}">
    <div class="error-message">${mensagemErro}</div>
  </c:if>

  <form action="pacientes" method="post">

    <input type="hidden" name="id" value="<c:out value='${paciente.id}'/>" />

    <input type="hidden" name="psicologoId" value="${psicologoId}">

    <label for="nome">Nome Completo:</label>
    <input type="text" id="nome" name="nome" required value="<c:out value='${paciente.nome}'/>" />

    <label for="email">E-mail:</label>
    <input type="email" id="email" name="email" required value="<c:out value='${paciente.email}'/>" />

    <label for="dataNascimento">Data de Nascimento:</label>
    <input type="date" id="dataNascimento" name="dataNascimento" required
           value="<c:if test='${paciente != null}'><fmt:formatDate value='${paciente.dataNascimento}' pattern='yyyy-MM-dd'/></c:if>" />

    <label for="status">Status:</label>
    <select id="status" name="status" required>
      <option value="Ativo" ${paciente.status == 'Ativo' ? 'selected' : ''}>Ativo</option>
      <option value="Em avaliacao" ${paciente.status == 'Em avaliacao' ? 'selected' : ''}>Em avaliação</option>
      <option value="Encerrado" ${paciente.status == 'Encerrado' ? 'selected' : ''}>Encerrado</option>
      <option value="Pausado" ${paciente.status == 'Pausado' ? 'selected' : ''}>Pausado</option>
    </select>

    <button type="submit">
      <c:choose>
        <c:when test="${paciente != null}">Salvar Alterações</c:when>
        <c:otherwise>Cadastrar Paciente</c:otherwise>
      </c:choose>
    </button>
  </form>

  <a href="pacientes">Voltar para Lista</a>
</div>
</body>
</html>
