/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Consulta;
import modelo.FormacionAcademica;
import modelo.Usuario;
/**
 *
 * @author AC1993
 */
@WebServlet(name = "servletRegistrandoFormacionAcademica", urlPatterns = {"/servletRegistrandoFormacionAcademica"})
public class servletRegistrandoFormacionAcademica extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         HttpSession session =  request.getSession();
         Usuario user = (Usuario)session.getAttribute("usuario");
         
        String idUsuario = user.getUsuario();
        
        
        Consulta con  = new Consulta();
        Conexion conexion = new Conexion();
       
        String gradotitulo = request.getParameter("gradoAcademico");
        String centroEducativoTitulo = request.getParameter("centroTitulo");
        String nombreTitulo = request.getParameter("nombreTitulo");
        String fechaObtenido = request.getParameter("tituloObtenido");
        String areaLaboral = request.getParameter("areaLaboral");
        int numeroFormacion = (int) (Math.random()*9000000);
        FormacionAcademica formacion = new FormacionAcademica(idUsuario, gradotitulo, centroEducativoTitulo, nombreTitulo, fechaObtenido,areaLaboral, numeroFormacion);
        escribirFormacion(conexion, formacion);     
        request.getRequestDispatcher("addCurriculum.jsp").forward(request,response);
        
    }
    

 private void escribirFormacion(Conexion con,FormacionAcademica formacion) {
         try{
            if (!con.getConexion().isClosed()){
            Statement st = con.getConexion().createStatement();  
            
            //INSERT INTO tbformacionacademica () VALUES ('A','B','C','D','E','F',24242);
                String sql = "INSERT INTO tbformacionacademica (idprofesor,gradoeduacion,nombrecentroeducativo,nombretitulo,fechaobtenido,anoslaborados,idformacion) "
            + "VALUES ('"+formacion.getIdUsuario()+"','"+formacion.getGradoeducacion()+ "','"+formacion.getNombrecentroeducativo()+"','"
                        +formacion.getNombretitulo()+"','"+formacion.getFechaobtenido()+"','"+formacion.getAnoslaborados()+"',"+formacion.getIdformacion()+");";
            st.executeUpdate(sql);
            }
        }catch (Exception e){
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
