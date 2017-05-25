package vista;

import controlador.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Atestado;
import modelo.FormacionAcademica;
import modelo.Profesor;

/**
 *
 * @author AC1993
 */
@WebServlet(name = "verDetalles", urlPatterns = {"/verDetalles"})
public class verDetalles extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session =  request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("idProfesor");
        Profesor profesor = getProfesor(id);
        Atestado atestado = getAtestado(id);
        LinkedList<FormacionAcademica> arreglo = new LinkedList<>();
        
        session.setAttribute("usuario", id);
    
   
        
        setFormacionAcademica(id, arreglo);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"icon\" type=\"image/png\" href=\"https://upload.wikimedia.org/wikipedia/en/1/1f/Universidad_Nacional_Costa_Rica.png\" />");
            out.println("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<link href=\"css/half-slider.css\" rel=\"stylesheet\">");
            out.println("<link href=\"css/estilos.css\" rel=\"stylesheet\">");
            out.println("<title>Detalles de  "+id+"</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"col-md-6 col-md-offset-3\">");
            out.println("<table summary='Curriculum Vitae'  class=\"table\" > ");
            out.println("<tr><td colspan = '2'><h1>Curriculum Vitae</h1></td></tr>");   
            out.println("<tr><td ></br><h2>Datos Personales</h2></td>  <td> <img width='100px'  src=\"data:image/jpeg;base64,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\" class=\"img-responsive\" alt=\"Cinque Terre\">  </td></tr>");
            out.println("<tr><td>ID</td> <td>"+profesor.getId()+" </td></tr>");
            out.println("<tr><td>Nombre</td> <td>"+profesor.getNombre()+" </td></tr>");
            out.println("<tr><td>Primer Apellido</td> <td>"+profesor.getApellido1()+" </td></tr>");
            out.println("<tr><td>Segundo Nombre</td> <td>"+profesor.getApellido2()+" </td></tr>");
            out.println("<tr><td>Correo</td> <td>"+profesor.getCorreo()+" </td></tr>");
            out.println("<tr><td>Telefono</td> <td>"+profesor.getTelefono()+" </td></tr>");
            out.println("<tr><td>Direccion</td> <td>"+profesor.getDireccion()+" </td></tr>");
            out.println("</table>");
            
            out.println("<h2>Formacion  Academica</h2>");
            for(FormacionAcademica array : arreglo){ 
                out.println("<table  class=\"table\" > ");
                out.println("<tr><td colspan = '2'> <h4>Formacion en "+array.getNombretitulo()+"</h4> </td></tr>");
                out.println("<tr><td>Grado de Educacion</td> <td>"+array.getGradoeducacion()+" </td></tr>");
                out.println("<tr><td>Nombre del Centro</td> <td>"+array.getNombrecentroeducativo()+" </td></tr>");
                out.println("<tr><td>Titulo</td> <td>"+array.getNombretitulo()+" </td></tr>");
                out.println("<tr><td>Obtenido el:</td> <td>"+array.getFechaobtenido()+" </td></tr>");
                out.println("<tr><td>Trabajo en esta area</td> <td>"+array.getAnoslaborados()+" </td></tr>");
                out.println("</table>");
            }
            
     
            
            out.println("<table  class=\"table\" > ");
            out.println("<tr><td colspan = '2'><h2>Atestado</h2></td></tr>");   
            out.println("<tr><td>Curso</td> <td>"+atestado.getCurso()+" </td></tr>");
            out.println("<tr><td>Experiencia Laboral</td> <td>"+atestado.getExLaboral()+" </td></tr>");
            out.println("<tr><td>Experiencia Docente</td> <td>"+atestado.getExDocente()+" </td></tr>");
            out.println("<tr><td>Descripcion</td> <td>"+atestado.getDescripcion()+" </td></tr>");
            out.println("<tr><td>Numero de Referencia 1</td> <td>"+atestado.getNumeroReferencia1()+" </td></tr>");
            out.println("<tr><td>Numero de Referencia 2</td> <td>"+atestado.getNumeroReferencia2()+" </td></tr>");
            out.println("<tr><td>Salario</td> <td>"+atestado.getSalarioBase()+" </td></tr>");
            out.println("</table>");
            
                    
            out.println("</div>");
            
            out.println("<a href ='verProfesoresServlet'> Regresar</a>");
            
            out.println("</br>");
            out.println("<button type=\"submit\" class=\"btn btn-info\" onclick=\"window.print();\" > <span class=\"glyphicon glyphicon-print\"></span></button>");
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    
       public Profesor getProfesor(String id){
            Profesor pro = new Profesor();
                Conexion con = new Conexion();
                try{
                   if (!con.getConexion().isClosed()){
                      Statement st = con.getConexion().createStatement();
                      ResultSet rs = st.executeQuery("SELECT * FROM tbprofesores");
                      while (rs.next()){
                       if(String.valueOf(rs.getObject( "idusario")).trim().equals(String.valueOf(id.trim()))){
                            pro.setId(rs.getObject("idusario").toString());
                            pro.setNombre(rs.getObject("nombre").toString());
                            pro.setApellido1(rs.getObject("apellido1").toString());
                            pro.setApellido2(rs.getObject("apellido2").toString());
                            pro.setCorreo(rs.getObject("correo").toString());
                            pro.setTelefono(rs.getObject("telefono").toString());
                            pro.setDireccion(rs.getObject("direccion").toString());
                          }
                      }
                   }
                      // cierre de la conexion
                      con.cerrarConexion();
                   }catch (Exception e)
                {
                
                }
                return  pro;
       }

    
       public Atestado getAtestado(String id){
            Atestado atestdo = new Atestado();
                Conexion con = new Conexion();
                try{
                   if (!con.getConexion().isClosed()){
                      Statement st = con.getConexion().createStatement();
                      ResultSet rs = st.executeQuery("SELECT * FROM tdcurriculos");
                      while (rs.next()){
                       if(String.valueOf(rs.getObject( "iduser")).trim().equals(String.valueOf(id.trim()))){
                            atestdo.setId(rs.getObject("iduser").toString());
                            atestdo.setCurso(rs.getObject("area").toString());
                            atestdo.setExLaboral(rs.getObject("experiencialaboral").toString());
                            atestdo.setExDocente(rs.getObject("experienciadocencia").toString());
                            atestdo.setDescripcion(rs.getObject("descripcion").toString());
                            atestdo.setNumeroReferencia1(rs.getObject("numerotelefono1").toString());
                            atestdo.setNumeroReferencia2(rs.getObject("numerotelefono2").toString());     
                            atestdo.setSalarioBase(rs.getObject("salario").toString());
                            atestdo.setFecha(rs.getObject("fecha").toString());
                          }
                      }
                   }
                      // cierre de la conexion
                      con.cerrarConexion();
                   }catch (Exception e)
                {
                
                }
                return  atestdo;
       }   
       public void setFormacionAcademica(String id, LinkedList arreglo){
            FormacionAcademica temp  = null;
                Conexion con = new Conexion();
                try{
                   if (!con.getConexion().isClosed()){
                      Statement st = con.getConexion().createStatement();
                      ResultSet rs = st.executeQuery("SELECT * FROM tbformacionacademica");
                      while (rs.next()){
                       if(String.valueOf(rs.getObject( "idprofesor")).trim().equals(String.valueOf(id.trim()))){
                            temp = new FormacionAcademica();
                            temp.setIdUsuario(rs.getObject("idprofesor").toString());
                            temp.setGradoeducacion(rs.getObject("gradoeduacion").toString());
                            temp.setNombrecentroeducativo(rs.getObject("nombrecentroeducativo").toString());
                            temp.setNombretitulo(rs.getObject("nombretitulo").toString());
                            temp.setFechaobtenido(rs.getObject("fechaobtenido").toString());
                            temp.setAnoslaborados(rs.getObject("anoslaborados").toString());
                            temp.setIdformacion(Integer.parseInt(rs.getObject("idformacion").toString()));                        
                            arreglo.add(temp);
                           }
                          
                      }
                   }
                      con.cerrarConexion();
                   }catch (NumberFormatException | SQLException e)
                {
                
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
