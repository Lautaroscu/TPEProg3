package TPEProg3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import TPEProg3.utils.CSVReader;

public class Servicios {
    private HashMap<String, Tarea> tareas = new HashMap<>();
    private ArrayList<Tarea> tareasCriticas = new ArrayList<>();
    private ArrayList<Tarea> tareasNoCriticas = new ArrayList<>();
    private int tiempoMenor = Integer.MAX_VALUE;
    private int estados;

    private List<Procesador> procesadores = new ArrayList<>();
    private List<Procesador> solucionFinalProcesadores = new ArrayList<>();

    /**
     * NO modificar la interfaz de esta clase ni sus métodos públicos.
     * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
     * de implementación.
     */

    /*
     * Expresar la complejidad temporal del constructor.
     */
    // Complejidad Temporal del Constructor :
    // O(n) PD : aca nose si deberia haber sumando readProcesesor + readTask son
    // ambos O(n) ,no
    // recurdo si se suman los O(n) o se puede simplificar en un O(n)

    public Servicios(String pathProcesadores, String pathTareas) {
        CSVReader reader = new CSVReader();

        reader.readProcessors(pathProcesadores, procesadores);
        reader.readTasks(pathTareas, tareas, tareasCriticas, tareasNoCriticas); // llena las estructuras
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     */
    // O(1) o un aproximado dependiendo la implementacion de sus RS en la libreria
    // "HashMap"
    public Tarea servicio1(String ID) {
        return tareas.get(ID);
    }

    /*
     * Expresar la complejidad temporal del servicio 2.
     */
    // Complejidad Temporal :
    // O(1)
    public List<Tarea> servicio2(boolean esCritica) {

        if (esCritica)
            return tareasCriticas;

        return tareasNoCriticas;

    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
    // Complejidad Temporal :
    // O(n)
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        final int PRIORIDAD_MIN = 0, PRIORIDAD_MAX = 100;

        if ((prioridadInferior > PRIORIDAD_MAX || prioridadInferior < PRIORIDAD_MIN)
                || prioridadSuperior < PRIORIDAD_MIN || prioridadSuperior > PRIORIDAD_MAX) {
            throw new IllegalArgumentException(
                    "La prioridad inferior debe ser mayor o igual a " + PRIORIDAD_MIN + " \n" +
                            " y la prioridad superior debe ser menor o igual a" + PRIORIDAD_MAX);
        }

        return tareas.values()
                .stream()
                .filter(tarea -> tarea.getNivelDePrioridad() >= prioridadInferior
                        && tarea.getNivelDePrioridad() <= prioridadSuperior)
                .collect(Collectors.toList());
    }

    private int getTiempoMenor(int tiempo) {
        ArrayList<Tarea> tareasList = new ArrayList<Tarea>(this.tareas.values());
        ArrayList<Tarea> verTareas = new ArrayList<>();

        for (Procesador p : procesadores) {
            buscarTiempoMenor(p, tareasList.get(0), tareasList, 0, verTareas, tiempo);

        }
        return this.tiempoMenor;

    }

    private void buscarTiempoMenor(Procesador p, Tarea tarea, ArrayList<Tarea> tareas, int index,
            ArrayList<Tarea> verTareas, int tiempo) {
        if (!p.asignarTarea(tarea, tiempo)) {
            return;
        }

        verTareas.add(tarea);

        if (getTiempoMayorProcesador() > this.tiempoMenor) {
            verTareas.remove(verTareas.size() - 1);
            p.eliminarTarea(p.getTareasAsignadas().size() - 1);

            return;
        }
        System.out.println(verTareas);

        if (index < tareas.size() - 1) {
            Tarea t = tareas.get(index + 1);

            for (Procesador pr : procesadores) {
                buscarTiempoMenor(pr, t, tareas, index + 1, verTareas, tiempo);
            }
        }
        estados++;

        if (verTareas.size() == tareas.size())

        {
            if (this.tiempoMenor > getTiempoMayorProcesador()) {
                this.tiempoMenor = getTiempoMayorProcesador();
                this.solucionFinalProcesadores.clear();
                guardarSolucion();

            }
        }

        verTareas.remove(verTareas.size() - 1);
        p.eliminarTarea(p.getTareasAsignadas().size() - 1);

    }

    private int getTiempoMayorProcesador() {
        int sumaMayor = 0;

        for (int i = 0; i < procesadores.size(); i++) {
            int suma = procesadores.get(i).getSumaTiempos();
            if (suma > sumaMayor) {
                sumaMayor = suma;
            }
        }
        return sumaMayor;
    }

    public Solucion asignarTareas(int tiempo) {
        int tiempoMaximo = this.getTiempoMenor(tiempo);
        Solucion solucion = new Solucion(solucionFinalProcesadores, tiempoMaximo, this.estados);

        return solucion;
    }

    private void guardarSolucion() {
        for (Procesador p : procesadores) {
            solucionFinalProcesadores.add(p.getCopia());
        }
    }
}
