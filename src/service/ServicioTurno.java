package service;

import modelo.EstadoTurno;
import modelo.Odontologo;
import modelo.Paciente;
import modelo.Turno;
import repository.RepositorioTurno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ServicioTurno {

    private final RepositorioTurno repositorio;
    private final ServicioPaciente servicioPaciente;
    private final ServicioOdontologo servicioOdontologo;
    private final ValidadorTransicionTurno validadorTransicion;

    public ServicioTurno(ServicioPaciente servicioPaciente, ServicioOdontologo servicioOdontologo) {
        this(servicioPaciente, servicioOdontologo, new ValidadorTransicionTurno());
    }

    public ServicioTurno(ServicioPaciente servicioPaciente,
                         ServicioOdontologo servicioOdontologo,
                         ValidadorTransicionTurno validadorTransicion) {
        this.repositorio = new RepositorioTurno();
        this.servicioPaciente = servicioPaciente;
        this.servicioOdontologo = servicioOdontologo;
        this.validadorTransicion = validadorTransicion;
    }

    public Turno asignar(Long idPaciente, Long idOdontologo, LocalDate fecha, LocalTime hora) {

        // Válida que el paciente exista
        Paciente paciente = servicioPaciente.buscarPorId(idPaciente);
        if (paciente == null) {
            System.out.println("No existe paciente con id " + idPaciente);
            return null;
        }

        // Válida que el odontólogo exista
        Odontologo odontologo = servicioOdontologo.buscarporId(idOdontologo);
        if (odontologo == null) {
            System.out.println("No existe el odontólogo con id " + idOdontologo);
            return null;
        }

        // Válida que fecha y hora no sean nulas
        if (fecha == null || hora == null) {
            System.out.println("La fecha y hora son obligatorias.");
            return null;
        }

        // Válida que la combinación fecha+hora no esté en el pasado
        if (LocalDateTime.of(fecha, hora).isBefore(LocalDateTime.now())) {
            System.out.println("No se pueden asignar turnos en fechas u horas pasadas.");
            return null;
        }

        // Válida que el odontólogo no tenga otro turno que se solape
        if (haySolapamientoOdontologo(idOdontologo, fecha, hora, odontologo.calcularDuracionTurno(), null)) {
            System.out.println("El odontólogo ya tiene un turno que se solapa con ese horario.");
            return null;
        }

        // Válida que el paciente no tenga otro turno a esa misma fecha y hora
        if (hayTurnoPaciente(idPaciente, fecha, hora, null)) {
            System.out.println("El paciente ya tiene un turno asignado para esa fecha y hora.");
            return null;
        }

        // Pasó todas las validaciones: crea el turno y lo guarda
        Turno turno = new Turno(paciente, odontologo, fecha, hora);
        return repositorio.guardar(turno);
    }

    public Turno buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public List<Turno> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    public List<Turno> listarPorPaciente(Long idPaciente) {
        return repositorio.listarPorPaciente(idPaciente);
    }

    public List<Turno> listarPorOdontologo(Long idOdontologo) {
        return repositorio.listarPorOdontologo(idOdontologo);
    }

    // La entidad Turno solo almacena el dato con setEstado(); la decisión de qué transiciones son válidas vive en ValidadorTransicionTurno (SRP).
    public Turno cambiarEstado(Long idTurno, EstadoTurno nuevoEstado) {
        Turno turno = repositorio.buscarPorId(idTurno);
        if (turno == null) {
            System.out.println("No existe el turno " + idTurno);
            return null;
        }
        if (!validadorTransicion.esTransicionValida(turno.getEstado(), nuevoEstado)) {
            System.out.println("Transición inválida: no se puede pasar de "
                    + turno.getEstado() + " a " + nuevoEstado + ".");
            return null;
        }
        turno.setEstado(nuevoEstado);
        return repositorio.actualizar(turno);
    }

    // Reprogramar fecha y hora
    public Turno reprogramar(Long idTurno, LocalDate nuevaFecha, LocalTime nuevaHora) {
        Turno turno = repositorio.buscarPorId(idTurno);
        if (turno == null) {
            System.out.println("No existe turno con id " + idTurno);
            return null;
        }
        if (nuevaFecha == null || nuevaHora == null) {
            System.out.println("La fecha y hora son obligatorias");
            return null;
        }
        if (LocalDateTime.of(nuevaFecha, nuevaHora).isBefore(LocalDateTime.now())) {
            System.out.println("No se puede reprogramar a una fecha u hora pasada.");
            return null;
        }
        if (turno.getEstado() == EstadoTurno.CANCELADO || turno.getEstado() == EstadoTurno.COMPLETADO) {
            System.out.println("No se puede reprogramar un turno " + turno.getEstado() + ".");
            return null;
        }
        // Al reprogramar, el propio turno no debe contarse como conflicto consigo mismo.
        if (haySolapamientoOdontologo(turno.getOdontologo().getId(), nuevaFecha, nuevaHora,
                turno.getOdontologo().calcularDuracionTurno(), turno.getId())) {
            System.out.println("El odontólogo ya tiene un turno que se solapa con ese horario.");
            return null;
        }
        if (hayTurnoPaciente(turno.getPaciente().getId(), nuevaFecha, nuevaHora, turno.getId())) {
            System.out.println("El paciente ya tiene un turno asignado para esa fecha y hora.");
            return null;
        }
        turno.setFecha(nuevaFecha);
        turno.setHora(nuevaHora);
        return repositorio.actualizar(turno);
    }

    public boolean eliminarPorId(Long id) {
        return repositorio.eliminarPorId(id);
    }

    // Devuelve true si existe un turno NO cancelado del mismo odontólogo cuyo intervalo [hora, hora+duración) se cruza con el intervalo propuesto.
    private boolean haySolapamientoOdontologo(Long idOdontologo, LocalDate fecha, LocalTime hora, int duracionMinutos, Long idTurnoExcluir) {
        LocalTime nuevoInicio = hora;
        LocalTime nuevoFin = hora.plusMinutes(duracionMinutos);
        for (Turno t : repositorio.listarPorOdontologo(idOdontologo)) {
            if (idTurnoExcluir != null && idTurnoExcluir.equals(t.getId())) continue;
            if (t.getEstado() == EstadoTurno.CANCELADO) continue;
            if (!t.getFecha().equals(fecha)) continue;
            LocalTime existenteInicio = t.getHora();
            LocalTime existenteFin = existenteInicio.plusMinutes(t.getOdontologo().calcularDuracionTurno());
            if (nuevoInicio.isBefore(existenteFin) && existenteInicio.isBefore(nuevoFin)) {
                return true;
            }
        }
        return false;
    }

    // El paciente no puede tener dos turnos no cancelados a la misma fecha y hora.
    private boolean hayTurnoPaciente(Long idPaciente, LocalDate fecha, LocalTime hora, Long idTurnoExcluir) {
        for (Turno t : repositorio.listarPorPaciente(idPaciente)) {
            if (idTurnoExcluir != null && idTurnoExcluir.equals(t.getId())) continue;
            if (t.getEstado() == EstadoTurno.CANCELADO) continue;
            if (t.getFecha().equals(fecha) && t.getHora().equals(hora)) {
                return true;
            }
        }
        return false;
    }
}
