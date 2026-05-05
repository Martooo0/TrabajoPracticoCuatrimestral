package view;

import controller.ControladorOdontologo;
import controller.ControladorPaciente;
import controller.ControladorTurno;

import java.util.Scanner;

public class VistaPrincipal {

    private final VistaPaciente vistaPaciente;
    private final VistaOdontologo vistaOdontologo;
    private final VistaTurno vistaTurno;
    private final Scanner scanner;

    public VistaPrincipal(ControladorPaciente controladorPaciente,
                          ControladorOdontologo controladorOdontologo,
                          ControladorTurno controladorTurno,
                          Scanner scanner) {
        this.vistaPaciente   = new VistaPaciente(controladorPaciente, scanner);
        this.vistaOdontologo = new VistaOdontologo(controladorOdontologo, scanner);
        this.vistaTurno      = new VistaTurno(controladorTurno, scanner);
        this.scanner = scanner;
    }

    public void iniciar() {
        System.out.println("=========================================");
        System.out.println("  SISTEMA DE GESTIÓN - CLÍNICA ODONTOLÓGICA");
        System.out.println("=========================================");

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Pacientes");
            System.out.println("2. Odontólogos");
            System.out.println("3. Turnos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1": vistaPaciente.mostrarMenu(); break;
                case "2": vistaOdontologo.mostrarMenu(); break;
                case "3": vistaTurno.mostrarMenu(); break;
                case "0":
                    continuar = false;
                    System.out.println("Sistema Cerrado Correctamente");
                    break;
                default: System.out.println("Opción inválida");
            }
        }
    }
}
