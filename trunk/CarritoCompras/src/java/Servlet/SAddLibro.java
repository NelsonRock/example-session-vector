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
@WebServlet(name = "SAddLibro", urlPatterns = {"/SAddLibro"})
public class SAddLibro extends HttpServlet {

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
        try {
            HttpSession sesion=request.getSession(true);
            Vector carro = (Vector)sesion.getAttribute("librosPrestados");
            Double total= (Double)sesion.getAttribute("importe");
            String libros [] = request.getParameterValues("libros");
            Libreria.inicio();
            String cadena="";
            if(carro==null || libros.length == 0)
            {
                carro=new Vector();
                total=0.0;
            }
            else{
                for(int i=0;i<libros.length;i++){
                    carro.add(Libreria.getLibro(libros[i]));
                    Libro aux=Libreria.getLibro(libros[i]);
                    total = total + aux.getPrecio();
                    sesion.setAttribute("librosPrestados", carro);
                    sesion.setAttribute("importe", total);
                }
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SAñadirLibro</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bienvenidos a mi Libreria</h1><img src='libreria.jpg'></br></br>");
            if(carro.isEmpty()==true)
                cadena+="Carro de compra vacio";
            else{
                for(int i=0;i<carro.size();i++){
                    //((Libro)carro.get(i)).getNombre();
                    cadena+="<p>"+((Libro)carro.get(i)).getNombre()+"</p>";
                }
                cadena+="<p>Total de la compra:"+total+"</p>";
            }
            out.println(cadena);
             out.println("<a href='/CarritoCompras/SInicio' >Volver a comprar</a>");
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
