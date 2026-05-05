package view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Scanner;

// Es la clase donde viene lo que ingresa el usuario que interactúe con el sistema
public final class EntradaConsola {

    private EntradaConsola() {}

    public static Long leerLong(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextLong()) {
            System.out.println("Valor inválido: ingresá un número entero.");
            scanner.nextLine(); // descartar la línea inválida
            System.out.print(prompt);
        }
        long valor = scanner.nextLong();
        scanner.nextLine(); // es para consumir el "\n" que deja nextLong
        return valor;
    }

    // Es como un verificador de que el dato que se ingrese sea válido según los parámetros que tiene el sistema
    public static int leerIntEnRango(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Valor inválido: ingresá un número entero");
                scanner.nextLine();
                System.out.print(prompt);
            }
            int valor = scanner.nextInt();
            scanner.nextLine();
            if (valor >= min && valor <= max) {
                return valor;
            }
            System.out.println("Fuera de rango: ingresá un número entre " + min + " y " + max);
        }
    }

    public static LocalDate leerFecha(Scanner scanner, String etiqueta) {
        System.out.println(etiqueta);
        int anio = leerIntEnRango(scanner, "  Año: ", 2000, 2100);
        int mes  = leerIntEnRango(scanner, "  Mes: ", 1, 12);
        int diasDelMes = YearMonth.of(anio, mes).lengthOfMonth();
        int dia  = leerIntEnRango(scanner, "  Día: " + diasDelMes, 1, diasDelMes);
        return LocalDate.of(anio, mes, dia);
    }

    public static LocalTime leerHora(Scanner scanner, String etiqueta) {
        System.out.println(etiqueta);
        int hora    = leerIntEnRango(scanner, "  Hora (0-23): ", 0, 23);
        int minutos = leerIntEnRango(scanner, "  Minutos: ", 0, 59);
        return LocalTime.of(hora, minutos);
    }
}
