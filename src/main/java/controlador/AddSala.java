/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import data.SalaDB;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.Empresario;
import modelo.Sala;
import modelo.Usuario;

@MultipartConfig
public class AddSala extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Empresario empresario = (Empresario) session.getAttribute("empresario");
        String empresa = empresario.getNombreEmp();
        String nomSala = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String localizacion = request.getParameter("ubicacion");
        Float precio = Float.parseFloat(request.getParameter("precio"));
        boolean salaInf = Boolean.valueOf(request.getParameter("infantil"));
        int capacidad = Integer.parseInt(request.getParameter("cap"));
        String categoria = request.getParameter("categoria");
        Part foto = request.getPart("imagen");
        
        Sala sala = new Sala();
        sala.setNombre(nomSala);
        sala.setDescripcion(descripcion);
        sala.setUbicacion(localizacion);
        sala.setPrecio(precio);
        sala.setCapacidad(capacidad);
        sala.setCategoria(categoria);
        sala.setImagen(foto);
        sala.setInfantil(salaInf);
       
        SalaDB.insert(sala, empresa);

        String url = "/CargaEmpresario";
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
