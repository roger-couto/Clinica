<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css?v=${System.currentTimeMillis()}">
</head>
<body>
<div class="login-container">
  <h2>Login</h2>
  <% if(request.getAttribute("mensagemErro") != null) { %>
  <div class="error-message">${mensagemErro}</div>
  <% } %>
  <form action="login" method="post">
    <label for="email">E-mail:</label>
    <input type="text" id="email" name="email" required>
    <label for="senha">Senha:</label>
    <input type="password" id="senha" name="senha" required>
    <button type="submit">Entrar</button>
  </form>
  <div class="register-link">
    <a href="cadastro.jsp">Novo Usuário</a>
  </div>
</div>
</body>
</html>