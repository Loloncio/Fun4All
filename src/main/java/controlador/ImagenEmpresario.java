package controlador;

import data.EmpresarioDB;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImagenEmpresario extends HttpServlet {

   protected void processRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("image/jpg");
        OutputStream respuesta = response.getOutputStream();
        String email = request.getParameter("email");
        EmpresarioDB.getImagen(email, respuesta);
        respuesta.close();
        response.flushBuffer();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
    }
}