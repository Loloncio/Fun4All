package controlador;

import data.SalaDB;
import data.ValoracionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Sala;
import modelo.Valoracion;

public class CargaSala extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        
        Sala sala = null;
        sala = SalaDB.selectSala(id);
        ArrayList<Valoracion> valoraciones = null;
        valoraciones = ValoracionDB.listarValoracionesSala(id);
        request.setAttribute("sala", sala);
        request.setAttribute("valoraciones", valoraciones);
        
        String url = "/Sala.jsp";
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