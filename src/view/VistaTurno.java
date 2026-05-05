package view;

import controller.ControladorTurno;
import modelo.EstadoTurno;
import modelo.Turno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class VistaTurno {

    private final ControladorTurno controlador;
    private final Scanner scanner;

    public VistaTurno(ControladorTurno controlador, Scanner scanner) {
        this.controlador = controlador;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- MENÚ TURNOS ---");
            System.out.println("1. Asignar turno");
            System.out.println("2. Buscar turno por id");
            System.out.println("3. Listar todos");
            System.out.println("4. Listar turnos por paciente");
            System.out.println("5. Listar turnos por odontólogo");
            System.out.println("6. Cambiar estado de turno");
            System.out.println("7. Reprogramar turno");
            System.out.println("8. Eliminar turno");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1": asignar(); break;
                case "2": buscarPorId(); break;
                case "3": listar(); break;
                case "4": listarPorPaciente(); break;
                case "5": listarPorOdontologo(); break;
                case "6": cambiarEstado(); break;
                case "7": reprogramar(); break;
                case "8": eliminar(); break;
                case "0": continuar = false; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private void asignar() {
        System.out.println("\n-- Asignar turno --");

        Long idPaciente = EntradaConsola.leerLong(scanner, "Id paciente: ");
        Long idOdontologo = EntradaConsola.leerLong(scanner, "Id odontólogo: ");
        LocalDate fecha = EntradaConsola.leerFecha(scanner, "Fecha del turno:");
        LocalTime hora  = EntradaConsola.leerHora(scanner, "Hora del turno:");

        Turno turno = controlador.asignar(idPaciente, idOdontologo, fecha, hora);
        if (turno != null) {
            System.out.println("Turno asignado: " + turno);
        }
    }

    private void buscarPorId() {
        Long id = EntradaConsola.leerLong(scanner, "Id del turno: ");
        Turno t = controlador.buscarPorId(id);
        if (t == null) {
            System.out.println("No existe turno con id " + id);
        } else {
            System.out.println(t);
        }
    }

    private void listar() {
        mostrarLista(controlador.listarTodos());
    }

    private void listarPorPaciente() {
        Long id = EntradaConsola.leerLong(scanner, "Id del paciente: ");
        mostrarLista(controlador.listarPorPaciente(id));
    }

    private void listarPorOdontologo() {
        Long id = EntradaConsola.leerLong(scanner, "Id del odontólogo: ");
        mostrarLista(controlador.listarPorOdontologo(id));
    }

    private void cambiarEstado() {
        Long id = EntradaConsola.leerLong(scanner, "Id del turno: ");

        System.out.println("Nuevo estado:");
        System.out.println("1. PENDIENTE");
        System.out.println("2. CONFIRMADO");
        System.out.println("3. COMPLETADO");
        System.out.println("4. CANCELADO");
        System.out.print("Opción: ");
        String op = scanner.nextLine();

        EstadoTurno nuevo;
        switch (op) {
            case "1": nuevo = EstadoTurno.PENDIENTE; break;
            case "2": nuevo = EstadoTurno.CONFIRMADO; break;
            case "3": nuevo = EstadoTurno.COMPLETADO; break;
            case "4": nuevo = EstadoTurno.CANCELADO; break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        Turno t = controlador.cambiarEstado(id, nuevo);
        if (t != null) {
            System.out.println("Estado actualizado: " + t);
        }
    }

    private void reprogramar() {
        Long id = EntradaConsola.leerLong(scanner, "Id del turno: ");
        LocalDate fecha = EntradaConsola.leerFecha(scanner, "Nueva fecha:");
        LocalTime hora  = EntradaConsola.leerHora(scanner, "Nueva hora:");

        Turno t = controlador.reprogramar(id, fecha, hora);
        if (t != null) {
            System.out.println("Turno reprogramado: " + t);
        }
    }

    private void eliminar() {
        Long id = EntradaConsola.leerLong(scanner, "Id del turno: ");
        if (controlador.eliminarPorId(id)) {
            System.out.println("Turno eliminado.");
        } else {
            System.out.println("No existe turno con id " + id);
        }
    }

    private void mostrarLista(List<Turno> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay turnos para mostrar.");
        } else {
            for (Turno t : lista) {
                System.out.println(t);
            }
        }
    }
}
