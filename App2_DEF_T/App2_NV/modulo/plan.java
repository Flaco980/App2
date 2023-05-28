package modulo;

public class plan {
    private String codPlan;
    private String descripcionPlan;

    public plan(String codPlan, String descripcionPlan) {
        this.codPlan = codPlan;
        this.descripcionPlan = descripcionPlan;
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
}
