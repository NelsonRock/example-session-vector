/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import Clases.*;

/**
 *
 * @author rcerrato
 */
@WebServlet(name = "SInicio", urlPatterns = {"/SInicio"})
public class SInicio extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String cadena="";
        try{
        Vector lb= new Vector();
        Double total=0.0;
        HttpSession sesion=request.getSession(true);
        //String nombre=null;
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SInicio</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bienvenidos a mi Libreria</h1><img src='libreria.jpg'></br></br>");//src='././libreria.jpg'
            if (sesion.isNew()){
                //sesion.setAttribute("visitas", 1);
                sesion.setAttribute("librosPrestados", lb);
                 sesion.setAttribute("importe", total);
                cadena+="<p>Bienvenido nuevo usuario</p>";
                cadena+="<p>Tienes "+lb.size()+" libros en tu carro</p>";
            }
            else{
                //Integer n = (Integer)sesion.getAttribute("visitas");
                lb = (Vector)sesion.getAttribute("librosPrestados");
                total=(Double)sesion.getAttribute("importe");
                cadena+=("<p>Bienvenido de nuevo</p>");
            //nombre=(String)sesion.getAttribute("nombre");
                if (!lb.isEmpty()){
                    //n=n+1;
                    //sesion.setAttribute("visitas",n);
                    cadena+="<p>Tienes " +lb.size()+ " libros en tu carro con un total de "+total+"</p>";
                    for(int i=0;i<lb.size();i++)
                        cadena+="<p>"+((Libro)lb.get(i)).getNombre()+"</p>";
                }
                else
                     cadena+="<p>Tienes "+lb.size()+" libros en tu carro con un total de "+total+"</p>";
            }    
            cadena+="<form action=/CarritoCompras/SAddLibro method=POST >";
            Libreria.inicio();
            for(int i=0;i<Libreria.libros.length;i++)   
                cadena+="<input type='checkbox'  name=libros value='"+Libreria.libros[i].getNombre()+"' />"+Libreria.libros[i].getNombre()+"";
            cadena+="<input type=submit value=Añadir /> </form>";
            out.println(cadena);         
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
