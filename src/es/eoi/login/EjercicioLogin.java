package es.eoi.login;

import java.util.Scanner;

public class EjercicioLogin {
    public static void main(String[] args) {
        //Definir variables válidas
        String usuario = "EstudianteEOI";
        String password = "ComoMolaJava";

        //Pedir que introduzca usuario
        Scanner entrada = new Scanner(System.in);
        System.out.print("Introduce tu usuario: ");
        String usuarioValidar = entrada.nextLine();

        //Comparar usuario
        boolean boolUsuario = usuarioValidar.equals(usuario);

        // Mensaje error si procede
        while (!boolUsuario) {
            System.out.println("Usuario incorrecto");
            System.out.print("Introduce tu usuario: ");
            usuarioValidar = entrada.nextLine();
            boolUsuario = usuarioValidar.equals(usuario);
        }

        // Introducir contraseña
        System.out.print("Introduce tu contraseña: ");
        String passwordValidar = entrada.nextLine();

        //Comparar contraseña
        boolean boolPassword = passwordValidar.equals(password);

        // Mensaje error si procede
        int counter = 2; //contador para limitar intentos contraseña (3). Ya llevas un intento, así que empieza por el segundo
        while (!boolPassword && counter <= 3) {
           System.out.println("Contraseña incorrecta");
           System.out.print("Introduce tu contraseña: ");
           passwordValidar = entrada.nextLine();
           boolPassword = passwordValidar.equals(password);
           counter++;
        }

       if (counter > 3) {
           System.out.println("Número de intentos excedido");
       }

        entrada.close(); //cerrar el scanner

        // Compara resultado final. Para que sea "true" los dos deben ser true
        boolean resultado = boolUsuario && boolPassword;

        //Imprimimos lo que sea

        if (resultado) {
            System.out.println("Resultado = " + resultado);
            System.out.println("Usuario y contraseña correctos");
        }else if (!resultado) {
            System.out.println("Resultado = " + resultado);
            System.out.println("Usuario y contraseña incorrectos");
        }
    }
}
