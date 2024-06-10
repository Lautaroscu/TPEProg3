package TPEProg3;

public class Main {
    public static void main(String[] args) throws Exception {
        Servicios s1 = new Servicios("./TPEProg3/datasets/Procesadores.csv", "./TPEProg3/datasets/Tareas.csv");
        System.out.println(s1.asignarTareasBacktracking(65));
    }
}
