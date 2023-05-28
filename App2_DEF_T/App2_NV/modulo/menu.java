package modulo;

import java.util.Scanner;
import java.util.List;


public class menu {
    private Scanner scanner;
    private proceso proceso;
    private List<plan> planes;
    private List<sede> sedes; 

    public menu(proceso proceso, List<plan> planes, List<sede> sedes) {
        scanner = new Scanner(System.in);
        this.proceso = proceso;
        this.planes = planes;
        this.sedes = sedes;
    }
    
    public void mostrar() {
        
        int opcion = 0;
        do {

            // Mostrar la cantidad de clientes
            System.out.println("--------------------------");
            System.out.println("Cantidad de clientes: " + proceso.getCantidadClientes());
            System.out.println("-----------MENU-----------");

            System.out.println("Seleccione una opción (número):");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Quitar cliente");
            System.out.println("3. Editar cliente");
            System.out.println("4. Agregar plan");
            System.out.println("5. Quitar plan");
            System.out.println("6. Cambiar plan");
            System.out.println("7. Agregar sede");
            System.out.println("8. Quitar sede");
            System.out.println("9. Cambiar sede");
            System.out.println("10. Buscar cliente");
            System.out.println("11. Salir");
            opcion = scanner.nextInt();
            
            switch(opcion) {
                case 1:
                    proceso.agregarCliente(planes, sedes);
                    break;
                case 2:
                    proceso.quitarCliente();
                    break;
                case 3:
                    proceso.editarCliente();
                    break;
                case 4:
                    proceso.agregarPlan();
                    break;
                case 5:
                    proceso.quitarPlan();
                    break;
                case 6:
                    proceso.cambiarPlan();
                    break;
                case 7:
                    proceso.agregarSede();
                    break;
                case 8:
                    proceso.quitarSede();
                    break;
                case 9:
                    proceso.cambiarSede();
                    break;
                case 10:
                    proceso.buscarCliente();
                    break;
                case 11:
                    proceso.hacerBackup("bigmuscle2.csv");
                    proceso.guardarClientesEnArchivo();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while(opcion != 11);
    }
}
