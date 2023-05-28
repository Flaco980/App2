package modulo;

//Clase cliente
public class cliente {
    private String rut;
    private String nombreCompleto;
    private int edad;
    private String codPlan;
    private String descripcionPlan;
    private String desde;
    private String hasta;
    private String codSede;
    private String ubicacionSede;

    // Constructor de la clase
    public cliente(String rut, String nombreCompleto, int edad, String codPlan, String descripcionPlan, String desde, String hasta, String codSede, String ubicacionSede) {
        this.rut = rut;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.codPlan = codPlan;
        this.descripcionPlan = descripcionPlan;
        this.desde = desde;
        this.hasta = hasta;
        this.codSede = codSede;
        this.ubicacionSede = ubicacionSede;
    }
  
    // Métodos getter y setter
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCodPlan() {
        return codPlan;
    }

    public void setCodPlan(String codPlan) {
        this.codPlan = codPlan;
    }

    public String getDescripcionPlan() {
        return descripcionPlan;
    }

    public void setDescripcionPlan(String descripcionPlan) {
        this.descripcionPlan = descripcionPlan;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getCodSede() {
        return codSede;
    }

    public void setCodSede(String codSede) {
        this.codSede = codSede;
    }

    public String getUbicacionSede() {
        return ubicacionSede;
    }

    public void setUbicacionSede(String ubicacionSede) {
        this.ubicacionSede = ubicacionSede;
    }
}
