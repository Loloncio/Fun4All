<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/headerEmpresarios.jsp" %>

<body class="all">
    <form id="formulario" action="InicioSesionEmpresario" method="post">
        <p >Inicio de sesión</p>
        <label for="email">E-mail:</label><br>
        <input name="email" type="text" required size="25" maxlength="30"><br>
        <label for="password">Contraseña:</label><br>
        <input name="password" type="password" required size="25" maxlength="30"><br>
        <p><input type="submit" value="Iniciar" ></p>
    </form>
    <p class="center"><a href = "Recuperacion.html">¿Ha olvidado su contraseña?</a></p>
    <p class="center">¿Aún no se ha registrado? <a href = "RegistroEmpresario.jsp">Registrarse</a></p>
</body>

