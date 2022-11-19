<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>Fun4All</title>
    <link rel="stylesheet" type="text/css" href="CSS/cabecera.css">
    <link rel="stylesheet" type="text/css" href="CSS/estilo.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<header class="head">  
    <a href="CargaSalas">
        <img class ="float" src="Imagenes/logo.jpg" width="200" height="80" alt="Logo"></a>
    <h1 class="center"> Fun4All </h1>
    <jsp:useBean id="user" scope="session" class="modelo.Usuario"/>
    <%if (!user.getNombre().equals("")) {%>
    <p class="right"><a href = "ValoracionesPerfil?nombre=<%=user.getNombre()%>"><%=user.getNombre()%></a></p>
    <a href="ValoracionesPerfil?nombre=<%= user.getNombre()%>">
        <%if (data.UserDB.hayImagen(user.getNombre())) {%>
        <img src="ImagenUsuario?nombre=<%=user.getNombre()%>" height="80" width="80" alt="Perfil">
        <%} else {%>
        <img class ="float" src="Imagenes/profilephoto.jpg" width="80" height="80" alt="Perfil">
        <%}%>
    </a>
    <%} else {%>
    <p class="right"><a href = "InicioSesion.jsp">Iniciar sesión/ Registro</a></p>
    <a href="InicioSesionEmpresario.jsp">
        <input type="submit" class="boton" value="Empresas">
    </a>  
    <%}%>  
    <script>
        function camposPass(){
            var x = document.forms["documento"]["password"].value;
            var y = document.forms["documento"]["password2"].value;
            if(x != y){
                alert("Las password deben ser iguales");
                return false;
            }
        }
    </script>
</header>