/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AC1993
 */
@WebServlet(name = "verProfesoresServlet", urlPatterns = {"/verProfesoresServlet"})
public class verProfesoresServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            
            
            out.println("<link rel=\"icon\" type=\"image/png\" href=\"https://upload.wikimedia.org/wikipedia/en/1/1f/Universidad_Nacional_Costa_Rica.png\" />");
            out.println("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<link href=\"css/half-slider.css\" rel=\"stylesheet\">");
            out.println("<link href=\"css/estilos.css\" rel=\"stylesheet\">");
        
            
            out.println("<script src='//code.jquery.com/jquery-1.12.4.js'> </script>");
            out.println("<script src='https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js'> </script>    ");
            out.println("<link href='https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css' type='text/css' rel='stylesheet'>");
   
            
   
   
            
            out.println("<title>Listado de Profesores</title>");            
            out.println("</head>");
            out.println("<body>");
            
        out.println("<table id=\"example\" class=\"display\" width=\"100%\" cellspacing=\"0\">");
        out.println("<thead>");
            out.println("<tr>");
                out.println("<th>ID</th>");
          //      out.println("<th>Nombre</th>");
          //      out.println("<th>Apellido 1</th>");
           //     out.println("<th>Apellido 2</th>");
                out.println("<th>Area</th>");
                out.println("<th>Descripcion</th>");
                out.println("<th>Provincia</th>");
                 out.println("<th>Canton</th>");
                out.println("<th>Distrito</th>");
       //         out.println("<th>Ver Detalles</th>");
            out.println("</tr>");
        out.println("</thead>");
        
        out.println("<tfoot>");
            out.println("<tr>");
                out.println("<th>ID</th>");
           //     out.println("<th>Nombre</th>");
            //    out.println("<th>Apellido 1</th>");
            //    out.println("<th>Apellido 2</th>");
                out.println("<th>Area</th>");
                out.println("<th>Descripcion</th>");
                out.println("<th>Provincia</th>");
                out.println("<th>Canton</th>");
                out.println("<th>Distrito</th>");
              //  out.println("<th>Ver Detalles</th>");
            out.println("</tr>");
        out.println("</tfoot>");
        
        out.println("<tbody>");       
        out.println(getProfesores()); 
        out.println("</tbody>");
        
        out.println("</table> ");           
            
            
          
            out.println("</body>");
           
            out.println("<script>");
            out.println("$(document).ready(function() {");
            out.println("$('#example').DataTable();");
            out.println("} );");
            out.println("</script>");
            
            out.println("</html>");
        }
    }
    
       public String getProfesores(){
            String salida = "";
             
                Conexion con = new Conexion();
                try
                {
                   if (!con.getConexion().isClosed()){
                      Statement st = con.getConexion().createStatement();
                      ResultSet rs = st.executeQuery("SELECT idusario,nombre,apellido1,apellido2,area,descripcion,provincia,canton,distrito FROM tbprofesores JOIN tdcurriculos ON(tbprofesores.idusario =  tdcurriculos.iduser)" );

                      // Ponemos los resultados en un table de html
                      
                      while (rs.next()){
                            salida+="<tr>";
                            
                            salida+="<td><a href = 'verDetalles?idProfesor="+rs.getObject("idusario")+"'>"+rs.getObject("idusario")+"</a></td>";
                       //     salida+="<td>"+rs.getObject("idusario")+"</td>";
                 //           salida+="<td>"+rs.getObject("nombre")+"</td>";
                  //          salida+="<td>"+rs.getObject("apellido1")+"</td>";
                   //         salida+="<td>"+rs.getObject("apellido2")+"</td>";
                            salida+="<td>"+rs.getObject("area")+"</td>";
                            salida+="<td>"+rs.getObject("descripcion")+"</td>";
                            salida+="<td>"+rs.getObject("provincia")+"</td>";
                            salida+="<td>"+rs.getObject("canton")+"</td>";
                            salida+="<td>"+rs.getObject("distrito")+"</td>";
                            //salida+="<td><a href = 'verDetalles?idProfesor="+rs.getObject("idusario")+"'>Ver mas Detalles</a></td>";
                            salida+="</tr>";
                      }
                       salida+="<a href = 'welcomeAdmi.jsp'> Regresar </a>";
                      // cierre de la conexion
                      con.cerrarConexion();
                   }
                   else
                      // Error en la conexion
                      salida+="fallo";
                }
                catch (Exception e)
                {
                   // Error en algun momento.
                    salida+="Excepcion "+e.getMessage();
                   
                }
                return  salida;
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
