package vista;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.Error;

/**
 *
 * @author AC1993
 */
@WebServlet(name = "menuServlet", urlPatterns = {"/Principal"})
public class menuServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
          HttpSession session =  request .getSession();
          Usuario usuario = (Usuario)session.getAttribute("usuario");
          
          if(usuario == null) {// caso para el bloqueo de la url
            usuario = new Usuario("","");
            Error error = new Error(501,"Debes de iniciar seccion");
            usuario.setError(error);
            List<Error> errores = usuario.getListaErrores();
            session.setAttribute("errores", errores);
            request.getRequestDispatcher("errorServlet").forward(request,response);
          }else{  
            session.setAttribute("user", usuario);
                if(usuario.getUsuarioTipo().trim().equals("admin".trim())){
                    request.getRequestDispatcher("welcomeAdmi.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("welcomeUser.jsp").forward(request, response);
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

    private boolean session(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
