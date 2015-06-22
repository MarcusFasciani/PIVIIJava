<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
	<body>
		<input type="hidden" name="mensagem" id="mensagem">${mensagem}</input>
		<div id="formulario">
			<form action="MedicoServlet" method="post">
				<input type="hidden" name="_id" id="_id" value="${_id}"/>
				<label>Nome</label>
				<input type="text" name="nome" id="nome" value="${nome}"/>
				<label>Salario</label>
				<input type="text" name="salario" id="salario" value="${salario}" />
				<label>Especializacao</label>
				<input type="text" name="especializacao" id="especializacao" value="${especializacao}" />
				<input type="submit" id="buscar" value="Salvar" />
			</form>
		</div>
	
	<div id="lista">
		<table>
			<tr>
				<th>Codigo</th>
				<th>Nome</th>
				<th>Salario</th>
				<th>Especializacao</th>
			</tr>
			<c:forEach items="${medicos}" var="medico">
			    <tr>      
			        <td>${medico._id}</td>
			        <td>${medico.nome}</td>
			        <td>${medico.salario}</td>
			        <td>${medico.especializacao}</td>
			    </tr>
			</c:forEach>
		</table>
	</div>

	</body>
</html>
