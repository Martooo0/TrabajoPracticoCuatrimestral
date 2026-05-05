package view;

import controller.ControladorPaciente;
import modelo.Paciente;

import java.util.List;
import java.util.Scanner;

public class VistaPaciente {

    private final ControladorPaciente controlador;
    private final Scanner scanner;

    public VistaPaciente(ControladorPaciente controlador, Scanner scanner) {
        this.controlador = controlador;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- MENÚ PACIENTES ---");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Buscar paciente por id");
            System.out.println("3. Buscar paciente por DNI");
            System.out.println("4. Listar todos");
            System.out.println("5. Actualizar paciente");
            System.out.println("6. Eliminar paciente");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1": registrar(); break;
                case "2": buscarPorId(); break;
                case "3": buscarPorDni(); break;
                case "4": listar(); break;
                case "5": actualizar(); break;
                case "6": eliminar(); break;
                case "0": continuar = false; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private void registrar() {
        System.out.println("\n-- Registrar paciente --");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("DNI (7-8 dígitos): ");
        String dni = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("-- Domicilio --");
        System.out.print("Calle: ");
        String calle = scanner.nextLine();

        System.out.print("Número: ");
        String numero = scanner.nextLine();

        System.out.print("Localidad: ");
        String localidad = scanner.nextLine();

        System.out.print("Provincia: ");
        String provincia = scanner.nextLine();

        Paciente registrado = controlador.registrar(nombre, apellido, dni, email,
                calle, numero, localidad, provincia);
        if (registrado != null) {
            System.out.println("Paciente registrado con id " + registrado.getId());
        }
    }

    private void buscarPorId() {
        Long id = EntradaConsola.leerLong(scanner, "Id del paciente: ");
        Paciente p = controlador.buscarPorId(id);
        if (p == null) {
            System.out.println("No existe paciente con id " + id);
        } else {
            System.out.println(p);
        }
    }

    private void buscarPorDni() {
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        Paciente p = controlador.buscarPorDni(dni);
        if (p == null) {
            System.out.println("No existe paciente con DNI " + dni);
        } else {
            System.out.println(p);
        }
    }

    private void listar() {
        List<Paciente> pacientes = controlador.listarTodos();
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            for (Paciente p : pacientes) {
                System.out.println(p);
            }
        }
    }

    private void actualizar() {
        Long id = EntradaConsola.leerLong(scanner, "Id del paciente a actualizar: ");
        Paciente actual = controlador.buscarPorId(id);
        if (actual == null) {
            System.out.println("No existe paciente con id " + id);
            return;
        }
        System.out.println("Datos actuales: " + actual);
        System.out.println("(Deje vacío para mantener el valor actual)");

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Nuevo DNI: ");
        String dni = scanner.nextLine();

        System.out.print("Nuevo email: ");
        String email = scanner.nextLine();

        Paciente actualizado = controlador.actualizar(id, nombre, apellido, dni, email);
        if (actualizado != null) {
            System.out.println("Paciente actualizado: " + actualizado);
        }
    }

    private void eliminar() {
        Long id = EntradaConsola.leerLong(scanner, "Id del paciente a eliminar: ");
        if (controlador.eliminarPorId(id)) {
            System.out.println("Paciente eliminado.");
        } else {
            System.out.println("No existe paciente con id " + id);
        }
    }
}
