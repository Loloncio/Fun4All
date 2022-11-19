/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Empresario;
import data.EmpresarioDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistroEmpresario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("usuario");
        String nombreEmp = request.getParameter("nombre_empresa");
        String email = request.getParameter("mail");
        String password = request.getParameter("password");
        String NIF = request.getParameter("nif_cif");
        String direccion = request.getParameter("dir");
        int telefono = Integer.parseInt(request.getParameter("telefono"));

        Empresario empresa = new Empresario(nombre, nombreEmp, email, password, NIF, direccion, telefono);
        if (!EmpresarioDB.emailExists(email)) {
            EmpresarioDB.insert(empresa);
        } else{
            String url = "/RegistroEmpresario.html";
            RequestDispatcher dispatcher
                     = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        HttpSession session = request.getSession();
        session.setAttribute("empresario", empresa);

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
