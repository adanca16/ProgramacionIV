package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AC1993
 */
public class Usuario {
    private String id;
    private String contrasena;
    private String nombre;
    private String tipoUsuario;
    private List<Error> errores;
    
    public Usuario(String id, String contrasena){
        this.id = id;
        this.contrasena = contrasena;
        errores = new ArrayList<Error>();
    }
    public Usuario(String id, String contrasena, String nombre,String tipo){
        this.id = id;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.tipoUsuario = tipo;
        errores = new ArrayList<Error>();
    }
    
    public boolean errorExiste(){
        return !errores.isEmpty();
    }
    public void setError(Error error){
        errores.add(error);
    }
    
    public void setID(String id){
        this.id = id;
    }
    public String getUsuario(){return id;}
    
    public void setContrasena(String pass){
        this.contrasena = pass;
    }
    public String getPass(){return contrasena;}
    
    public void setNombre(String pass){
        this.nombre = pass;
    }
    public String getNombre(){return nombre;}
    
      public void setTipoUsuario(String id){
        this.tipoUsuario = id;
    }
    public String getUsuarioTipo(){return tipoUsuario;}
    
    
    public List getListaErrores(){return  errores;}

    
}