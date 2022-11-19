<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<body class="all">
    <form id="formulario" action="InicioSesion" method="post">
        <p >Inicio de sesión</p>
        <label for="usuario">Usuario:</label><br>
        <input type="text" name="usuario" required size="25" maxlength="30"><br>
        <label for="password">Contraseña:</label><br>
        <input name="password" type="password" required size="25" maxlength="30"><br>
        <p><input type="submit" value="Iniciar" ></p>
    </form>
    <p class="center"><a href = "Recuperacion.html">¿Ha olvidado su contraseña?</a></p>
    <p class="center">¿Aún no se ha registrado? <a href = "Registro.jsp">Registrarse</a></p>
</body>