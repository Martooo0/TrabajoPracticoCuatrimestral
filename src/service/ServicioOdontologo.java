package service;

import modelo.Odontologo;
import repository.RepositorioOdontologo;

import java.util.List;

public class ServicioOdontologo {

    private final RepositorioOdontologo repositorio;

    public ServicioOdontologo() {
        this.repositorio = new RepositorioOdontologo();
    }

    public ServicioOdontologo(RepositorioOdontologo repositorio) {
        this.repositorio = repositorio;
    }


    public Odontologo registrar(Odontologo odontologo) {
        // Odontólogo no puede ser nulo
        if (odontologo == null) {
            System.out.println("El odontólogo no esta en la base de datos");
            return null;
        }
        // Nombre no puede estar vacío
        if (esVacio(odontologo.getNombre())) {
            System.out.println("El nombre no puede estar vacío");
            return null;
        }
        // Apellido no puede estar vacío
        if (esVacio(odontologo.getApellido())) {
            System.out.println("El apellido no puede estar vacío");
            return null;
        }
        // Matrícula no puede estar vacía
        if (esVacio(odontologo.getMatricula())) {
            System.out.println("La matricula no puede estar vacía");
            return null;
        }
        // No puede haber dos matrículas iguales
        if (repositorio.buscarPorMatricula(odontologo.getMatricula()) != null) {
            System.out.println("La matricula " + odontologo.getMatricula() + " ya existe");
            return null;
        }
        return repositorio.guardar(odontologo);
    }

    // Buscar el id de un odontologo
    public Odontologo buscarporId(Long id) {
        return repositorio.buscarPorId(id);
    }

    // Buscar por la matrícula a un odontologo
    public Odontologo buscarPorMatricula(String matricula) {
        return repositorio.buscarPorMatricula(matricula);
    }

    // Metodo para update de odontologo (CRUD)
    public Odontologo actualizar(Odontologo odontologo) {
        if (odontologo == null || odontologo.getId() == null) {
            System.out.println("id invalido, ingrese nuevamente");
            return null;
        }
        if (repositorio.buscarPorId(odontologo.getId()) == null) {
            System.out.println("No existe un odontólogo con ese id");
            return null;
        }
        if (esVacio(odontologo.getNombre())) {
            System.out.println("El nombre no puede estar vacío");
            return null;
        }
        if (esVacio(odontologo.getApellido())) {
            System.out.println("El apellido no puede estar vacío");
            return null;
        }
        if (esVacio(odontologo.getMatricula())) {
            System.out.println("La matricula no puede estar vacía");
            return null;
        }
        Odontologo mismaMatricula = repositorio.buscarPorMatricula(odontologo.getMatricula());
        if (mismaMatricula != null && !mismaMatricula.getId().equals(odontologo.getId())) {
            System.out.println("La matricula " + odontologo.getMatricula() + " ya pertenece a otro odontólogo");
            return null;
        }
        return repositorio.actualizar(odontologo);
    }

    public boolean eliminarPorId(Long id) {
        return repositorio.eliminarPorId(id);
    }

    public List<Odontologo> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    private boolean esVacio(String s) {
        return s == null || s.trim().isEmpty();
    }
}
