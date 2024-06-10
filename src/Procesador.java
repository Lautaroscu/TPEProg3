package TPEProg3.src;

import java.util.ArrayList;
import java.util.List;

public class Procesador implements Comparable<Procesador> {
    private String idProcesador, codigoProcesador;
    private Boolean estaRefrigerado;
    private Integer anioFuncionamiento;
    private List<Tarea> tareasAsignadas;
    private final int MAX_CRITICAS = 2;
    private int criticas;
    private int sumaTiempoTareas;

    public Procesador(String idProcesador, String codigoProcesador, Boolean estaRefrigerado,
            Integer anioFuncionamiento) {
        this.idProcesador = idProcesador;
        this.codigoProcesador = codigoProcesador;
        this.estaRefrigerado = estaRefrigerado;
        this.anioFuncionamiento = anioFuncionamiento;
        tareasAsignadas = new ArrayList<>();
        sumaTiempoTareas = 0;
    }

    public int getSumaTiempoTareas() {
        return sumaTiempoTareas;
    }

    public String getIdProcesador() {
        return this.idProcesador;
    }

    public boolean asignarTarea(Tarea t, int tiempo) {
        this.sumaTiempoTareas += t.getTiempoEjecucion();

        boolean sePuedeAsignar = false;

        if (t.getEsCritica() && criticas == MAX_CRITICAS) {

            System.out.println("Un procesador no puede tener mas de " + MAX_CRITICAS +
                    " tareas criticas asignadas");
        } else {
            if (t.getEsCritica() && cumpleCriticas()) {
                this.criticas++;
            }
            if ((cumpleCriticas() || !t.getEsCritica()) && estaRefrigerado) {
                sePuedeAsignar = true;
            } else if (cumpleCriticas() && !estaRefrigerado) {
                if (cumple(tiempo)) {
                    sePuedeAsignar = true;
                } else {
                    sumaTiempoTareas -= t.getTiempoEjecucion();

                    System.out.println(
                            "El procesador no puede ejecutar tareas las cuales sumadas superen " + tiempo
                                    + " de tiempo sin estar refrigerado");
                }
            }

        }

        if (sePuedeAsignar) {
            tareasAsignadas.add(t);
            return true;
        }
        return false;
    }

    private boolean cumpleCriticas() {
        return criticas <= MAX_CRITICAS;
    }

    public int getSumaTiempos() {
        int suma = 0;
        for (Tarea t : tareasAsignadas) {
            suma += t.getTiempoEjecucion();
        }
        return suma;
    }

    private boolean cumple(int tiempo) {
        return tiempo > this.sumaTiempoTareas;

    }

    public void eliminarTarea(int t) {

        if (tareasAsignadas.remove(t).getEsCritica()) {
            criticas--;
        }
    }

    public int getCriticas() {
        return criticas;
    }

    public int getMAX_CRITICAS() {
        return MAX_CRITICAS;
    }

    public void setTareasAsignadas(List<Tarea> tareasAsignadas) {
        this.tareasAsignadas = tareasAsignadas;
    }

    public List<Tarea> getTareasAsignadas() {
        return tareasAsignadas;
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

    @Override
    public String toString() {
        return "Procesador: {\n" +
                "    idProcesador: " + idProcesador + "\n" +
                "    refrigerado: " + estaRefrigerado + "\n" +
                "    tareas asignadas: " + tareasAsignadas + "\n" +

                "}";
    }

    public Procesador getCopia() {
        Procesador copia = new Procesador(this.idProcesador, this.codigoProcesador, this.estaRefrigerado,
                this.anioFuncionamiento);
        copia.tareasAsignadas.addAll(tareasAsignadas);
        return copia;
    }

    @Override
    public int compareTo(Procesador o) {
        return Integer.compare(this.getSumaTiempos(), o.getSumaTiempos());
    }
}