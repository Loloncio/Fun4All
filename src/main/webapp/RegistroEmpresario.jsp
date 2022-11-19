<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/headerEmpresarios.jsp" %>

<body class="all">
    <form id="formulario" name="documento" onsubmit="return camposPass()" action="RegistroEmpresario" method="post">
        <h1>Registro:</h1><br>
        <label for="nombre_empresa">Nombre o razón social:</label><br>
        <input name="nombre_empresa" type="text" required size="25" maxlength="30"><br>
        <label for="nif_cif">NIF/CIF:</label><br>      
        <input name="nif_cif" type="text" required size="25" maxlength="30"><br>
        <label for="telefono">Número de teléfono:</label><br>
        <input name="telefono" type="text" required size="25" maxlength="30"><br>
        <label for="usuario">Usuario: </label><br>
        <input name="usuario" type="text" required size="25" maxlength="30"><br>
        <label for="password">Contraseña:</label><br>
        <input name="password" type="password" required size="25" maxlength="30"><br>
        <label for="password2">Repetir contraseña:</label><br>
        <input name="password2" type="password" required size="25" maxlength="30"><br>
        <label for="mail">E-mail:</label><br>
        <input name="mail" type="text" required size="25" maxlength="50"><br>
        <label for="dir">Dirección:</label><br>
        <input name="dir" type="text" required size="25" maxlength="30">
        <br><br><p><input type="submit" value="Registrarse" ></p>
    </form>
</body>
