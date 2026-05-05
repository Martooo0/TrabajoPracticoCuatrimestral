package modelo;

// Herencia (hay que corregir y encontrar la manera de poder asignarle la especialidad al dentista, básicamente siempre que se instance un odontólogo se debería pedir la especialidad que va a tener este mismo)
public class Ortodoncista extends Odontologo{

    public Ortodoncista() {}

    public Ortodoncista(String nombre, String apellido, String matricula) {
        super(nombre, apellido, matricula);
    }

    // Con el @Override implementamos los metodos de la clase padre

    @Override
    public Especialidad getEspecialidad() {
        return Especialidad.ORTODONCIA;
    }

    @Override
    public int calcularDuracionTurno() {
        return 50; // Por default los turnos del ortodoncista durarían 50 minutos en la agenda. Es algo común que se asignen turnos cada cierto tiempo dejando un margen mayor por si a caso
    }
}
