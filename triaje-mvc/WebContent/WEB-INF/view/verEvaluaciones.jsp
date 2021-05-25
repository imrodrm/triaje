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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu_y_footer.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/verEvaluaciones.css" />

    <!--jQuery y DataTables-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
    <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/js/mostrarDataTables.js"></script>


    <title>Triaje - Salud</title>
  </head>
  <body>
    <!--BARRA DE NAVEGACIÓN SUPERIOR-->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
      <!--logo de la aplicación-->
      <a href="${pageContext.request.contextPath}/resources" class="navbar-brand">
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
              >Evaluaciones<span class="caret"></span>
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
            <a href="#" class="nav-link" id="nuevaEvaluacion"
              >NUEVA EVALUACIÓN</a
            >
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item">
            <a href="/cerrarSesion" class="nav-link" id="nuevaEvaluacion">
              <img
                src="/img/cerrarsesion.png"
                alt="Cerrar sesión"
                class="py-10 px-10 h-100"
                id="cerrarsesion"
              />
              <p class="txtCerrarSesion">Cerrar sesión</p>
            </a>
          </li>
        </ul>
      </div>
    </nav>
    <div class="py-5 px-5 todo">
      <table id="tablaEvaluaciones" class="display">
          <thead>
              <tr>
                  <th>Id evaluación</th>
                  <th>Fecha y hora</th>
                  <th>ID evaluador</th>
                  <th>Nombre del Paciente</th>
                  <th>Prioridad</th>
                  <th>Temperatura</th>
                  <th>Altura</th>
                  <th>Peso</th>
                  <th></th>
              </tr>
          </thead>
          <tbody>
              <tr>
                  <th>AB123458901235-1</th>
                  <th>2021-03-02 10:35:42</th>
                  <th>1</th>
                  <th>Ermenegildo Rupérez</th>
                  <th>5</th>
                  <th>36.5</th>
                  <th>1.61</th>
                  <th>60</th>
                  <th>Ver dolencia</th>
              </tr>
              <tr>
                <th>AB123458901235-1</th>
                <th>2021-03-02 10:35:42</th>
                <th>1</th>
                <th>Ermenegildo Rupérez</th>
                <th>5</th>
                <th>36.5</th>
                <th>1.61</th>
                <th>60</th>
                <th>Ver dolencia</th>
            </tr>
            <tr>
                <th>AB123458901235-1</th>
                <th>2021-03-02 10:35:42</th>
                <th>1</th>
                <th>Ermenegildo Rupérez</th>
                <th>5</th>
                <th>36.5</th>
                <th>1.61</th>
                <th>60</th>
                <th>Ver dolencia</th>
            </tr>
            <tr>
                <th>AB123458901235-1</th>
                <th>2021-03-02 10:35:42</th>
                <th>1</th>
                <th>Ermenegildo Rupérez</th>
                <th>5</th>
                <th>36.5</th>
                <th>1.61</th>
                <th>60</th>
                <th>Ver dolencia</th>
            </tr>
            <tr>
                <th>AB123458901235-1</th>
                <th>2021-03-02 10:35:42</th>
                <th>1</th>
                <th>Ermenegildo Rupérez</th>
                <th>5</th>
                <th>36.5</th>
                <th>1.61</th>
                <th>60</th>
                <th>Ver dolencia</th>
            </tr>
            <tr>
                <th>AB123458901235-1</th>
                <th>2021-03-02 10:35:42</th>
                <th>1</th>
                <th>Ermenegildo Rupérez</th>
                <th>5</th>
                <th>36.5</th>
                <th>1.61</th>
                <th>60</th>
                <th>Ver dolencia</th>
            </tr>
            <tr>
                <th>AB123458901235-1</th>
                <th>2021-03-02 10:35:42</th>
                <th>1</th>
                <th>Ermenegildo Rupérez</th>
                <th>5</th>
                <th>36.5</th>
                <th>1.61</th>
                <th>60</th>
                <th>Ver dolencia</th>
            </tr>
          </tbody>
      </table>
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

