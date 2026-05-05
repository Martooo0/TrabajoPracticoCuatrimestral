package modelo;

// Cada Especialidad sabe construir la subclase concreta de Odontólogo que le
// corresponde. Al ser un metodo abstracto, agregar una nueva especialidad
// obliga (en tiempo de compilación) a proveer su factory: es imposible
// olvidarse y la lógica de "qué clase instanciar" queda en un único lugar.
public enum Especialidad {

    ORTODONCIA {
        @Override
        public Odontologo crear(String nombre, String apellido, String matricula) {
            return new Ortodoncista(nombre, apellido, matricula);
        }
    };

    public abstract Odontologo crear(String nombre, String apellido, String matricula);
}
