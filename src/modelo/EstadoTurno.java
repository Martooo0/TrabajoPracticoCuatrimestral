package modelo;

public enum EstadoTurno { // Hacemos un ENUM para ver el ciclo de vida que puede tener el turno. Por defecto se lo va a establecer en PENDIENTE. El ENUM es lo más seguro y legible/entendible como alternativa.
    PENDIENTE,
    CONFIRMADO,
    CANCELADO,
    COMPLETADO
}
