<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="modelo.Sala"%>
<%@ page language="java" import="modelo.Empresario"%>
<%@ include file="/headerEmpresarios.jsp" %>

<body class="all">
    <div class ="userEmpresario">
        <%if (data.EmpresarioDB.hayImagen(empresario.getEmail())) {%>
        <img src="ImagenUsuario?nombre=<%=empresario.getEmail()%>" height="170" width="150" alt="Perfil">
        <%} else {%>
        <img class ="float" src="Imagenes/perfil.jpg" width="170" height="150" alt="Perfil">
        <%}%>
        <p><input type="submit" value="Editar Perfil"></p>
        <p>Nombre: <%=empresario.getNombre()%></p>
        <p>Empresa: <%=empresario.getNombreEmp()%></p>
        <p>CIF/NIF: <%=empresario.getNIF()%></p>
        <p>e-mail: <%=empresario.getEmail()%></p>
        <p>Teléfono: <%=empresario.getTelefono()%></p>
        <a href="SubirSala.jsp"><p><input type="submit" value="Añadir Sala"></p></a>
        <a href="CerrarSesion"><p><input type="submit" value="Cerrar sesión"></p></a>
    </div>

    <div class ="salasSubidas">
        <%List<Sala> listaSalas = (ArrayList<Sala>) request.getAttribute("salas");
            Iterator<modelo.Sala> itSalas = listaSalas.iterator();
            while (itSalas.hasNext()) {
                Sala sala = itSalas.next();%>
        <div id="sala1">
            <div id="cajaIVE" class="cajaIVE">
                <img src="ImagenSala?id=<%= sala.getId()%>" width="170" height="150" alt="imagen">
                <p><%=sala.getNombre()%> </p>
                <p>Capacidad: <%=sala.getCapacidad()%> </p>
                <p>Precio: <%=sala.getPrecio()%>€/hora </p>
                <p>Valoración: </p>
                <p>Descripcion: <%=sala.getDescripcion()%></p>
            </div>
            
        </div>
        <%if (itSalas.hasNext()) {
                sala = itSalas.next();%>
        <div id="sala2">
            <div id="cajaDVE" class="cajaDVE">
                <img src="ImagenSala?id=<%= sala.getId()%>" width="170" height="150" alt="imagen">
                <p><%=sala.getNombre()%> </p>
                <p>Capacidad: <%=sala.getCapacidad()%> </p>
                <p>Precio: <%=sala.getPrecio()%>€/hora </p>
                <p>Valoración: </p>
                <p>Descripcion: <%=sala.getDescripcion()%></p>
            </div>
        </div>
        <%}%>
        <%}%>
    </div>   
</body>
