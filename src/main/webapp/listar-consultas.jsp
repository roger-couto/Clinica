<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Minhas Consultas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/listar.css">
</head>
<body>
<div class="container">
    <h1>Minhas Consultas</h1>

    <a href="pacientes" class="add-btn">Meus Pacientes</a>
    <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Sair</a>

    <c:choose>
        <c:when test="${empty consultas}">
            <p class="no-data">Você ainda não tem consultas marcadas.</p>
        </c:when>
        <c:otherwise>
            <table class="consultas-table">
                <thead>
                <tr>
                    <th>Data</th>
                    <th>Hora</th>
                    <th>Duração</th>
                    <th>Observações</th>
                    <th>Paciente</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${consultas}" var="consulta">
                    <tr>
                        <td><fmt:formatDate value="${consulta.dataHoraAsDate}" pattern="dd/MM/yyyy"/></td>
                        <td><fmt:formatDate value="${consulta.dataHoraAsDate}" pattern="HH:mm"/></td>
                        <td>${consulta.duracaoMinutos} min</td>
                        <td class="observacoes">${empty consulta.observacoes ? '-' : consulta.observacoes}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty consulta.paciente}">
                                    ${consulta.paciente.nome}
                                </c:when>
                                <c:otherwise>
                                    <span class="paciente-nao-encontrado">Paciente não encontrado</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <form method="post" action="consulta" onsubmit="return confirm('Tem certeza que deseja excluir esta consulta?');">
                                <input type="hidden" name="action" value="delete" />
                                <input type="hidden" name="idConsulta" value="${consulta.id}" />
                                <button type="submit" class="delete-btn">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
