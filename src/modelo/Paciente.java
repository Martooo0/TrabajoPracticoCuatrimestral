package modelo;

import java.time.LocalDate;

public class Paciente {

    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDate fechaIngreso;
    private Domicilio domicilio; // Acá se da una relación de composition con la clase Domicilio, ya que si Paciente deja de existir, Domicilio no va a tener más sentido

    public Paciente() {}

    public Paciente(Long id, String nombre, String apellido, String dni, String email, LocalDate fechaIngreso, Domicilio domicilio) { // Agregar Domicilio
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    // Métodos para modificación (set) de datos del paciente (incluido su domicilio).

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    // Métodos para obtención (get) de datos del paciente (incluido su domicilio).

    public String getNombreCompleto() { // Trae directamente el nombre y el apellido del paciente
        return nombre + " " + apellido;
    } // es mas comodo para imprimir...

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    // toString para imprimir en consola la información del paciente

    public String toString() {
        return "Paciente: " + id +
                ", nombre: " + getNombreCompleto() +
                ", dni: " + dni +
                ", email: " + email +
                ", fechaIngreso: " + fechaIngreso +
                ". Domicilio: " + domicilio;
    }
}
