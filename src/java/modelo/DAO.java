
package modelo;
public class DAO {
    Consulta con;
    public Usuario exist(Usuario u){
        con = new Consulta();
        
        if(con.getLoginAceptador(u.getUsuario(), u.getPass())){
            return new Usuario(u.getUsuario(), u.getPass(),con.getNombre(u.getUsuario()),con.tipoAcceso());
        }else{
                Error error = new Error(403,"No se ha encontrado el usuario "+u.getUsuario());
                Usuario usuario = new Usuario(u.getUsuario(), u.getPass(), "null","null");
                usuario.setError(error);
                
                return  usuario;
            }
    }
}
