package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {

    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fecha;
    private LocalTime hora;
    private EstadoTurno estado;

    public Turno() {}

    public Turno(Long id, Paciente paciente, Odontologo odontologo,
                 LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = EstadoTurno.PENDIENTE; // Lo mejor es dejarlo en pendiente para luego darlo de alta y completarlo o cancelarlo
    }

    // Métodos para modificación (set) de datos del Turno.

    // ...

    // Métodos para obtención (get) de datos del Turno.

    // ...

    // Aca usamos los métodos para mostrar el flujo que va a ir teniendo el turno

    public void confirmar() {
        this.estado = EstadoTurno.CONFIRMADO;
    }

    public void completar() {
        this.estado = EstadoTurno.COMPLETADO;
    }

    public void cancelar() {
        this.estado = EstadoTurno.CANCELADO;
    }

    public String toString() {
        return "Turno: " + id +
                ", paciente: " + paciente.getNombreCompleto() + // Solo traemos el nombre completo del Paciente
                ", odontólogo: " + odontologo.getNombreCompleto() +
                ", fecha del turno: " + fecha + ", " + hora + " hs" +
                ". Estado: " + estado;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }
}
