/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import data.ReservaDB;
import data.SalaDB;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Reserva;
import modelo.Sala;
import modelo.Usuario;

/**
 *
 * @author ruixin
 */
public class RealizarReserva extends HttpServlet {

    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             
        HttpSession session=request.getSession();
        HttpSession session2=request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        Sala sala = (Sala) session2.getAttribute("sala");
        
        String nomUsuario = request.getParameter("nombre_user");
        int idSala = Integer.parseInt(request.getParameter("nombre_sala"));
        Date dia = Date.valueOf(request.getParameter("dia"));
        Time hora = Time.valueOf(request.getParameter("hora"));
        Float precio = Float.parseFloat(request.getParameter("precio"));
        
        Reserva reserva = new Reserva(nomUsuario, idSala, dia, hora, precio);
        
        ReservaDB.insert(reserva);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }


}
