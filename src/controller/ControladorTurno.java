package controller;

import modelo.EstadoTurno;
import modelo.Turno;
import service.ServicioTurno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

// Capa intermedia entre la Vista y el Servicio. Para Turno alcanza con reenviar parámetros: el ServicioTurno ya recibe ids primitivos, fecha y hora, y se encarga de buscar paciente/odontólogo, validar y persistir.
public class ControladorTurno {

    private final ServicioTurno servicio;

    public ControladorTurno(ServicioTurno servicio) {
        this.servicio = servicio;
    }

    public Turno asignar(Long idPaciente, Long idOdontologo, LocalDate fecha, LocalTime hora) {
        return servicio.asignar(idPaciente, idOdontologo, fecha, hora);
    }

    public Turno buscarPorId(Long id) {
        return servicio.buscarPorId(id);
    }

    public List<Turno> listarTodos() {
        return servicio.obtenerTodos();
    }

    public List<Turno> listarPorPaciente(Long idPaciente) {
        return servicio.listarPorPaciente(idPaciente);
    }

    public List<Turno> listarPorOdontologo(Long idOdontologo) {
        return servicio.listarPorOdontologo(idOdontologo);
    }

    public Turno cambiarEstado(Long idTurno, EstadoTurno nuevoEstado) {
        return servicio.cambiarEstado(idTurno, nuevoEstado);
    }

    public Turno reprogramar(Long idTurno, LocalDate nuevaFecha, LocalTime nuevaHora) {
        return servicio.reprogramar(idTurno, nuevaFecha, nuevaHora);
    }

    public boolean eliminarPorId(Long id) {
        return servicio.eliminarPorId(id);
    }
}
