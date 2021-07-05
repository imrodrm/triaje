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
    	<script src="${pageContext.request.contextPath}/resources/js/cardShadow.js"></script>
	
	    <!-- ARCHIVOS CSS PROPIOS -->
	    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu_y_footer.css" />
	    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">

    	<title>Triaje - Salud</title>
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
		                		<a class="nav-link text-center" href="evaluacion/verHoy">Listado de hoy</a>
              				</li>
              				<li class="nav-item-dropdown">
		                		<a class="nav-link text-center" href="evaluacion/verUltimasCuatroHoras">Últimas 4 horas</a>
              				</li>
              				<li class="nav-item-dropdown">
		                		<a class="nav-link text-center" href="evaluacion/verTodas">Ver todas</a>
              				</li>
            			</ul>
          			</li>
          			<li class="nav-item">
		            	<a href="paciente/verTodos" class="nav-link">Listado de pacientes</a>
    	    		</li>
        		</ul>

	        	<ul class="navbar-nav ml-md-auto">
          			<li class="nav-item">
	            		<a href="evaluacion/nueva" class="nav-link" id="nuevaEvaluacion">NUEVA EVALUACIÓN</a>
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
    	<div class="Bienvenidx pt-4 px-5">
    		<p class="bienvenida">Hola, ${login.nombre} ${login.apellidos}.</p>
    	</div>
    	<!-- Sección de las cartas, los py y px son para el padding -->
    	<section class="py-4 px-5">
    		<!-- Div que es una rejilla para las cartas, si es móvil es 1 columna por fila, si es tablet 2 y si es desktop 3 -->
      		<div class="gridCard row row-cols-1 row-cols-sm-2 row-cols-md-3">
      			<!-- Primera carta  -->
        		<article class="col-12 col-sm-6 col-md-4 col-lg-4">
          			<div class="card text-center border-primary h-100">
            			<img src="${pageContext.request.contextPath}/resources/img/evaluacion.png" class="card-img-top h-100 py-1 px-1" alt="Evaluación">
            			<div class="card-body">
              				<h2 class="card-title">Evaluaciones</h2>
              				<p class="card-text">Visualiza las evaluaciones recientes.</p>
            			</div>
            			<div class="card-footer">
              				<a href="evaluacion/verUltimasCuatroHoras" class="stretched-link btn btn-primary">Ver</a>
            			</div>
          			</div>
        		</article>
        
        		<article class="col-12 col-sm-6 col-md-4 col-lg-4">
          			<div class="card text-center border-primary h-100">
            			<img src="${pageContext.request.contextPath}/resources/img/pacientes.png" class="card-img-top h-100 py-1 px-1" alt="Pacientes">
            			<div class="card-body">
              				<h2 class="card-title">Pacientes</h2>
              				<p class="card-text">Visualiza los pacientes.</p>
            			</div>
            			<div class="card-footer">
              				<a href="paciente/verTodos" class="btn btn-primary">Ver</a>
            			</div>
          			</div>
        		</article>

        		<article class="col-12 col-sm-6 col-md-4 col-lg-4">
          			<div class="card text-center border-primary">
            			<img src="${pageContext.request.contextPath}/resources/img/virus.png" class="card-img-top h-100 py-1 px-1" alt="Enfermedades">
            			<div class="card-body">
              				<h2 class="card-title">Enfermedades</h2>
              				<p class="card-text">Visualiza las enfermedades.</p>
            			</div>
            			<div class="card-footer">
              				<a href="enfermedad/verTodas" class="btn btn-primary">Ver</a>
            			</div>
          			</div>
        		</article>
      		</div>
    	</section>
    	
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