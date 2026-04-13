package modelo;

public enum EstadoTurno { // Hacemos un ENUM para ver el ciclo de vida que puede tener el turno. Por defecto se lo va a establecer en PENDIENTE, ya que es lo más lógico en un sistema de asignación de turno
    PENDIENTE,
    CONFIRMADO,
    CANCELADO,
    COMPLETADO
}
