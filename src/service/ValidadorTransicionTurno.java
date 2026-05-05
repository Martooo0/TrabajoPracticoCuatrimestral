package service;

import modelo.EstadoTurno;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

// Responsabilidad única (SRP): saber qué transiciones de estado están permitidas
// para un Turno. Ni Turno (clase de datos) ni ServicioTurno (orquestación) tienen
// que conocer la máquina de estados; viven acá.
public class ValidadorTransicionTurno {

    private static final Map<EstadoTurno, Set<EstadoTurno>> TRANSICIONES_PERMITIDAS =
            new EnumMap<>(EstadoTurno.class);

    static {
        TRANSICIONES_PERMITIDAS.put(EstadoTurno.PENDIENTE,
                EnumSet.of(EstadoTurno.CONFIRMADO, EstadoTurno.CANCELADO));
        TRANSICIONES_PERMITIDAS.put(EstadoTurno.CONFIRMADO,
                EnumSet.of(EstadoTurno.COMPLETADO, EstadoTurno.CANCELADO));
        // CANCELADO y COMPLETADO son estados finales: no se sale de ellos.
        TRANSICIONES_PERMITIDAS.put(EstadoTurno.CANCELADO,
                EnumSet.noneOf(EstadoTurno.class));
        TRANSICIONES_PERMITIDAS.put(EstadoTurno.COMPLETADO,
                EnumSet.noneOf(EstadoTurno.class));
    }

    public boolean esTransicionValida(EstadoTurno actual, EstadoTurno nuevo) {
        if (actual == null || nuevo == null) {
            return false;
        }
        return TRANSICIONES_PERMITIDAS.get(actual).contains(nuevo);
    }
}
