package service;

import modelo.Paciente;
import repository.RepositorioPaciente;

import java.util.List;

public class ServicioPaciente {

    private final RepositorioPaciente repositorio;

    public ServicioPaciente() {
        this.repositorio = new RepositorioPaciente();
    }

    public ServicioPaciente(RepositorioPaciente repositorio) {
        this.repositorio = repositorio;
    }

    public Paciente registrar(Paciente paciente) {
        if (paciente == null) {
            System.out.println("El paciente debe estar ingresado");
            return null;
        }
        if (esVacio(paciente.getNombre())) {
            System.out.println("El nombre es obligatorio");
            return null;
        }
        if (esVacio(paciente.getApellido())) {
            System.out.println("El apellido es obligatorio");
            return null;
        }
        if (!dniValido(paciente.getDni())) {
            System.out.println("El DNI tiene que tener entre 7 y 8 dígitos");
            return null;
        }
        if (!emailValido(paciente.getEmail())) {
            System.out.println("El email tiene un formato invalido");
            return null;
        }
        if (repositorio.buscarPorDNI(paciente.getDni()) != null) {
            System.out.println("Ya existe un paciente con el DNI " + paciente.getDni());
            return null;
        }
        return repositorio.guardar(paciente);
    }

    // Operación UPDATE en CRUD. Válido los parámetros y uso la forma de buscar por dni

    public Paciente actualizar(Paciente paciente) {
        if (paciente == null || paciente.getId() == null) {
            System.out.println("El paciente o id es inválido.");
            return null;
        }
        if (repositorio.buscarPorId(paciente.getId()) == null) {
            System.out.println("No existe un paciente con id " + paciente.getId());
            return null;
        }
        if (esVacio(paciente.getNombre())) {
            System.out.println("El nombre es obligatorio");
            return null;
        }
        if (esVacio(paciente.getApellido())) {
            System.out.println("El apellido es obligatorio");
            return null;
        }
        if (!dniValido(paciente.getDni())) {
            System.out.println("El DNI tiene que tener entre 7 y 8 dígitos");
            return null;
        }
        if (!emailValido(paciente.getEmail())) {
            System.out.println("El email tiene un formato invalido");
            return null;
        }
        Paciente conMismoDni = repositorio.buscarPorDNI(paciente.getDni());
        if (conMismoDni != null && !conMismoDni.getId().equals(paciente.getId())) {
            System.out.println("El DNI " + paciente.getDni() + " ya pertenece a otro paciente");
            return null;
        }
        return repositorio.actualizar(paciente);
    }

    // Metodos para buscar atributos del paciente

    public Paciente buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public Paciente buscarPorDni(String dni) {
        return repositorio.buscarPorDNI(dni);
    }

    public boolean eliminarPorId(Long id) {
        return repositorio.eliminarPorId(id);
    }

    // Metodos para ayudar a validar ciertos parámetros !!!!!

    private boolean esVacio(String s) {
        return s == null || s.trim().isEmpty();
    }

    private boolean dniValido(String dni) {
        return dni != null && dni.matches("\\d{7,8}");
    } // Una forma de medir la cantidad que se ingresó

    private boolean emailValido(String email) {
        return email != null && email.contains("@") && email.contains(".");
    } // No impuse un formato, solamente que tenga el '@' y un '.'

    public List<Paciente> listarTodos() {
        return repositorio.obtenerTodos();
    }

}
