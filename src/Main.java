package TPEProg3.src;

public class Main {
    public static void main(String[] args) throws Exception {
        Servicios s1 = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");
        System.out.println(s1.asignarTareasBacktracking(65));
    }
}
