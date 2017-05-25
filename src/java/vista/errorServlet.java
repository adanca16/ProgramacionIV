
package vista;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "errorServlet", urlPatterns = {"/errorServlet"})
public class errorServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession  session= request.getSession();
       // Error error = (Error)session.getAttribute("errores");
         List<Error> error = (List<Error>)session.getAttribute("errores");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");            
            out.println("<style>");            
            out.println("h1 { color:red;}");            
            out.println("</style>");            
            out.println("</head>");
            out.println("<body>");
            out.println("</br></br></br><h1 align = center >ERROR </br></h1>");
            if(error!=null){
                for(int i = 0 ; i <error.size();i++){
                out.println("<h4 align ='center'>"+error.get(i)+"</h4>");
            }
            }else{
                out.println("<h3 align = 'center' >No se puede acceder a este sitio mediante este modo.</h3>");
            }
            
            
            out.println("<h3 align = center ><a href ='index.html'>Regresar a la pagina principal</a></h3>");
            out.println("</body>");
            out.println("</html>");
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
