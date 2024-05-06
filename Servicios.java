package TPEProg3;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import TPEProg3.utils.CSVReader;

public class Servicios {

    private HashMap<String, Tarea> tareas;

    /**
     * NO modificar la interfaz de esta clase ni sus métodos públicos.
     * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
     * de implementación.
     */

    /*
     * Expresar la complejidad temporal del constructor.
     */
    // Complejidad Temporal del Constructor :
    // O(n) al cuadrado * 2
    public Servicios(String pathProcesadores, String pathTareas) {
        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores);
        tareas = reader.readTasks(pathTareas);
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
    // O(n)
    public List<Tarea> servicio2(boolean esCritica) {

        return tareas.values()
                .stream()
                .filter(tarea -> tarea.getEsCritica().equals(esCritica))
                .collect(Collectors.toList());

    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
    // Complejidad Temporal :
    // O(n)
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        final int PRIORIDAD_MIN = 0, PRIORIDAD_MAX = 100;

        if (prioridadInferior < PRIORIDAD_MIN || prioridadSuperior > PRIORIDAD_MAX) {
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

}
