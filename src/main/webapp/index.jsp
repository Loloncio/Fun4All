<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,modelo.Sala" %>
<%@ include file="/header.jsp" %>

<body class="all">        
    <form id = "formulario" method="post" action="FiltraSalas"><input type="hidden" name="origen" value="web">
        <select name="Tipo de sala">
            <option selected>Cine</option>
            <option>Deporte</option>
            <option>Videojuegos</option>
            <option>Karaoke</option>
        </select>
        <select name="Ubicación">
            <option selected>Valladolid</option>
            <option>Madrid</option>
            <option>Valencia</option>
            <option>Sevila</option>
        </select>
        <select name="Personas">
            <option selected>5</option>
            <option>10</option>
            <option>15</option>
            <option>20</option>
        </select>
        <p>
            <input name="edad" type="radio" value="adulto" checked> Adultos
            <input name="edad" type="radio" value="niño" checked> Niños
        </p>
        <p>
            <input type="submit" value="Buscar">
        </p></form>

    <div class="titulos">
        <p class="cajaI"><strong>Los más destacados en tu zona</strong></p>
        <p class="cajaD"><strong>Los más buscados</strong></p>
    </div>
    <%List<Sala> lista = (ArrayList<modelo.Sala>) request.getAttribute("salas");
        Iterator<modelo.Sala> itSalas = lista.iterator();
        while (itSalas.hasNext()) {
            Sala sala = itSalas.next();%>

    <span class="cajaI">
        <img src="ImagenSala?id=<%= sala.getId()%>" width="170" height="150" alt="imagen">
        <%=sala.getNombre()%><br>   
        Capacidad: <%=sala.getCapacidad()%> personas<br>
        Precio: <%=sala.getPrecio()%>€/hora<br>
        Valoración: <b>&#9733;&#9733;&#9733;&#9733;&#9733;</b><br>
        <a href="CargaSala?id=<%= sala.getId()%>"><input type="submit" class="info" value="Más info."></a> 
    </span>
    <%if (itSalas.hasNext()) {
            sala = itSalas.next();%>
    <span class="cajaD">

        <img src="ImagenSala?id=<%= sala.getId()%>" width="170" height="150" alt="imagen">
        <%=sala.getNombre()%><br>
        Capacidad: <%=sala.getCapacidad()%> personas<br>
        Precio: <%=sala.getPrecio()%>€/hora<br>
        Valoración: <b>&#9733;&#9733;&#9733;&#9733;&#9733;</b><br>           
        <a href="CargaSala?id=<%= sala.getId()%>"><input type="submit" class="info" value="Más info."></a>       
    </span>  
    <%}%>
    <%}%>
</body>  

