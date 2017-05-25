/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Atestado;
import modelo.Consulta;
import modelo.Usuario;
@WebServlet(name = "Curriculo", urlPatterns = {"/Curriculo"})
public class Curriculo extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        HttpSession session =  request.getSession();
        Usuario usuario =(Usuario)session.getAttribute("usuario");
        
        
        String idAtestado = request.getParameter("idAtestado");
        
        String curso = request.getParameter("areaLaboral");
        String exLaboral = request.getParameter("experienciaLaboral");
        String exDocente = request.getParameter("experienciaDocente");
        String descripcion = request.getParameter("descripcion");
        String numeroReferencia1 = request.getParameter("numeroReferencia1");
        String numeroReferencia2 = request.getParameter("numeroReferencia2");
        String salarioBase = request.getParameter("salarioBase");
        
         Calendar fecha = new GregorianCalendar();
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaString = dia+"-"+ ++mes+"-"+ano;
        
        int numero = (int) (Math.random()*90000000);
        
        Consulta con = new Consulta();
        try (PrintWriter out = response.getWriter()) {
            out.println("</br>ingresando un nuevo Atestado..</br>");
            out.println("</br>ID  = "+idAtestado+"</br>");
            
            if(idAtestado!=null){
                con.actualizarAtestado(curso,exLaboral,exDocente,descripcion,numeroReferencia1,numeroReferencia2,salarioBase, idAtestado);
                request.getRequestDispatcher("Consulta").forward(request,response);
            }else{
                Atestado atestado = new Atestado(usuario.getUsuario(),curso,
                exDocente,exLaboral,descripcion, numeroReferencia1,numeroReferencia2,salarioBase,
                fechaString,""+numero);
                
                out.println(con.escribirAtestado(atestado));
              //  request.getRequestDispatcher("Consulta").forward(request,response);
              request.getRequestDispatcher("Principal").forward(request,response);
            }
            
            
        }
      
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

 
}
