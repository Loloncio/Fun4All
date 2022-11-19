<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Valoracion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<link rel="stylesheet" type="text/css" href="CSS/calendario.css">

<% modelo.Sala sala = (modelo.Sala) request.getAttribute("sala");%>
<body class="all">
    <div class="masInfo">
        <img class="float" src="ImagenSala?id=<%=sala.getId()%>" width="500" height="400" alt="Imagen">
        <div class="carac1">
            <%=sala.getNombre()%><br>
            Capacidad: <%=sala.getCapacidad()%> personas<br>
            Precio: <%=sala.getPrecio()%>€/hora <br>
            <p>Valoración: <b>&#9733;&#9733;&#9733;&#9733;&#9733;</b></p>
        </div>
        <div class="carac2">
            <%=sala.getDescripcion()%>
        </div>
    </div>
    <div class="reserva">

        <div id="cal"> 
            <div class="header"> 
                <span class="left button" id="prev"> &lang; </span> 
                <span class="month-year" id="label"> June 20&0 </span> 
                <span class="right button" id="next"> &rang; </span>
            </div> 
            <table id="days"> 
                <td>sun</td> 
                <td>mon</td> 
                <td>tue</td> 
                <td>wed</td> 
                <td>thu</td> 
                <td>fri</td> 
                <td>sat</td>
            </table> 
            <div id="cal-frame"> 
                <table class="curr"> 
                    <tbody> 
                        <tr><td class="nil"></td><td class="nil"></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td></tr> 
                        <tr><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td class="today">11</td><td>12</td></tr> 
                        <tr><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td></tr> 
                        <tr><td>20</td><td>21</td><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td></tr> 
                        <tr><td>27</td><td>28</td><td>29</td><td>30</td><td class="nil"></td><td class="nil"></td><td class="nil"></td></tr> 
                    </tbody> 
                </table>
            </div> 
        </div>
        <table class="res">
            <tr>
                <th class="res1"></th>
                <th class="res2">Horario</th>
            </tr>
            <tr>
                <td class="res"><input type="checkbox"></td>
                <td class="res">16:00-18:00</td>
            </tr>
            <tr>
                <td class="res"><input type="checkbox"></td>
                <td class="res">18:00-20:00</td>
            </tr>
            <tr>
                <td class="res"><input type="checkbox"></td>
                <td class="res">20:00-22:00</td>
            </tr>

        </table>
        <input class="res" type="submit" value="Reservar">
    </div>
    <div class="valoraciones">
        <%List<Valoracion> listValoraciones = (ArrayList<Valoracion>) request.getAttribute("valoraciones");
            Iterator<Valoracion> itValoraciones = listValoraciones.iterator();
            while (itValoraciones.hasNext()) {
                Valoracion valoracion = itValoraciones.next();%>
        <div class="val">
            <%if (data.UserDB.hayImagen(user.getNombre())) {%>
            <img src="ImagenUsuario?nombre=<%=user.getNombre()%>" width="60" height="60" alt="Perfil">
            <%} else {%>
            <img class ="float" src="Imagenes/profilephoto.jpg" width="60" height="60" alt="Perfil">
            <%}%>
            <p><b><%=valoracion.getUserName()%></b></p>
            <p><%=valoracion.getOpinion()%></p>
            <p class="estrellas"><b>&#9733;&#9733;&#9733;&#9733;&#9733;</b></p>
        </div>
        <%}%>
    </div>
</body>