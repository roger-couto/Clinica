<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Marcar Consulta</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css?v=${System.currentTimeMillis()}">
</head>
<body>
<div class="form-container">
    <h2>Marcar Consulta</h2>

    <form action="consulta" method="post">
        <input type="hidden" name="pacienteId" value="${param.idPaciente}" />
        <input type="hidden" name="psicologoId" value="${sessionScope.psicologoLogado.id}" />

        <label for="dataHora">Data e Hora:</label>
        <input type="datetime-local" id="dataHora" name="dataHora" required />

        <label for="duracaoMinutos">Duração (minutos):</label>
        <input type="number" id="duracaoMinutos" name="duracaoMinutos" min="1" required />

        <label for="observacoes">Observações:</label>
        <textarea id="observacoes" name="observacoes" rows="4" placeholder="Ex: Sessão introdutória, feedback inicial..."></textarea>

        <button type="submit">Confirmar Consulta</button>
    </form>

    <a href="pacientes">Voltar para Lista</a>
</div>
</body>
</html>
