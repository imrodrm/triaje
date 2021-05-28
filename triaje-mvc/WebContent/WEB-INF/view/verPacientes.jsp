<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

    <!-- ARCHIVOS CSS PROPIOS -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu_y_footer.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/verEvaluaciones.css" />

    <!--jQuery y DataTables-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/js/mostrarDataTablePaciente.js"></script>


    <title>Triaje - Salud</title>
  </head>
  <body>
    <!--BARRA DE NAVEGACI�N SUPERIOR-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
      <!--logo de la aplicaci�n-->
      <a href="${pageContext.request.contextPath}/" class="navbar-brand">
        <img
          class="d-inline-block align-text-bottom"
          alt="Logo de la aplicaci�n"
          src="${pageContext.request.contextPath}/resources/img/logo.png"
        />
      </a>
      <!--Este bot�n es a la hora de hacerlo responsivo. Es una aplicaci�n web pensada para ordenadores, pero quiz� en un futuro se pase a tablets y m�viles, por ello lo dejamos-->
      <button
        type="button"
        class="navbar-toggler"
        data-toggle="collapse"
        data-target="#menu-principal"
        aria-controls="menu-principal"
        aria-expanded="false"
        aria-label="Desplegar men� de navegaci�n"
      >
        <!--El span es el icono de hamburguesa para el men� responsivo-->
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
              >Evaluaciones<span class="caret"></span>
            </a>

            <ul class="dropdown-menu bg-primary">
              <li class="nav-item-dropdown">
                <a class="nav-link text-center" href="../evaluacion/verHoy">Listado de hoy</a>
              </li>
              <li class="nav-item-dropdown">
                <a class="nav-link text-center" href="../evaluacion/verUltimasCuatroHoras">�ltimas 4 horas</a>
              </li>
              <li class="nav-item-dropdown">
                <a class="nav-link text-center" href="../evaluacion/verTodas">Ver todas</a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a href="verTodos" class="nav-link">Listado de pacientes</a>
          </li>
        </ul>

        <ul class="navbar-nav ml-md-auto">
          <li class="nav-item">
            <a href="../evaluacion/nueva" class="nav-link" id="nuevaEvaluacion"
              >NUEVA EVALUACI�N</a
            >
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <a href="../cerrarSesion" class="nav-link" id="nuevaEvaluacion">
              <img
                src="${pageContext.request.contextPath}/resources/img/cerrarsesion.png"
                alt="Cerrar sesi�n"
                class="py-10 px-10 h-100"
                id="cerrarsesion"
              />
              <p class="txtCerrarSesion">Cerrar sesi�n</p>
            </a>
          </li>
        </ul>
      </div>
    </nav>
    <div class="py-5 px-5 todo">
      <table id="tablaPacientes" class="display">
          <thead>
              <tr>
                  <th>NSS</th>
                  <th>Nombre</th>
                  <th>Fecha de nacimiento</th>
                  <th>Domicilio</th>
                  <th>Poblaci�n</th>
                  <th>Tel�fono</th>
                  <th>G�nero</th>
              </tr>
          </thead>
          <tbody>
          	<c:forEach items="${pacientes}" var="paciente">
          		<tr>
          			<td>${paciente.NSS}</td>
          			<td>${paciente.nombre}</td>
          			<td>${paciente.f_nac}</td>
          			<td>${paciente.domicilio}</td>
          			<td>${paciente.poblacion}</td>
          			<td>${paciente.telefono}</td>
          			<td>${paciente.genero}</td>
          		</tr>
          	</c:forEach>

          </tbody>
          <tfoot>
          	<tr>
              	  <th>NSS</th>
                  <th>Nombre</th>
                  <th>Fecha de nacimiento</th>
                  <th>Domicilio</th>
                  <th>Poblaci�n</th>
                  <th>Tel�fono</th>
                  <th>G�nero</th> 
              </tr>
          </tfoot>
      </table>
    </div>
    <!-- PIE DE P�GINA -->
    <footer class="text-center bg-primary text-white">
      <div class="container">
        <p class="mx-1 py-3">
          2021. Aplicaci�n web para la realizaci�n de evaluaciones de triajes en
          una sala de urgencias.
        </p>
      </div>
    </footer>
  </body>
</html>
