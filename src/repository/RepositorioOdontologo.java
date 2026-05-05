package repository;

import modelo.Odontologo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepositorioOdontologo implements IRepositorio <Odontologo> {

    private final HashMap<Long, Odontologo> odontologos;
    private Long siguienteId = 1L;

    public RepositorioOdontologo() {
        this.odontologos = new HashMap<>();
        this.siguienteId = 1L;
    }


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologo.setId(siguienteId);
        odontologos.put(siguienteId, odontologo);
        siguienteId++;
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        return odontologos.get(id);
    }

    @Override
    public boolean eliminarPorId(Long id) {
        return odontologos.remove(id) != null;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        if (odontologo.getId() == null || !odontologos.containsKey(odontologo.getId())) {
            return null;
        }
        odontologos.put(odontologo.getId(), odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> obtenerTodos() {
        return new ArrayList<>(odontologos.values());
    }

    public Odontologo buscarPorMatricula(String matricula){
        for (Odontologo o : odontologos.values()) {
            if (o.getMatricula() != null && o.getMatricula().equals(matricula))  {
                return o;
            }
        }
        return null;
    }
}
