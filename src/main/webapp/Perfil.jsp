<%@page import="data.ValoracionDB"%>
<%@page import="modelo.Reserva"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Valoracion"%>
<%@page import="modelo.Sala"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<body class="all">

    <div class="cajaI">
        <div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <%if (data.UserDB.hayImagen(user.getNombre())) {%>
            <img src="ImagenUsuario?nombre=<%=user.getNombre()%>" height="125" width="125" alt="Perfil">
            <%} else {%>
            <img class ="float" src="Imagenes/profilephoto.jpg" width="125" height="125" alt="Perfil">
            <%}%>


            <div class="cajaInf">
                <b>Nombre:</b> <jsp:getProperty name="user" property="nombre"/>
                <b>E-mail:</b><br> <jsp:getProperty name="user" property="email"/>
                <b>Fecha de nacimiento:</b><br> <jsp:getProperty name="user" property="nacimiento"/>     
            </div>
        </div>
        <button type="submit" class="info" >Editar Perfil</button>
        <a href="CerrarSesion"><button type="submit" class="info">Cerrar sesión</button></a>
        <span class="cajaBsb">
            Valoraciones realizadas:
        </span>
        <%List<Valoracion> listValoraciones = (ArrayList<Valoracion>) request.getAttribute("valoraciones");
            Iterator<Valoracion> itValoraciones = listValoraciones.iterator();
            while (itValoraciones.hasNext()) {
                Valoracion valoracion = itValoraciones.next();%>

        <span class="cajaB">
            <%if (data.UserDB.hayImagen(user.getNombre())) {%>
            <img src="ImagenUsuario?nombre=<%=user.getNombre()%>" height="35" width="35" alt="Perfil">
            <%} else {%>
            <img class ="float" src="Imagenes/profilephoto.jpg" width="35" height="35" alt="Perfil">
            <%}%>
            <b> <jsp:getProperty name="user" property="nombre"/></b>  Valoracion: <%=valoracion.getPuntuacion()%>
            <br>
            <%=valoracion.getOpinion()%>
        </span>
        <%}%>
    </div>

    <div class="cajaD" style="text-align: center;">
        <%List<Reserva> listReservas = (ArrayList<Reserva>) request.getAttribute("reservas");
            Iterator<Reserva> itReservas = listReservas.iterator();
            List<Sala> listSalas = (ArrayList<Sala>) request.getAttribute("salas");
            Iterator<Sala> itSalas = listSalas.iterator();
            while (itReservas.hasNext()) {
                Reserva reserva = itReservas.next();
                Sala sala = itSalas.next();%>

        <b> Alquilado para: <%=reserva.getDia()%> <%=reserva.getHora()%> </b><br>

        <span>
            <img src="ImagenSala?id=<%= sala.getId()%>" width="185" height="110" alt="imagen">
        </span>
        <span>
            <b><%=sala.getNombre()%></b><br>
            Capacidad: <%=sala.getCapacidad()%> pax.<br>
            Precio: <%=sala.getPrecio()%>€/h
            Valoración:&#9733;&#9733;&#9733;&#9733;&#9734;
        </span>
        <%if (!ValoracionDB.existsVal(sala.getId(), user.getNombre())) {%>
        <form action="AddValoracion?sala=<%=sala.getId()%>" method="post">
            <span>               
                <label for="comentario">Comentario:</label><br>
                <textarea id="comentario" name="comentario" required  cols="50" style="resize: none">
                </textarea>
                <label for="val">Valoracion:</label>
                <input id="val" name="val" type="number" required size="5" step="0.01" min=0 max=5>
                <button type="submit" class="info" >Enviar</button>
            </span>
        </form>
        <%}%>
    </div>
    <%}%>

</body>
