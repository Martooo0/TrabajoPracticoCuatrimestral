package repository;

import modelo.Turno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepositorioTurno implements IRepositorio <Turno> {

    private final HashMap<Long, Turno> turnos;
    private Long siguienteId;

    public RepositorioTurno() {
        this.turnos = new HashMap<>();
        this.siguienteId = 1L;
    }

    @Override
    public Turno guardar(Turno turno) {
        turno.setId(siguienteId);
        turnos.put(siguienteId, turno);
        siguienteId++;
        return turno;
    }

    @Override
    public Turno buscarPorId(Long id) {
        return turnos.get(id);
    }

    @Override
    public boolean eliminarPorId(Long id) {
        return turnos.remove(id) != null;
    }

    @Override
    public Turno actualizar(Turno turno) {
        if (turno.getId() == null || !turnos.containsKey(turno.getId())) {
            return null;
        }
        turnos.put(turno.getId(), turno);
        return turno;
    }

    @Override
    public List<Turno> obtenerTodos() {
        return new ArrayList<>(turnos.values());
    }

    public List<Turno> listarPorPaciente(Long pacienteId) {
        List<Turno> listaResultantePacientes = new ArrayList<>();
        for (Turno t : turnos.values()) {
            if (t.getPaciente() != null && pacienteId.equals(t.getPaciente().getId())) {
                listaResultantePacientes.add(t);
            }
        }
        return listaResultantePacientes;
    }

    public List<Turno> listarPorOdontologo(Long odontologoId) {
        List<Turno> listaResultanteOdontologos = new ArrayList<>();
        for (Turno t : turnos.values()) {
            if (t.getOdontologo() != null && odontologoId.equals(t.getOdontologo().getId())) {
                listaResultanteOdontologos.add(t);
            }
        }
        return listaResultanteOdontologos;
    }
}
