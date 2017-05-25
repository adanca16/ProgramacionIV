/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author AC1993
 */
public class Error {
    private int numeroError;
    private String descripcion;

    public Error(int numeroError, String descripcion) {
        this.numeroError = numeroError;
        this.descripcion = descripcion;
    }

    public int getNumeroError() {
        return numeroError;
    }

    public void setNumeroError(int numeroError) {
        this.numeroError = numeroError;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String toString(){
        return "Error: "+numeroError+" : "+descripcion;
    }
    
}
