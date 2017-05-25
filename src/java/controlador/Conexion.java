package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
        //Variables necesarias para iniciar la coneccion
        private String user, password, cadenaConeccion;
        private Connection conex = null;
        public Conexion(){
                user= "postgres";
                password = "12345";
                cadenaConeccion = "jdbc:postgresql://localhost:5432/dbreclutamiento";
        }
        /*Metodo para devolver la coneccion, por lo que desde donde se llame este retornara la 
         * variable coeccion
         * */
        public Connection getConexion(){
                
                try {
                        Class.forName("org.postgresql.Driver");		
                        conex = DriverManager.getConnection(cadenaConeccion,user,password);
                } catch (Exception e) {
                        System.out.println("Connection Failed! Check output console "+ e.getMessage() );
                }
                return conex;
        }
        
        public void cerrarConexion(){try{conex.close();}catch(Exception r){}}
}