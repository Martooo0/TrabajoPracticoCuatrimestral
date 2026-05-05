import controller.ControladorOdontologo;
import controller.ControladorPaciente;
import controller.ControladorTurno;
import service.ServicioOdontologo;
import service.ServicioPaciente;
import service.ServicioTurno;
import view.VistaPrincipal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 1. Un único Scanner para toda la aplicación.
        //    Se crea acá y se pasa por constructor a las vistas.
        Scanner scanner = new Scanner(System.in);

        // 2. Servicios (cada uno arma su propio repositorio internamente).
        ServicioPaciente   servicioPaciente   = new ServicioPaciente();
        ServicioOdontologo servicioOdontologo = new ServicioOdontologo();
        ServicioTurno      servicioTurno      = new ServicioTurno(servicioPaciente, servicioOdontologo);

        // 3. Controllers: capa intermedia entre las Vistas y los Servicios.
        ControladorPaciente   controladorPaciente   = new ControladorPaciente(servicioPaciente);
        ControladorOdontologo controladorOdontologo = new ControladorOdontologo(servicioOdontologo);
        ControladorTurno      controladorTurno      = new ControladorTurno(servicioTurno);

        // 4. Vista principal: lanza el menú y solo conoce los Controllers.
        VistaPrincipal vista = new VistaPrincipal(
                controladorPaciente,
                controladorOdontologo,
                controladorTurno,
                scanner
        );
        vista.iniciar();

        // 5. Al salir, cerramos el Scanner.
        scanner.close();
    }
}