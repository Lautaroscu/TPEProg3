package TPE;

public class Procesador {
    private String idProcesador, codigoProcesador;
    private Boolean estaRefrigerado;
    private Integer anioFuncionamiento;

    public Procesador(String idProcesador, String codigoProcesador, Boolean estaRefrigerado,
            Integer anioFuncionamiento) {
        this.idProcesador = idProcesador;
        this.codigoProcesador = codigoProcesador;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
    }

    public String getIdProcesador() {
        return this.idProcesador;
    }

    public void setIdProcesador(String idProcesador) {
        this.idProcesador = idProcesador;
    }

    public String getCodigoProcesador() {
        return this.codigoProcesador;
    }

    public void setCodigoProcesador(String codigoProcesador) {
        this.codigoProcesador = codigoProcesador;
    }

    public Boolean isEstaRefrigerado() {
        return this.estaRefrigerado;
    }

    public Boolean getEstaRefrigerado() {
        return this.estaRefrigerado;
    }

    public void setEstaRefrigerado(Boolean estaRefrigerado) {
        this.estaRefrigerado = estaRefrigerado;
    }

    public Integer getAnioFuncionamiento() {
        return this.anioFuncionamiento;
    }

    public void setAnioFuncionamiento(Integer anioFuncionamiento) {
        this.anioFuncionamiento = anioFuncionamiento;
    }
}