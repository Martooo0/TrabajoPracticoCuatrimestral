package controller;

import modelo.Especialidad;
import modelo.Odontologo;
import modelo.Ortodoncista;
import service.ServicioOdontologo;

import java.util.List;

// Capa intermedia entre la Vista y el Servicio. Recibe los datos crudos del usuario, instancia la subclase de Odontólogo correspondiente y delega la validación y persistencia al ServicioOdontologo.
public class ControladorOdontologo {

    private final ServicioOdontologo servicio;

    public ControladorOdontologo(ServicioOdontologo servicio) {
        this.servicio = servicio;
    }

    public Odontologo registrar(String nombre, String apellido, String matricula, Especialidad especialidad) {
        if (especialidad == null) {
            System.out.println("La especialidad es obligatoria.");
            return null;
        }
        Odontologo odontologo = especialidad.crear(nombre, apellido, matricula);
        return servicio.registrar(odontologo);
    }

    // Update parcial: valores nulos o vacíos no pisan el dato actual.
    public Odontologo actualizar(Long id, String nombre, String apellido, String matricula) {
        Odontologo actual = servicio.buscarporId(id);
        if (actual == null) {
            return null;
        }
        if (nombre != null && !nombre.isEmpty())actual.setNombre(nombre);
        if (apellido != null && !apellido.isEmpty())actual.setApellido(apellido);
        if (matricula != null && !matricula.isEmpty())actual.setMatricula(matricula);
        return servicio.actualizar(actual);
    }

    public Odontologo buscarPorId(Long id) {
        return servicio.buscarporId(id);
    }

    public Odontologo buscarPorMatricula(String matricula) {
        return servicio.buscarPorMatricula(matricula);
    }

    public List<Odontologo> listarTodos() {
        return servicio.obtenerTodos();
    }

    public boolean eliminarPorId(Long id) {
        return servicio.eliminarPorId(id);
    }
}