import modelo.Odontologo;
import modelo.Paciente;
import modelo.Domicilio;
import modelo.Turno;

import java.time.LocalDate;
import java.time.LocalTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Instancias de Clases

        Domicilio domicilio1 = new Domicilio(1L, "Av. Corrientes", "1234", "San Nicolás", "Buenos Aires");
        Domicilio domicilio2 = new Domicilio(2L, "Av. Cordoba", "742", "San Nicolás", "Buenos Aires");

        Paciente paciente1 = new Paciente(1L, "Juan", "Pérez", "30123456", "juan@mail.com", LocalDate.now(), domicilio1);
        Paciente paciente2 = new Paciente(2L, "Laura", "Gómez", "27654321", "laura@mail.com", LocalDate.now(), domicilio2);

        Odontologo odontologo1 = new Odontologo(1L, "María", "González", "4521");
        Odontologo odontologo2 = new Odontologo(2L, "Carlos", "Rodríguez", "8834");

        Turno turno1 = new Turno(1L, paciente1, odontologo1, LocalDate.of(2026, 4, 20), LocalTime.of(10, 30));
        Turno turno2 = new Turno(2L, paciente2, odontologo2, LocalDate.of(2026, 4, 21), LocalTime.of(14, 0));

        paciente2.getDomicilio().setCalle("...");

        // Impresión de las Clases

        System.out.println("Información de los Domicilios:");
        System.out.println();

        System.out.println(domicilio1);
        System.out.println();
        System.out.println(domicilio2);

        System.out.println();

        System.out.println("Información de los Pacientes");
        System.out.println();

        System.out.println(paciente1);
        System.out.println();
        System.out.println(paciente2);

        System.out.println();

        System.out.println("Información de los Odontólogos");
        System.out.println();

        System.out.println(odontologo1);
        System.out.println();
        System.out.println(odontologo2);

        System.out.println();

        System.out.println("Información de los Turnos");
        System.out.println();

        // Demostración del ciclo de vida que pueden tener los Turnos

        System.out.println("Turno 1 creado ");
        System.out.println(turno1);

        System.out.println();

        turno1.confirmar();
        System.out.println("Turno 1 confirmado");
        System.out.println(turno1);

        System.out.println();

        turno1.completar();
        System.out.println("Turno 1 completado");
        System.out.println(turno1);

        System.out.println();

        System.out.println("Turno 2 recién creado");
        System.out.println(turno2);

        System.out.println();

        turno2.cancelar();
        System.out.println("Turno 2 cancelado");
        System.out.println(turno2);

    }
}