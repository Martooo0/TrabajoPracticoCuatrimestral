import modelo.Paciente;
import modelo.Domicilio;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Paciente martin = new Paciente(1234L, "Martin", "Ferreira", "46625515", "martinferreira009@gmail.com", LocalDate.now(), null);

        System.out.println("Me llamo: " + martin.getNombre());
        martin.setNombre("Lucas");
        System.out.println("Me llamo: " + martin.getNombre());

    }
}