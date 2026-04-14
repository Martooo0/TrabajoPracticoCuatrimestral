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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    // Métodos para obtención de datos del Odontólogo.

        public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String toString() {
        return "Odontólogo: " + id +
                ", nombre: " + getNombreCompleto() +
                ", matricula: " + matricula;
    }
}
