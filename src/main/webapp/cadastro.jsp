<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Cadastro de Psicólogo</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">

</head>
<body>
<div class="login-container">
  <h2>Criar Conta</h2>

  <% if(request.getAttribute("mensagemErro") != null) { %>
  <div class="error-message">${mensagemErro}</div>
  <% } %>

  <form action="psicologos" method="post">
    <label for="nome">Nome Completo:</label>
    <input type="text" id="nome" name="nome" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>

    <label for="senha">Senha:</label>
    <input type="password" id="senha" name="senha" required>

    <button type="submit">Cadastrar</button>
  </form>
  <div class="register-link">
    <a href="${pageContext.request.contextPath}/index.jsp">Já tem conta? Faça login</a>
  </div>
</div>
</body>
</html>