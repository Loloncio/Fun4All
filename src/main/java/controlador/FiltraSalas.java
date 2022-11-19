/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import data.SalaDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Sala;

/**
 *
 * @author lolo
 */
public class FiltraSalas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String sala = request.getParameter("Tipo de sala");
        String ubicacion = request.getParameter("Ubicacion");
        String personas = request.getParameter("Personas");
        String adultos = request.getParameter("edad");
        int cap = Integer.parseInt(personas);
        ArrayList<Sala> salas;
        if(adultos.equals("Adultos"))
            salas = SalaDB.Filtrar(sala,  ubicacion, cap, false);
        else
            salas = SalaDB.Filtrar(sala,  ubicacion, cap, true);
        
        request.setAttribute("salas", salas);

        String url = "/Seleccion.jsp";
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
