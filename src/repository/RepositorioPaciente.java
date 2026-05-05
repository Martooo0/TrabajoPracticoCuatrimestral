package repository;

import modelo.Paciente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepositorioPaciente implements IRepositorio <Paciente> {

    private final HashMap<Long, Paciente> pacientes;
    private Long proximoId;

    public RepositorioPaciente() {
        this.pacientes = new HashMap<>();
        this.proximoId = 1L;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        paciente.setId(proximoId); // Establezco los ids de esta manera
        pacientes.put(proximoId, paciente);
        proximoId++;
        return paciente;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        return pacientes.get(id);
    }

    @Override
    public boolean eliminarPorId(Long id) {
        return pacientes.remove(id) != null;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        if (paciente.getId() == null || !pacientes.containsKey(paciente.getId())) {
            return null;
        }
        pacientes.put(paciente.getId(), paciente);
        return paciente;
    }

    @Override
    public List<Paciente> obtenerTodos() {
        return new ArrayList<>(pacientes.values());
    }

    public Paciente buscarPorDNI(String dni) {
        for (Paciente p : pacientes.values()) {
            if (p.getDni() != null && p.getDni().equals(dni)) {
                return p;
            }
        }
        return null;
    }
}
