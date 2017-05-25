/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.Conexion;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
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
@WebServlet(name = "pdf", urlPatterns = {"/pdf"})
public class pdf extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        
        HttpSession session =  request.getSession();
        String id = (String)session.getAttribute("usuario");
        
         Profesor profesor = getProfesor(id);
        Atestado atestado = getAtestado(id);
        FormacionAcademica [] formacion = getFormacionAcademica(id);
        OutputStream outS = response.getOutputStream();
        
        try  {
            try {
               // Conexion con = new Conexion();
                Document documento = new Document();
                PdfWriter.getInstance(documento, outS);
                documento.open();
                
                
                Paragraph part =  new Paragraph();
                Font fontTitulo = new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.BLACK);
                part.add(new Phrase("Curriculum Vitae", fontTitulo));
                part.setAlignment(1);
                documento.add(part);
                                
                Image imagen = Image.getInstance("C:\\Users\\AC1993\\Documents\\NetBeansProjects\\ProgramacionIV\\Progra4\\web\\pictures\\usuario.jpg");
                imagen.setAlignment(2);
                imagen.scaleToFit(75, 50);    
                documento.add(imagen);
                
                Paragraph informacionPersonal =  new Paragraph();
                Font fontInfoPersonal = new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.BLACK);
                informacionPersonal.add(new Phrase("        Informacion Personal", fontTitulo));
                informacionPersonal.setAlignment(0);
                documento.add(informacionPersonal);
              
                Paragraph part2 =  new Paragraph();
                Font fontTitulo2 = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL,BaseColor.BLACK);
                part2.add(new Phrase("              Nombre          "+profesor.getNombre(), fontTitulo2));
                part2.setAlignment(0);
             //   part2.add(new Phrase(Chunk.NEWLINE));
                part2.add(new Phrase(Chunk.NEWLINE));
                documento.add(part2);
                
                Paragraph part3 =  new Paragraph();
                part3.add(new Phrase("              Apellido  1     "+profesor.getApellido1(), fontTitulo2));
                part3.setAlignment(0);
                part3.add(new Phrase(Chunk.NEWLINE));
           //     part3.add(new Phrase(Chunk.NEWLINE));
                documento.add(part3);
                
                Paragraph part4 =  new Paragraph();
                part4.add(new Phrase("              Apellido  2     "+profesor.getApellido2(), fontTitulo2));
                part4.setAlignment(0);
                //part4.add(new Phrase(Chunk.NEWLINE));
                part4.add(new Phrase(Chunk.NEWLINE));
                documento.add(part4);
                
                Paragraph part5 =  new Paragraph();
                part5.add(new Phrase("              Correo            "+profesor.getCorreo(), fontTitulo2));
                part5.setAlignment(0);
                part5.add(new Phrase(Chunk.NEWLINE));
               // part5.add(new Phrase(Chunk.NEWLINE));
                documento.add(part5);
                
                
                Paragraph part6 =  new Paragraph();
                part6.add(new Phrase("              Telefono        "+profesor.getTelefono(), fontTitulo2));
                part6.setAlignment(0);
                //part6.add(new Phrase(Chunk.NEWLINE));
                part6.add(new Phrase(Chunk.NEWLINE));
                documento.add(part6);
                
                
                
                Paragraph part7 =  new Paragraph();
                part7.add(new Phrase("              Direccion       "+profesor.getDireccion(), fontTitulo2));
                part7.setAlignment(0);
                part7.add(new Phrase(Chunk.NEWLINE));
                documento.add(part7);
                
                 Paragraph partLinea =  new Paragraph();
                partLinea.add(new Phrase("   ____________________________________________________________________________________", fontTitulo2));
                partLinea.setAlignment(0);
                partLinea.add(new Phrase(Chunk.NEWLINE));
                partLinea.add(new Phrase(Chunk.NEWLINE));
                documento.add(partLinea);
                
                
                
                
                
                /*
                PdfPTable tablaUsuario = new PdfPTable(7);
                PdfPCell celda1 = new PdfPCell(new Paragraph("Id",FontFactory.getFont("Arial",12)));
                PdfPCell celda2 = new PdfPCell(new Paragraph("Nombre",FontFactory.getFont("Arial",12)));
                PdfPCell celda3 = new PdfPCell(new Paragraph("Apellido 1",FontFactory.getFont("Arial",12)));
                PdfPCell celda4 = new PdfPCell(new Paragraph("Apellido 2",FontFactory.getFont("Arial",12)));
                PdfPCell celda5 = new PdfPCell(new Paragraph("Telefono",FontFactory.getFont("Arial",12)));
                PdfPCell celda6 = new PdfPCell(new Paragraph("Correo",FontFactory.getFont("Arial",12)));
                PdfPCell celda7 = new PdfPCell(new Paragraph("Direccion",FontFactory.getFont("Arial",12)));
                
                
                tablaUsuario.addCell(celda1);
                tablaUsuario.addCell(celda2);
                tablaUsuario.addCell(celda3);
                tablaUsuario.addCell(celda4);
                tablaUsuario.addCell(celda5);
                tablaUsuario.addCell(celda6);
                tablaUsuario.addCell(celda7);
                
                 try{
                   if (!con.getConexion().isClosed()){
                      Statement st = con.getConexion().createStatement();
                      ResultSet rs = st.executeQuery("SELECT * FROM tbprofesores");
                      while (rs.next()){
                       if(String.valueOf(rs.getObject( "idusario")).trim().equals(String.valueOf(id.trim()))){
                            tablaUsuario.addCell(rs.getObject("idusario").toString());
                            tablaUsuario.addCell(rs.getObject("nombre").toString());
                            tablaUsuario.addCell(rs.getObject("apellido1").toString());
                            tablaUsuario.addCell(rs.getObject("apellido2").toString());
                            tablaUsuario.addCell(rs.getObject("correo").toString());
                            tablaUsuario.addCell(rs.getObject("telefono").toString());
                            tablaUsuario.addCell(rs.getObject("direccion").toString());
                          }
                      }
                   }
                      // cierre de la conexion
                      con.cerrarConexion();
                   }catch (Exception e)
                {
                
                }
                 
                
                
                documento.add(tablaUsuario);
                */
                documento.close();
                
            } catch (DocumentException e) {           e.getMessage();}
            
            
        }finally{
            outS.close();
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
       public FormacionAcademica [] getFormacionAcademica(String id){
            FormacionAcademica vector[] = new FormacionAcademica[10];
                Conexion con = new Conexion();
                try{
                   if (!con.getConexion().isClosed()){
                      Statement st = con.getConexion().createStatement();
                      ResultSet rs = st.executeQuery("SELECT * FROM tbformacionacademica");
                      while (rs.next()){
                       if(String.valueOf(rs.getObject( "idprofesor")).trim().equals(String.valueOf(id.trim()))){
                           for(int i = 0 ; i < vector.length;i++){
                               if(vector[i] == null){
                                    vector[i] = new FormacionAcademica();
                                    vector[i].setIdUsuario(rs.getObject("idprofesor").toString());
                                    vector[i].setGradoeducacion(rs.getObject("gradoeduacion").toString());
                                    vector[i].setNombrecentroeducativo(rs.getObject("nombrecentroeducativo").toString());
                                    vector[i].setNombretitulo(rs.getObject("nombretitulo").toString());
                                    vector[i].setFechaobtenido(rs.getObject("fechaobtenido").toString());
                                    vector[i].setAnoslaborados(rs.getObject("anoslaborados").toString());
                                    vector[i].setIdformacion(Integer.parseInt(rs.getObject("idformacion").toString()));                        
                                   i = vector.length;
                               }
                           }
                           
                          }
                      }
                   }
                      con.cerrarConexion();
                   }catch (Exception e)
                {
                
                }
                return  vector;
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
