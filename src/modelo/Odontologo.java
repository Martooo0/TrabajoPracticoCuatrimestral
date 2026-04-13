package modelo;

public class Odontologo {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    public Odontologo() {}

    public Odontologo(Long id, String nombre, String apellido, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    // Métodos para modificación de datos del Odontólogo.

    // ...

    // Métodos para obtención de datos del Odontólogo.

    // ...

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String toString() {
        return "Odontólogo: " + id +
                ", nombre: " + getNombreCompleto() +
                ", matricula: " + matricula;
    }
}
