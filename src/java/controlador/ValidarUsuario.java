package controlador;

import modelo.Error;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import modelo.DAO;
import modelo.Usuario;
import static org.eclipse.jdt.internal.compiler.parser.Parser.name;

@WebServlet(name = "ValidarUsuario", urlPatterns = {"/ValidarUsuario"})
public class ValidarUsuario extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        DAO dao  = new DAO();
        String cedula, pass;
        cedula = request.getParameter("idCedula");
        pass = request.getParameter("contrasena");
        Usuario uTemp = new Usuario(cedula, pass);
        
        Usuario uTemp2 =dao.exist(uTemp);
         HttpSession session =  request.getSession();
        if(uTemp2.errorExiste()){
            //Enviar el error al indexx//
            List<Error> error = uTemp2.getListaErrores();
            session.setAttribute("errores", error);
            request.getRequestDispatcher("errorServlet").forward(request,response);
        }else{
            session.setAttribute("usuario", uTemp2);
           // request.sendRedirect("");
            request.getRequestDispatcher("Principal").forward(request,response);
            // redireccionanado..
            
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
