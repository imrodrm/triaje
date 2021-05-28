<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
    <!-- Mis JS -->
    <script src="${pageContext.request.contextPath}/resources/js/ajaxNombrePaciente.js"></script>

    <!-- ARCHIVOS CSS PROPIOS -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu_y_footer.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pacienteEvaluacion.css" />

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
          src="${pageContext.request.contextPath}/resources/img/logo.png"
        />
      </a>
      <!--Este botón es a la hora de hacerlo responsivo. Es una aplicación web pensada para ordenadores, pero quizá en un futuro se pase a tablets y móviles, por ello lo dejamos-->
      <button
        type="button"
        class="navbar-toggler"
        data-toggle="collapse"
        data-target="#menu-principal"
        aria-controls="menu-principal"
        aria-expanded="false"
        aria-label="Desplegar menú de navegación"
      >
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
              aria-expanded="false"
              >Evaluaciones<span class="caret"></span
            ></a>
            <ul class="dropdown-menu bg-primary">
              <li class="nav-item-dropdown">
               <a class="nav-link text-center" href="verHoy">Listado de hoy</a>
              </li>
              <li class="nav-item-dropdown">
                <a class="nav-link text-center" href="verUltimasCuatroHoras">Últimas 4 horas</a>
              </li>
              <li class="nav-item-dropdown">
		        <a class="nav-link text-center" href="verTodas">Ver todas</a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="../paciente/verTodos" class="nav-link">Listado de pacientes</a>
          </li>
        </ul>

        <ul class="navbar-nav ml-md-auto">
          <li class="nav-item">
            <a href="nueva" class="nav-link" id="nuevaEvaluacion"
              >NUEVA EVALUACIÓN</a
            >
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <a href="../cerrarSesion" class="nav-link" id="nuevaEvaluacion">
              <img src="${pageContext.request.contextPath}/resources/img/cerrarsesion.png" alt="Cerrar sesión" class="py-10 px-10 h-100" id="cerrarsesion">
              <p class="txtCerrarSesion">Cerrar sesión</p>
            </a>
          </li>
        </ul>
      </div>
    </nav>


    <div class="col-12 col-sm-10 col-md-8 col-lg-6 px-5 py-5 pacienteEvaluacion">
    	<form:form class="py-2 px-2" id="pEvaluacion" method="POST" action="nuevaNombre" modelAttribute="paciente">
    		<legend>Introduzca el nombre del paciente de la evaluación</legend>
    		<hr>
    		<c:if test="${errores != null}">
				<div class="form-row col-12 text-center error">
					<p>${errores}</p>
				</div>
			</c:if>
    		<div class="form-row">
    			<div class="form-group col-12">
    				<label id="lblNombre" class="required bigLBL" for="nombre">Nombre:</label>
    				<form:input path="nombre" id="nombre" class="inputTexto" name="pacienteNombre" required="required" placeholder="Nombre del paciente" autocomplete="off"/>
    				<div id="pacientesSugerencias" style="backgroud-color: black;"></div>
    			</div>
    		</div>
    		<div class="form-row">
    			<div class="form-group col-12">
    				<label id="lblDomicilio" class="required bigLBL" for="domicilio">Domicilio:</label>
    				<form:input path="domicilio" id="domicilio" class="inputTexto" name="domicilio" required="required" placeholder="Domicilio del paciente" autocomplete="off"/>
    			</div>
    		</div>
    		<div class="form-row">
                <div class="col-12 divBoton">
                    <input class="btnAniadir" type="submit" value="Continuar"/>
                </div>
            </div>
            <hr>
            <div class="form-row text-center">
                <div class="col-12 divNombre">
                    <a href="nueva"><p class="formNombre">Haz clic aquí si prefiere introducir el NSS</p></a>
                </div>
            </div>
    	</form:form>
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