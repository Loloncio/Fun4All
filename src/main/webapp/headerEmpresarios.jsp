<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>Fun4All</title>
    <link rel="stylesheet" type="text/css" href="CSS/estilo.css">
    <link rel="stylesheet" type="text/css" href="CSS/cabecera.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<header class="headEmp">
    <a href="CargaSalas">
        <img class ="float" src="Imagenes/logo.jpg" width="200" height="80" alt="Logo"></a>
    <h1 class="center"> Fun4All </h1>

    <jsp:useBean id="empresario" scope="session" class="modelo.Empresario"/>
    <% HttpSession ses = request.getSession();
        if (!empresario.getNombre().equals("")) {%>
    <p class="right"><a href = "CargaEmpresario"></a></p>

    <a href = "CargaEmpresario">
        <%if (data.EmpresarioDB.hayImagen(empresario.getEmail())) {%>
        <img src="ImagenUsuario?nombre=<%=empresario.getEmail()%>" height="80" width="80" alt="Perfil">
        <%} else {%>
        <img class ="float" src="Imagenes/perfil.jpg" width="80" height="80" alt="Perfil">
        <%}%>
    </a>    

    <%} else {%>
    <p class="right"><a href = "InicioSesionEmpresario.jsp">Iniciar sesión/ Registro</a></p>
    <a href="InicioSesion.jsp">
        <input type="submit" class="boton" value="Individuales">
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

