package controlador;

import data.UserDB;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.Usuario;

@MultipartConfig
public class RegistroUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("usuario");
        String password = request.getParameter("password");
        String email = request.getParameter("mail");
        Date nacimiento = Date.valueOf(request.getParameter("fnac"));
        Part foto = request.getPart("logo");

        Usuario user = new Usuario(nombre, password, email, nacimiento);
        UserDB.insert(user);
        
        if (foto.getSize() != 0) {
            UserDB.setImagen(nombre, foto);
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        String url = "/CargaSalas";
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

}
