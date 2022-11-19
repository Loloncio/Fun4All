<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<body class="all">

    <form id="formulario" action="CambiarUser" method="post">
        <h1>Editar nombre de usuario</h1>
        <br><p >Nuevo nombre de usuario: </p>
        <input name="usuario" type="text" size="25" maxlength="30">
        <br><br><br><input type="submit" value="Submit" >
    </form>

    <form id="formulario2" action="CambiarPass" method="post">
        <h1>Cambiar contraseña</h1>
        <p >Contraseña actual:</p>
        <input name="password" type="password" size="25" maxlength="30">
        <p >Nueva Contraseña:</p>
        <input name="password" type="password" size="25" maxlength="30">
        <p >Repetir nueva contraseña:</p>
        <input name="password" type="password" size="25" maxlength="30">
        <br><br><br><input type="submit" value="Submit" >
    </form>

    <form id="formulario3" action="CambiarMail" method="post">
        <h1>Cambiar e-mail de contacto</h1>
        <p>Nuevo e-mail:</p>
        <input name="mail" type="text" size="25" maxlength="50">
        <br><br><br><input type="submit" value="Submit" >
    </form>

    <form id="formulario4" action="CambiarImagen" method="post">
        <h1>Cambiar imagen de perfil</h1>
        <p >Nueva imagen:</p>
        <input type="file" id="logo" name="logo" accept="image/*">                
        <br><br><br><input type="submit" value="Submit" >
    </form>
</body>

