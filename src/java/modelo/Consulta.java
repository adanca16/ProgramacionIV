/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AC1993
 */
@WebServlet(name = "Consulta", urlPatterns = {"/Consulta"})
public class Consulta extends HttpServlet {
    private String tipoUser;
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
          
            out.println("<title>Listado de Curriculos</title>");            
            out.println("</head>");
            out.println("<body>");
            
            HttpSession session =  request .getSession();
            Usuario usuario = (Usuario)session.getAttribute("usuario");
            
            
            out.println("<h1 align = 'center'> Listado de Curriculos de  "+usuario.getNombre()+"</h1>");
            out.println("<h3>"+ getCurriculos(usuario.getUsuario())+"</h3>");
            
           
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    public String escribirAtestado(Atestado atestado){
        String salida = "";
        Conexion con = new Conexion();
        try{
            if (!con.getConexion().isClosed()){
            Statement st = con.getConexion().createStatement();  
                String sql = "INSERT INTO tdcurriculos (iduser,area,experiencialaboral,experienciadocencia,descripcion,numerotelefono1,numerotelefono2,salario,fecha, numeroatestado) "
            + "VALUES ('"+atestado.getId()+"','"+atestado.getCurso()+"','"+atestado.getExLaboral()+"',"
                        + "'"+atestado.getExDocente()+"','"+atestado.getDescripcion()+"','"+atestado.getNumeroReferencia1()+"',"
                        + "'"+atestado.getNumeroReferencia2()+"','"+atestado.getSalarioBase()+"','"+atestado.getFecha()+"','"+atestado.getIdAtestado()+"');";
                
            st.executeUpdate(sql);
            salida = "Insertado con exito";
            }
        }catch (Exception e){
            salida = e.getMessage();
        }
        return salida;
    }
    
