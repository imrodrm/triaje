<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	    <!-- CSS BOOTSTRAP 4 -->
    	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
    	
    	
    	<!-- JS BOOTSTRAP -->
    	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    	<script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

	    <!-- ARCHIVOS CSS PROPIOS -->
	    <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet" />
	    
	    <!-- ARCHIVOS JS PROPIOS -->
	    <script src="${pageContext.request.contextPath}/resources/js/cleanpwd.js"></script>

    	<title>Iniciar sesión - Triaje - Salud</title>
  	</head>
	<body>
		<!--BARRA DE NAVEGACIÓN SUPERIOR-->
    	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
      		<!--logo de la aplicación-->
      		<a href="${pageContext.request.contextPath}" class="navbar-brand">
        		<img class="d-inline-block align-text-bottom" alt="Logo de la aplicación" src="${pageContext.request.contextPath}/resources/img/logo.png">
      		</a>
  		</nav>
		<div class="col-sm-10 col-md-6 col-lg-4 todo">
  			<div class="titulo"><h3>Iniciar sesión</h3></div>
			<div class="card formularioContainer">
				<form:form id="login" action="login" modelAttribute="login" method="POST">
					<c:if test="${erroresLogin!= null }">
						<div class="error">
							<p>${erroresLogin}</p>
						</div>
					</c:if>
					<label class="lblTexto" for="username">Usuario:</label><br>
					<form:input id="usuario" class="inputTexto" name="username" type="text" path="usuario" required="required"/><br>
					<label class="lblTexto" for="pwd">Contraseña:</label><br>
					<form:input id="contrasenia" class="inputTexto" name="pwd" type="password" path="contrasenia" required="required"/><br><br>
					<input type="submit" value="Iniciar sesión" class="save"/>
				</form:form>
			</div>
		</div>
		<!-- PIE DE PÁGINA -->
    	<footer class="text-center bg-primary text-white">
    		<div class="container">
      			<p class="mx-1 py-3">
       				2021. Aplicación web para la realización de evaluaciones de triajes en
       				una sala de urgencias.
       			</p>
    		</div>
    	</footer>
	</body>
</html>