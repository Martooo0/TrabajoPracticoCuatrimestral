package controller;

import modelo.Domicilio;
import modelo.Paciente;
import service.ServicioPaciente;

import java.time.LocalDate;
import java.util.List;

// Capa intermedia entre la Vista y el Servicio. Recibe los datos crudos que el usuario ingresó, arma el objeto de dominio y delega la validación y persistencia al ServicioPaciente
public class ControladorPaciente {

    private final ServicioPaciente servicio;

    public ControladorPaciente(ServicioPaciente servicio) {
        this.servicio = servicio;
    }

    public Paciente registrar(String nombre, String apellido, String dni, String email,
                              String calle, String numero, String localidad, String provincia) {
        Domicilio domicilio = new Domicilio(calle, numero, localidad, provincia);
        Paciente paciente = new Paciente(nombre, apellido, dni, email, LocalDate.now(), domicilio);
        return servicio.registrar(paciente);
    }

    // Update parcial: cualquier valor nulo o vacío conserva el dato actual.
    public Paciente actualizar(Long id, String nombre, String apellido, String dni, String email) {
        Paciente actual = servicio.buscarPorId(id);
        if (actual == null) {
            return null;
        }
        if (nombre != null && !nombre.isEmpty())   actual.setNombre(nombre);
        if (apellido != null && !apellido.isEmpty()) actual.setApellido(apellido);
        if (dni != null && !dni.isEmpty())         actual.setDni(dni);
        if (email != null && !email.isEmpty())     actual.setEmail(email);
        return servicio.actualizar(actual);
    }

    public Paciente buscarPorId(Long id) {
        return servicio.buscarPorId(id);
    }

    public Paciente buscarPorDni(String dni) {
        return servicio.buscarPorDni(dni);
    }

    public List<Paciente> listarTodos() {
        return servicio.listarTodos();
    }

    public boolean eliminarPorId(Long id) {
        return servicio.eliminarPorId(id);
    }
}
