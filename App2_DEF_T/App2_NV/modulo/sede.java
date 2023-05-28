package modulo;

public class sede {
    private String codSede;
    private String ubicacionSede;

    public sede(String codSede, String ubicacionSede) {
        this.codSede = codSede;
        this.ubicacionSede = ubicacionSede;
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
