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
public class FormacionAcademica {
    private String idUsuario;
    private String gradoeducacion;
    private String nombrecentroeducativo;
    private String nombretitulo;
    private String fechaobtenido;
    private String anoslaborados;
    private int idformacion;
    
    public FormacionAcademica(){}
    
    public FormacionAcademica(String idUsuario, String gradoeducacion, String nombrecentroeducativo, String nombretitulo, String fechaobtenido, String anoslaborados, int idformacion) {
        this.idUsuario = idUsuario;
        this.gradoeducacion = gradoeducacion;
        this.nombrecentroeducativo = nombrecentroeducativo;
        this.nombretitulo = nombretitulo;
        this.fechaobtenido = fechaobtenido;
        this.anoslaborados = anoslaborados;
        this.idformacion = idformacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getGradoeducacion() {
        return gradoeducacion;
    }

    public void setGradoeducacion(String gradoeducacion) {
        this.gradoeducacion = gradoeducacion;
    }

    public String getNombrecentroeducativo() {
        return nombrecentroeducativo;
    }

    public void setNombrecentroeducativo(String nombrecentroeducativo) {
        this.nombrecentroeducativo = nombrecentroeducativo;
    }

    public String getNombretitulo() {
        return nombretitulo;
    }

    public void setNombretitulo(String nombretitulo) {
        this.nombretitulo = nombretitulo;
    }

    public String getFechaobtenido() {
        return fechaobtenido;
    }

    public void setFechaobtenido(String fechaobtenido) {
        this.fechaobtenido = fechaobtenido;
    }

    public String getAnoslaborados() {
        return anoslaborados;
    }

    public void setAnoslaborados(String anoslaborados) {
        this.anoslaborados = anoslaborados;
    }

    public int getIdformacion() {
        return idformacion;
    }

    public void setIdformacion(int idformacion) {
        this.idformacion = idformacion;
    }
    
    
    
    
}
