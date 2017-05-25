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
import javax.servlet.http.HttpSession;
import modelo.Atestado;
import modelo.Consulta;
import modelo.Usuario;

/**
 *
 * @author AC1993
 */
@WebServlet(name = "editarAtestado", urlPatterns = {"/editarAtestado"})
public class editarAtestado extends HttpServlet {

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
        String numeroAtestado  = request.getParameter("numeroAtestado");
        Consulta consulta = new  Consulta();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"image/png\" href=\"https://upload.wikimedia.org/wikipedia/en/1/1f/Universidad_Nacional_Costa_Rica.png\" />");
            out.println("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<link href=\"css/half-slider.css\" rel=\"stylesheet\">");
            out.println("<link href=\"css/estilos.css\" rel=\"stylesheet\">");

            out.println("<title>Editando Atestado</title>");            
            out.println("</head>");
            
            out.println("<body>");
            
            
            out.println("<form action = 'Curriculo?idAtestado="+numeroAtestado+"' method=\"POST\">");
            out.println("<fieldset>\n" +
            "<legend>Modificando Atestado</legend>");
            
            
            
            Conexion con = new Conexion();
                try{
                   // Conexion con bd
                 
                   if (!con.getConexion().isClosed()){
                      Statement st = con.getConexion().createStatement();
                      ResultSet rs = st.executeQuery("SELECT * from tdcurriculos" );

                      // Ponemos los resultados en un table de html
                      
                      out.println(" <table align = 'center' class='table users table-hover'  ><tr><td>Editando ....</td></tr>");
                      while (rs.next())
                         if(String.valueOf(rs.getObject("numeroatestado")).trim()  .equals(String.valueOf(numeroAtestado.trim()))){
                            out.println("<tr><td > Modificando Atestado # </td><td> <input name = 'idAtestado' type='text' value= '"+ rs.getObject("numeroatestado")+"'  disabled></td></tr>");
                            
                          out.println("<tr><td> Curso</td> <td> <input  name = 'areaLaboral'type = 'text' value = "+rs.getObject("area")+" required=\"\"  > </td> </tr>");
                          
                            out.println("<tr><td> Experiencia Laboral</td> <td> <input  name = 'experienciaLaboral'type = 'text' value = "+rs.getObject("experiencialaboral")+" required=\"\"> </td> </tr>");

                            out.println("<tr><td> Experiencia Docente</td> <td> <input  name = 'experienciaDocente' type = 'text' value = "+rs.getObject("experienciadocencia")+" required=\"\"> </td> </tr>");

                            out.println("<tr><td> Descripcion</td> <td> <input name = 'descripcion' type = 'text' value = "+rs.getObject("descripcion")+" required=\"\"> </td> </tr>");

                            out.println("<tr><td> Numero de Referencia 1</td> <td> <input  name = 'numeroReferencia1' type = 'text' value = "+rs.getObject("numerotelefono1")+" required=\"\"> </td> </tr>");

                            out.println("<tr><td> Numero de Referencia 2</td> <td> <input name = 'numeroReferencia2' type = 'text' value = "+rs.getObject("numerotelefono2")+" required=\"\"> </td> </tr>");

                            out.println("<tr><td> Salario</td> <td> <input name = 'salarioBase'type = 'text' value = "+rs.getObject("salario")+" required=\"\"> </td> </tr>");
                            
                            out.println("<tr><td> <a href='Consulta'> <input type=\"button\" value=\"Regresar\"  class=\"btn btn-warning\"> </a>  </td><td> <input type=\"submit\" value=\"Actualizar\"  class=\"btn btn-success\"> </td> </tr>");

                            
                            }
                      }
                    out.println("</table>");
                      con.cerrarConexion();
                   
                }catch(Exception e ){
                }
                out.println("</fieldset>");
                out.println("</form>");
                
                out.println( "</div>");
                out.println( "</body>");
                out.println( "</html>");
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
