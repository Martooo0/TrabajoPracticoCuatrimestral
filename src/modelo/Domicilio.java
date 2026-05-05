package modelo;

// Domicilio se usa por composición desde Paciente: su ciclo de vida está atado
// al del paciente, así que no necesita un id propio (el del paciente alcanza
// para identificarlo).
public class Domicilio {

    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public Domicilio() {}

    public Domicilio(String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    // Métodos para modificación (set) de datos del Domicilio.

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    // Métodos para obtención (get) de datos del Domicilio.

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    // toString

    public String toString(){
        return calle + ", altura: " + numero + ", en: " + localidad + ", " + provincia;
    }
}