       public String getCurriculos(String id){
                String salida = "<div class='table-responsive'  style='margin-top: 10px;'>";
                Conexion con = new Conexion();
                try
                {
                   if (!con.getConexion().isClosed()){
                      Statement st = con.getConexion().createStatement();
                      ResultSet rs = st.executeQuery("SELECT * from tdcurriculos" );

                      // Ponemos los resultados en un table de html
                      salida+=" <table align = 'center' class = 'table users table-hover'  ><tr ><td>Area</td><td>Experiencia Laborar</td><td>Experiencia Docencia</td><td>Descripcion</td><td>Numero Referencia 1</td> <td>Numero Referencia 2</td> <td>Salario</td><td>Fecha</td> <td colspan = '2'><a href = 'addCurriculum.jsp'>Agregar Nuevo </a></td></tr>";
                      while (rs.next())
                      {
                         if(String.valueOf(rs.getObject( "iduser")).trim()  .equals(String.valueOf(id.trim()))){
                            salida+="<tr>";
                            salida+="<td>"+rs.getObject("area")+"</td>";
                            salida+="<td>"+rs.getObject("experiencialaboral")+"</td>";
                            salida+="<td>"+rs.getObject("experienciadocencia")+"</td>";
                            salida+="<td>"+rs.getObject("descripcion")+"</td>";
                            salida+="<td>"+rs.getObject("numerotelefono1")+"</td>";
                            salida+="<td>"+rs.getObject("numerotelefono2")+"</td>";
                            salida+="<td>"+rs.getObject("salario")+"</td>";
                             salida+="<td>"+rs.getObject("fecha")+"</td>";
                            salida+="<td><a href='editarAtestado?numeroAtestado="+rs.getObject("numeroatestado")+"'> <input type=\"button\" value=\"Editar\"  class=\"btn btn-warning\"> </a></td>";
                            salida+="<td><a href='eliminarAtestado?numeroAtestado="+rs.getObject("numeroatestado")+"'> <input type=\"button\" value=\"Eliminar\"  class=\"btn btn-danger\"> </a></td>";
                        
                            
                            salida+="</tr>";
                         }
                      }
                       salida+="</table>";

                       salida+="<a href = 'welcomeUser.jsp'> Regresar </a> </div>";
                       
                       
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
    
    
       public boolean getLoginAceptador(String id, String pass){
           tipoUser= "";
            boolean salida = false;
            Conexion con = new Conexion();
            try
            {
               // Conexion con bd

               if (!con.getConexion().isClosed()){
                  Statement st = con.getConexion().createStatement();
                  ResultSet rs = st.executeQuery("SELECT * from tblogin" );

                  while (rs.next())
                  {
                     if(String.valueOf(rs.getObject( "idusuario")).trim()  .equals(String.valueOf(id.trim())) 
                             && String.valueOf(rs.getObject( "passwordusario")).trim() .equals(String.valueOf(pass.trim()))){
                        tipoUser = rs.getObject("tipousuario").toString();
                         salida = true;
                     }
                  }

                  con.cerrarConexion();
               }

            }
            catch (Exception e)
            {
            }
            return  salida;
   }
       public String  getNombre(String cedula){
           
           String usuario ="";
           Conexion con = new Conexion();
            try
            {
               // Conexion con bd

               if (!con.getConexion().isClosed()){
                  Statement st = con.getConexion().createStatement();
                  ResultSet rs = st.executeQuery("SELECT * from tbprofesores" );

                  while (rs.next()){
                     if(String.valueOf(rs.getObject( "idusario")).trim().equals(String.valueOf(cedula.trim()))){
                         usuario = rs.getObject( "nombre").toString();
                     }
                  }

                  con.cerrarConexion();
               }

            }
            catch (SQLException e)
            {
            }
            return  usuario;
       }
       
       public String tipoAcceso(){return tipoUser;}
       
       public boolean getIDNoFound(String id){
           boolean found = true;  
           Conexion con = new Conexion();
            try
            {
               // Conexion con bd

               if (!con.getConexion().isClosed()){
                  Statement st = con.getConexion().createStatement();
                  ResultSet rs = st.executeQuery("SELECT * from tblogin" );

                  while (rs.next()){
                     if(String.valueOf(rs.getObject( "idusuario")).trim().equals(String.valueOf(id.trim()))){
                         found  = false;
                     }
                  }
                  con.cerrarConexion();
               }
            }
            catch (SQLException e)
            {
            }
           return found;
       }
       
       public void actualizarAtestado(String area,String expLa,String expDo,String descripcion, String tel1,String tel2,String salario, String numeroAtestado){
          Conexion con = new Conexion();
          String cadSQL = "";
            try{
               // Conexion con bd

               if (!con.getConexion().isClosed()){
                  Statement st = con.getConexion().createStatement();
		//inicialixo la sentencia SQL
                //UPDATE tdcurriculos SET descripcion='Adan' WHERE numeroatestado = 2587071
                    cadSQL = "UPDATE tdcurriculos  SET area='"+area+"', experiencialaboral='"+expLa+"',experienciadocencia = '"+expDo+"'"
                            + ",descripcion = '"+descripcion+"',numerotelefono1 = '"+tel1+"',numerotelefono2 = '"+tel2+"',salario = "+salario
                            + "	WHERE numeroatestado ='"+numeroAtestado+"'"  ;
                    st.executeUpdate(cadSQL);//actualizo
               }
            }
            catch (SQLException e) {//caso de algun error.    
                e.printStackTrace();
            }
           
                
           
       }
       public Atestado getAtestado(String idAtestado){
           Conexion con = new Conexion();
           Atestado atestado  = new Atestado("", "", "", "", "", "", "", "", "", idAtestado);
            try
            {
               // Conexion con bd

               if (!con.getConexion().isClosed()){
                  Statement st = con.getConexion().createStatement();
                  ResultSet rs = st.executeQuery("SELECT * from tdcurriculos" );
                  while (rs.next()){
                     if(String.valueOf(rs.getObject( "numeroatestado")).trim().equals(String.valueOf(idAtestado.trim()))){
                         atestado.setCurso("area");
                         atestado.setExLaboral(rs.getObject("experiencialaboral").toString().trim());
                         atestado.setExDocente(rs.getObject("experienciadocencia").toString().trim());
                         atestado.setDescripcion(rs.getObject("descripcion").toString().trim());
                         atestado.setNumeroReferencia1(rs.getObject("numerotelefono1").toString().trim());
                         atestado.setNumeroReferencia2(rs.getObject("numerotelefono2").toString().trim());
                         atestado.setSalarioBase(rs.getObject("salario").toString().trim());
                         
                         
                     }
                  }
                  con.cerrarConexion();
               }
            }
            catch (SQLException e)
            {
            }
           return atestado;
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

    public void escribirLogin(Conexion con, String id, String contrasena, String acceso) {
        try{
            if (!con.getConexion().isClosed()){
            Statement st = con.getConexion().createStatement();  
                String sql = "INSERT INTO tblogin (idusuario,passwordusario,tipousuario) "
            + "VALUES ('"+id+"','"+contrasena+ "','"+acceso+"');";
            st.executeUpdate(sql);
            }
        }catch (Exception e){
        }  
    }

    public void escribirProfesor(Conexion con,Profesor profesor) {
         try{
            if (!con.getConexion().isClosed()){
            Statement st = con.getConexion().createStatement();  
                String sql = "INSERT INTO tbprofesores (idusario,nombre,apellido1,apellido2,correo,telefono,provincia,canton,distrito,direccion) "
            + "VALUES ('"+profesor.getId()+"','"+profesor.getNombre()+ "','"+profesor.getApellido1()+"','"
                        +profesor.getApellido2()+"','"+profesor.getCorreo()+"','"+profesor.getTelefono()+"','"+profesor.getProvincia()+"','"+profesor.getCanton()+"','"+profesor.getDistrito()+"','"+profesor.getDireccion()+"');";
            st.executeUpdate(sql);
            }
        }catch (Exception e){
        }  
    }

    public boolean eliminarAtestado(String numerAtestado){
        Conexion con = new Conexion();
        boolean eliminado = false;
        try{
            if (!con.getConexion().isClosed()){
            Statement st = con.getConexion().createStatement();  
            //DELETE FROM tdcurriculos WHERE numeroatestado ='41007050';
              String sql = "DELETE FROM tdcurriculos WHERE numeroatestado = '"+numerAtestado+"';";
            st.executeUpdate(sql);
            eliminado = true;
            }
        }catch (Exception e){
        }
        return eliminado;
    }
    
    
    
       public Profesor getProfesor(String id){
           Profesor profesor = new Profesor();
    
            Conexion con = new Conexion();
            try
            {
               // Conexion con bd

               if (!con.getConexion().isClosed()){
                  Statement st = con.getConexion().createStatement();
                  ResultSet rs = st.executeQuery("SELECT * from tbprofesores" );

                  while (rs.next()){
                     if(String.valueOf(rs.getObject( "idusario")).trim()  .equals(String.valueOf(id.trim()))){
                         profesor.setId("idusario");
                         profesor.setNombre("nombre");
                         profesor.setApellido1("apellido1");
                         profesor.setApellido1("apellido2");
                         profesor.setCorreo("correo");
                         profesor.setTelefono("telefono");
                         profesor.setDireccion("direccion");
                     }
                  }

                  con.cerrarConexion();
               }

            }
            catch (Exception e)
            {
            }
            return  profesor;
   }
       

}