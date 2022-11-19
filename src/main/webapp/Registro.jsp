<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="/header.jsp" %>

<body class="all">
    <form id="formulario" name="documento" action="RegistroUsuario" onsubmit="return camposPass()" method="post" enctype="multipart/form-data">
        <h1>Registro:</h1>
        <br><label for="usuario">Usuario:</label><br>
        <input name="usuario" type="text" required size="25" maxlength="30">
        <br><label for="password">Contraseña:</label><br>
        <input name="password" type="password" required size="25" maxlength="30">
        <br><label for="password2">Repetir contraseña:</label><br>
        <input name="password2" type="password" required size="25" maxlength="30">
        <br><label for="mail">E-mail:</label><br>
        <input name="mail" type="text" required size="25" maxlength="50">
        <br><label for="fnac">Fecha de nacimiento:</label><br>
        <input name="fnac" type="date" required size="25" ><br>
        <br><label for="logo">Foto de perfil:</label><br>
        <input type="file" id="logo" name="logo" accept="image/*"><br>
        <br><br><br><p><input type="submit" value="Submit" ></p>
    </form>
</body>

