package controlador;

import data.UserDB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

public class InicioSesion extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");
        String usuario = request.getParameter("usuario");
        
        Usuario user = null;
        if(UserDB.userExists(usuario)&&UserDB.passCorrecta(password, usuario)){
            user = UserDB.selectUser(usuario);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            String url = "/CargaSalas";
            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }else{
            String url = "/InicioSesion.jsp";
            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
