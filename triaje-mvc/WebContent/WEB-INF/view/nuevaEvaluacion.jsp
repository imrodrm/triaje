<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- CSS BOOTSTRAP 4 -->
    	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
    	<!-- JS BOOTSTRAP -->
    	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    	<script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    	
    	<!-- ARCHIVOS JS PROPIOS -->
    	<script src="${pageContext.request.contextPath}/resources/js/verEnfermedades.js"></script>
    	<script src="${pageContext.request.contextPath}/resources/js/limpiarInputsEvaluacion.js"></script>
	
	    <!-- ARCHIVOS CSS PROPIOS -->
	    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu_y_footer.css" />
	    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nuevaEvaluacion.css">

    	<title>Nueva Evaluación - Triaje</title>
	</head>
	<body>
		<!--BARRA DE NAVEGACIÓN SUPERIOR-->
    	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
      		<!--logo de la aplicación-->
      		<a href="${pageContext.request.contextPath}" class="navbar-brand">
	        	<img
          			class="d-inline-block align-text-bottom"
          			alt="Logo de la aplicación"
          			src="${pageContext.request.contextPath}/resources/img/logo.png"/>
      		</a>
      		<!--Este botón es a la hora de hacerlo responsivo. Es una aplicación web pensada para ordenadores, pero quizá en un futuro se pase a tablets y móviles, por ello lo dejamos-->
      		<button
	      		type="button"
      			class="navbar-toggler"
      			data-toggle="collapse"
      			data-target="#menu-principal"
      			aria-controls="menu-principal"
      			aria-expanded="false"
      			aria-label="Desplegar menú de navegación">
      			
      		<!--El span es el icono de hamburguesa para el menú responsivo-->
      			<span class="navbar-toggler-icon"></span>
    		</button>

	      	<div class="collapse navbar-collapse" id="menu-principal">
    	    	<ul class="navbar-nav">

        	  		<li class="nav-item dropdown">
            			<a
	            	  		href="#"
              				class="nav-link dropdown-toggle"
	              			data-toggle="dropdown"
              				role="button"
              				aria-haspopup="true"
              				aria-expanded="false">Evaluaciones<span class="caret"></span>
              			</a>
	              	
	            		<ul class="dropdown-menu bg-primary">
              				<li class="nav-item-dropdown">
		                		<a class="nav-link text-center" href="#">Listado de hoy</a>
              				</li>
              				<li class="nav-item-dropdown">
		                		<a class="nav-link text-center" href="#">Últimas 4 horas</a>
              				</li>
            			</ul>
          			</li>
          			<li class="nav-item">
		            	<a href="#" class="nav-link">Listado de pacientes</a>
    	    		</li>
        		</ul>

	        	<ul class="navbar-nav ml-md-auto">
          			<li class="nav-item">
	            		<a href="${pageContext.request.contextPath}/evaluacion/nueva" class="nav-link" id="nuevaEvaluacion">NUEVA EVALUACIÓN</a>
          			</li>
        		</ul>
        		<ul class="navbar-nav">
          			<li class="nav-item">
            			<a href="${pageContext.request.contextPath}/cerrarSesion" class="nav-link" id="nuevaEvaluacion">
              				<img src="${pageContext.request.contextPath}/resources/img/cerrarsesion.png" alt="Cerrar sesión" class="py-10 px-10 h-100" id="cerrarsesion">
              				<p class="txtCerrarSesion">Cerrar sesión</p>
            			</a>
          			</li>
        		</ul>
      		</div>
    	</nav>
    	<div id="myModal" class="modal">
    		<div class="modal-content">
    			<span id="close">&times;</span>
    			<p>Estas son las enfermedades del paciente</p>
    			<c:forEach items="${paciente.enfermedades }" var="enfermedad">
    				<p>${enfermedad.enfermedad.nombre }, con una temporalidad de: ${enfermedad.temporalidad}</p>
    			</c:forEach>
    		</div>
    	</div>
    		<div class="py-5 px-5 todo">
    			<form:form id="nuevaEvaluacionForm" class="py-2 px-2" action="nuevaForm" modelAttribute="evaluacion" method="POST">
    				<legend>Nueva evaluación</legend>
    				<hr>
    				<c:if test="${erroresEvaluacion!= null }">
    					<div class="error">
    						<c:forEach items="${erroresEvaluacion}" var="error">
    							<p>${error}</p>
    						</c:forEach>
						</div>
						<hr>
    				</c:if>
    				<div class="form-row">
    					<div class="form-group col-12 col-md-8 col-lg-6">
    						<label class="required bigLBL" for="evaluador">Personal evaluador:</label>
    						<form:input id="evaluador" type="text" class="inputTexto" name="evaluador" required="required" value="${logueado.id}" readonly="true" disabled="true" path="evaluador"/>
    					</div>
    					<div class="form-group col-12 col-sm-6 col-md-4 col-lg-3">
               				<label class="lblTexto" for="altura">Altura:</label>
               				<form:input id="altura" name="altura" class="short" type="number" placeholder="Ej: 175" path="altura"/>
            			</div>
            			<div class="form-group col-12 col-sm-6 col-md-4 col-lg-3">
                			<label class="lblTexto" for="peso">Peso:</label>
                			<form:input id="peso" name="peso" class="short" type="number" step="0.1" placeholder="Ej: 78" path="peso"/>
            			</div>
    				</div>
    				
    				<div class="form-row">
            			<div class="form-group col-12 col-md-8 col-lg-6">
                			<label class="required bigLBL" for="paciente">Paciente:</label>
                			<form:input id="paciente" name="paciente" class="inputTexto" type="text" required="required" placeholder="NSS del paciente" readonly="true" disabled="true" path="paciente" value="${paciente.NSS}"/>
                			<small id="enfermedades" class="pl-5 form-text text-muted">Vea las enfermedades del Paciente.</small>
            			</div>
            			<div class="form-group col-12 col-sm-6 col-md-4 col-lg-3">
                			<label class="lblTexto" for="temperatura">Temperatura:</label>
                			<form:input id="temperatura" name="temperatura" class="short" type="number" step="0.1" placeholder="Ej: 37.8" path="temperatura"/>
            			</div>
            			<div class="form-group col-12 col-sm-6 col-md-4 col-lg-3">
                			<label class="required lblTexto" for="prioridad">Prioridad:</label>
                			<form:select path="prioridad">
                				<form:option value="1">1</form:option>
                				<form:option value="2">2</form:option>
                				<form:option value="3">3</form:option>
                				<form:option value="4">4</form:option>
                				<form:option value="5">5</form:option>
                			</form:select>
            			</div>
          			</div>
          
					<div class="form-group">
            			<label class="required" for="dolencia">Dolencia:</label>
            			<form:textarea id="dolencia" name="dolencia" path="dolencia" rows="5" cols="50" required="required" placeholder="¿De qué adolece el paciente?"/>
          			</div>
          
					<div class="py-1 px-1 divBoton">
            			<input class="btnAniadir col-md-4 col-lg-3" type="submit" value="Añadir evaluación"/>
          			</div>
    			</form:form>
    		</div>
		<!-- PIE DE PÁGINA -->
    	<footer class="text-center text-md-right bg-primary text-white">
    		<div class="container">
      			<p class="mx-1 py-3">
       				2021. Aplicación web para la realización de evaluaciones de triajes en
       				una sala de urgencias.
       			</p>
    		</div>
    	</footer>
	</body>
</html>