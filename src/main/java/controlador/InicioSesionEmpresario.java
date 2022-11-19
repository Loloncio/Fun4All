/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import data.EmpresarioDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Empresario;

public class InicioSesionEmpresario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");
        String email = request.getParameter("email");

        Empresario empresa = null;
        if (EmpresarioDB.emailExists(email) && EmpresarioDB.passCorrecta(password, email)) {
            empresa = EmpresarioDB.selectUser(email);
            HttpSession session = request.getSession();
            session.setAttribute("empresario", empresa);
            
            String url = "/CargaEmpresario";
            RequestDispatcher dispatcher
                    = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }else{
            String url = "/InicioSesionEmpresario.jsp";
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
