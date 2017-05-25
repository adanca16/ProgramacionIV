package modelo;

/**
 *
 * @author AC1993
 */
public class Atestado {
        String id;
        String curso ;
        String exLaboral;
        String exDocente ;
        String descripcion ;
        String numeroReferencia1;
        String numeroReferencia2;
        String salarioBase ;
        String fecha ;
        String idAtestado;
    public Atestado (){}
    public Atestado(String id,String curso,  String exLaboral, String exDocente, String descripcion, String numeroReferencia1, String numeroReferencia2, String salarioBase,String fecha,String idAtestado) {
        this.id = id;
        this.curso = curso;
        this.exLaboral = exLaboral;
        this.exDocente = exDocente;
        this.descripcion = descripcion;
        this.numeroReferencia1 = numeroReferencia1;
        this.numeroReferencia2 = numeroReferencia2;
        this.salarioBase = salarioBase;
        this.fecha = fecha;
        this.idAtestado = idAtestado;
    }

    
      
    
    
    public String getIdAtestado() {
        return idAtestado;
    }

    public void setIdAtestado(String idAtestado) {
        this.idAtestado = idAtestado;
    }
    
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    public String getFecha() {
        return fecha;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this. id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getExLaboral() {
        return exLaboral;
    }

    public void setExLaboral(String exLaboral) {
        this.exLaboral = exLaboral;
    }

    public String getExDocente() {
        return exDocente;
    }

    public void setExDocente(String exDocente) {
        this.exDocente = exDocente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroReferencia1() {
        return numeroReferencia1;
    }

    public void setNumeroReferencia1(String numeroReferencia1) {
        this.numeroReferencia1 = numeroReferencia1;
    }

    public String getNumeroReferencia2() {
        return numeroReferencia2;
    }

    public void setNumeroReferencia2(String numeroReferencia2) {
        this.numeroReferencia2 = numeroReferencia2;
    }

    public String getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(String salarioBase) {
        this.salarioBase = salarioBase;
    }
        
        
        
        
    
    
}
