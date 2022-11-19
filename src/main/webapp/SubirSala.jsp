<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/headerEmpresarios.jsp" %>

<body class="all">
    <form id="formulario_sala" action="AddSala" enctype="multipart/form-data" method="post">
        <div class="row">


            <div class="column">            
                <br><label for="imagen">Imagen:</label>
                <br><input type="file" id="imagen" name="imagen" accept="image/*">
                <br><label for="nombre">Nombre:</label>
                <br><input name="nombre" id="nombre" type="text" size="25" maxlength="30">
                <br><label for="cap">Capacidad:</label>
                <br><input id="cap" name="cap" type="number" required min=0>
                <br><label for="precio">Precio/hora:</label>
                <br><input id="precio" name="precio" type="number" required step="0.01" min=0>
                <br><label for="ubicacion">Ubicación:</label>
                <br><input name="ubicacion" id="ubicacion" type="text" size="25" maxlength="30">
                <br><label for="categoria">Categoría:</label>
                <br><input name="categoria" id="categoria" type="text" size="25" maxlength="30">

            </div>

            <div class="column" id="contenedor_medio">
                
                <br>Clasificación:
                <a>
                    <input name="clasificacion_sala" type="radio" checked>Adultos 
                    <input name="clasificacion_sala" type="radio" checked>Niños 
                </a>
                <h2>Descripción:</h2>
                <textarea name="descripcion" required  cols="50" style="resize: none; height: 150px">
                Describa su sala.
                </textarea>                        
            </div>

            <div class="column" style="margin: 0px;">
                <h2> Horarios:</h2>
                    <%for(int i=10;i<23;i++){%>
                    <input type="checkbox" name="hora"><%=String.valueOf(i)+":00"%>
                    <%}%>
                
                <button type="submit" class="info" >Subir sala</button>
            </div>

        </div>
    </form>
</body>

