package modelo;

public class Domicilio {

    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public Domicilio() {}

    public Domicilio(Long id, String calle, String numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    // Métodos para modificación (set) de datos del Domicilio.

    public void setCalle(String calle) {
        this.calle = calle;
    }


    // ...

    // Métodos para obtención (get) de datos del Domicilio.

    // ...


    public String toString(){
        return id + ", " + calle + ", altura: " + numero + ", en: " + localidad + ", " + provincia;
    }

}
