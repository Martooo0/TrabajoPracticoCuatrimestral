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

    public Turno(Paciente paciente, Odontologo odontologo, LocalDate fecha, LocalTime hora) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = EstadoTurno.PENDIENTE; // Lo mejor es dejarlo en pendiente para luego darlo de alta y completarlo o cancelarlo
    }

    // Métodos para modificación (set) de datos del Turno.

    public void setId(Long id) {this.id = id;}

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

    // Métodos para obtención (get) de datos del Turno.

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

    public String toString() {
        return "Turno: " + id +
                ", paciente: " + paciente.getNombreCompleto() + // Solo traemos el nombre completo del Paciente
                ", odontólogo: " + odontologo.getNombreCompleto()  + // Lo mismo aca con odontólogo
                ", fecha del turno: " + fecha + ", " + hora + " hs" +
                ". Estado: " + estado;
    }
}
