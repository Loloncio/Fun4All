/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import data.ValoracionDB;
import data.ReservaDB;
import data.SalaDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Reserva;
import modelo.Sala;
import modelo.Valoracion;

/**
 *
 * @author ruixin
 */
public class CargaPerfil extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        
        ArrayList<Valoracion> valoraciones = null;
        valoraciones = ValoracionDB.listarValoraciones(nombre);
        ArrayList<Reserva> reservas = null;
        reservas = ReservaDB.listarReservas(nombre);
        ArrayList<Sala> salas = new ArrayList<>();
        
        for(Reserva reserva: reservas){
            salas.add(SalaDB.selectSala(String.valueOf(reserva.getSalaId())));
        }
        
        request.setAttribute("reservas", reservas);
        request.setAttribute("valoraciones", valoraciones);
        request.setAttribute("salas", salas);
        String url = "/Perfil.jsp";
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
