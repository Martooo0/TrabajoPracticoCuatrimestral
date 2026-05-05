package view;

import controller.ControladorOdontologo;
import modelo.Especialidad;
import modelo.Odontologo;

import java.util.List;
import java.util.Scanner;

public class VistaOdontologo {

    private final ControladorOdontologo controlador;
    private final Scanner scanner;

    public VistaOdontologo(ControladorOdontologo controlador, Scanner scanner) {
        this.controlador = controlador;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n--- MENÚ ODONTÓLOGOS ---");
            System.out.println("1. Registrar odontólogo");
            System.out.println("2. Buscar por id");
            System.out.println("3. Buscar por matrícula");
            System.out.println("4. Listar todos");
            System.out.println("5. Actualizar odontólogo");
            System.out.println("6. Eliminar odontólogo");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1": registrar(); break;
                case "2": buscarPorId(); break;
                case "3": buscarPorMatricula(); break;
                case "4": listar(); break;
                case "5": actualizar(); break;
                case "6": eliminar(); break;
                case "0": continuar = false; break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private void registrar() {
        System.out.println("\n-- Registrar odontólogo --");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        Especialidad especialidad = elegirEspecialidad();

        Odontologo registrado = controlador.registrar(nombre, apellido, matricula, especialidad);
        if (registrado != null) {
            System.out.println("Odontólogo registrado con id " + registrado.getId());
        }
    }

    // Construye el menú a partir de Especialidad.values(). Al sumar una nueva
    // especialidad al enum, aparece automáticamente en este menú: la Vista no
    // hay que tocarla.
    private Especialidad elegirEspecialidad() {
        Especialidad[] especialidades = Especialidad.values();
        System.out.println("Especialidad:");
        for (int i = 0; i < especialidades.length; i++) {
            System.out.println("  " + (i + 1) + ". " + especialidades[i]);
        }
        int opcion = EntradaConsola.leerIntEnRango(scanner, "Opción: ", 1, especialidades.length);
        return especialidades[opcion - 1];
    }

    private void buscarPorId() {
        Long id = EntradaConsola.leerLong(scanner, "Id del odontólogo: ");
        Odontologo o = controlador.buscarPorId(id);
        if (o == null) {
            System.out.println("No existe odontólogo con id " + id);
        } else {
            System.out.println(o);
        }
    }

    private void buscarPorMatricula() {
        System.out.print("Matrícula: ");
        String mat = scanner.nextLine();
        Odontologo o = controlador.buscarPorMatricula(mat);
        if (o == null) {
            System.out.println("No existe odontólogo con matrícula " + mat);
        } else {
            System.out.println(o);
        }
    }

    private void listar() {
        List<Odontologo> odontologos = controlador.listarTodos();
        if (odontologos.isEmpty()) {
            System.out.println("No hay odontólogos registrados.");
        } else {
            for (Odontologo o : odontologos) {
                System.out.println(o);
            }
        }
    }

    private void actualizar() {
        Long id = EntradaConsola.leerLong(scanner, "Id del odontólogo a actualizar: ");
        Odontologo actual = controlador.buscarPorId(id);
        if (actual == null) {
            System.out.println("No existe odontólogo con id " + id);
            return;
        }
        System.out.println("Datos actuales: " + actual);
        System.out.println("(Deje vacío para mantener el valor actual)");

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Nuevo apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Nueva matrícula: ");
        String mat = scanner.nextLine();

        Odontologo actualizado = controlador.actualizar(id, nombre, apellido, mat);
        if (actualizado != null) {
            System.out.println("Odontólogo actualizado: " + actualizado);
        }
    }

    private void eliminar() {
        Long id = EntradaConsola.leerLong(scanner, "Id del odontólogo a eliminar: ");
        if (controlador.eliminarPorId(id)) {
            System.out.println("Odontólogo eliminado.");
        } else {
            System.out.println("No existe odontólogo con id " + id);
        }
    }
}
