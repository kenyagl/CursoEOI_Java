package es.eoi.boletinCalificaciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Boletin {
    public static void main(String[] args) {
        //Datos del centro
        String nomCentro = "Centro de Enseñanzas Tecnológicas";
        String dirCentro = "Marqués de Cubas, 45 - 37001 Salamanca";
        String telfCentro = "923225566";
        String correoCentro = "contacto@cet.es";

        //Introducir datos alumno por consola
        Scanner entrada = new Scanner(System.in);

        //Nombre y apellidos
        System.out.println(Colores.negrita(Colores.cambiarColor("1) Datos del alumno", Colores.BLUE)));

        String patronNombre = "^[A-Za-zºª-ÁÉÍÓÚáéíóúü]{2,}+$"; //Solo incluye los caracteres que pueden estar en un nombre
        String nombreSinFormato = introduceDato("\nIntroduzca el nombre del alumno", entrada, patronNombre, Colores.BLUE);
        String nombre = nombreSinFormato.substring(0, 1).toUpperCase() + nombreSinFormato.substring(1).toLowerCase();

        String apellido1SinFormato = introduceDato("Introduzca el primer apellido", entrada, patronNombre, Colores.BLUE);
        String apellido1 = apellido1SinFormato.substring(0, 1).toUpperCase() + apellido1SinFormato.substring(1).toLowerCase();

        String apellido2SinFormato = introduceDato("Introduzca el segundo apellido", entrada, patronNombre, Colores.BLUE);
        String apellido2 = apellido2SinFormato.substring(0, 1).toUpperCase() + apellido2SinFormato.substring(1).toLowerCase();

        String apellidos = apellido1 + " " + apellido2;

        //Dirección
        String direccion = introduceDato("\nIntroduzca la dirección del alumno", entrada, Colores.BLUE);
        //Hay demasiadas variaciones de direcciones para definir un patrón - o por lo menos a mí no se me ocurren
        //TODO investigar esto

        //email
        String patronContacto = "^[a-z0-9_]+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,}$";  //RegEx para validar email
        String contacto = introduceDato("\nIntroduzca el email del alumno", entrada, patronContacto, Colores.BLUE);

        //Grupo
        String patronGrupo = "^[1-9]{1,1}[A-G]{1,1}$"; //Que empiece por un solo dígito y acabe por una sola letra mayúscula
        String grupo = introduceDato("\nIntroduzca el grupo del alumno: curso(1-9) y clase(A-G) (Ej. 4B)", entrada, patronGrupo, Colores.BLUE);

        //Crear objeto tipo Alumno con los datos
        Alumno miAlumno = new Alumno(nombre, apellidos, direccion, contacto, grupo);


        //Listado de notas por asignatura
        boolean seguir = true;
        Materias materia = null;
        double nota = 0;
        ArrayList<Calificaciones> listaCalificaciones = new ArrayList<>();

        System.out.println(Colores.negrita(Colores.cambiarColor("\n\n2) Datos de las asignaturas", Colores.PURPLE)));

        while (seguir) {
            String codigo = introduceDato("\nIntroduzca el código de la asignatura (00 -> salir) " +
                    "\n25 -> Matemáticas" +
                    "\n43 -> Física" +
                    "\n44 -> Química" +
                    "\n67 -> Filosofía" +
                    "\n88 -> Historia", entrada, Colores.PURPLE);
            if (codigo.equals("00")) {
                seguir = false;
            } else {
                boolean err = false;
                switch (codigo) {
                    case "25" -> materia = Materias.MATEMATICAS;
                    case "43" -> materia = Materias.FISICA;
                    case "44" -> materia = Materias.QUIMICA;
                    case "67" -> materia = Materias.FILOSOFIA;
                    case "88" -> materia = Materias.HISTORIA;
                    default -> {
                        err = true;
                        System.out.println(Colores.cambiarColor("\nERROR - introduzca un código válido", Colores.RED));
                    }
                }
                if (!err) {
                    boolean notaValida = false;
                    while (!notaValida) {
                        try {
                            nota = Double.parseDouble(introduceDato(Colores.cambiarColor("\nIntroduzca la nota para la asignatura de " + materia.getNombreAsignatura(), Colores.PURPLE), entrada));
                            notaValida = true;
                        } catch (Exception e) {
                            System.out.println(Colores.cambiarColor("\nERROR en la nota - Introduzca un número entre 0 y 10", Colores.RED));
                        }
                        if (nota > 10 | nota < 0) {
                            notaValida = false;
                            System.out.println(Colores.cambiarColor("\nERROR en la nota - Introduzca un número entre 0 y 10", Colores.RED));
                        }
                    }
                    if (notaValida) {
                        Calificaciones lineaCalificacion = new Calificaciones(nota, materia);
                        listaCalificaciones.add(lineaCalificacion);
                    }
                }
            }
        }
        entrada.close();
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("------------------------------------------------------------------------------------");

        //Imprimir datos del centro
        System.out.println(Colores.negrita("\n" + nomCentro));
        System.out.println(dirCentro + "\nTel. " + telfCentro + " - " + correoCentro);

        //Imprimir título y datos alumno
        System.out.println(Colores.negrita("\n\n\t\t\t\tBoletín de Calificaciones Trimestral"));

        System.out.println(miAlumno.toString());

        //Imprimir lugar y fecha
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\tSalamanca, a " + fechaHoraHoy());

        //Imprimir calificaciones individuales
        double sumaNotas = 0;

        System.out.println(Colores.negrita("\n\t\t\t\t\t\tCALIFICACIONES"));
        System.out.println("\n\t\tCod\t\tAsignatura\t\tNota\t\tCalificación\n");

        for (Calificaciones lineaCalificacion : listaCalificaciones) {
            System.out.println(lineaCalificacion.imprimeNotas());
            sumaNotas += lineaCalificacion.getNota();
        }

        //Calcular e imprimir media y calificación finales

        double notaMedia = calculaMedia(sumaNotas, listaCalificaciones);
        System.out.printf(Colores.negrita("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tNota media:\t\t\t%.1f"), notaMedia);
        Calificaciones notaFinal = new Calificaciones(notaMedia); //Creo un objeto tipo calificaciones para usar su metodo
        String calificacionFinal = notaFinal.calculoCalificacion();
        System.out.println(Colores.negrita("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tCalificación: " + calificacionFinal));

        System.out.println("\n\n------------------------------------------------------------------------------------");
        System.out.println("\n\n\n\n\n\n\n\n");
    }


    //MÉTODOS:
    public static String introduceDato(String mensaje, Scanner entrada) {
        System.out.print(mensaje + ": ");
        return entrada.nextLine();
    }

    public static boolean busqueda(String patron, String texto) {
        Pattern miPatron = Pattern.compile(patron);
        Matcher miMatcher = miPatron.matcher(texto);
        return miMatcher.find();
    }

    //Sobrecargo introduceDato con nuevo parámetro: color
    public static String introduceDato(String mensaje, Scanner entrada, Colores colorMsj) {
        System.out.print(Colores.cambiarColor(mensaje + ": ", colorMsj));
        return entrada.nextLine();
    }

    //Sobrecargo el método introduceDato con un nuevo parámetro: el patrón
    public static String introduceDato(String mensaje, Scanner entrada, String patron) {
        String textoFinal = null;
        boolean salir = false;

        while (!salir) {
            System.out.print(mensaje + ": ");
            String textoPrueba = entrada.nextLine();
            if (busqueda(patron, textoPrueba)) {
                textoFinal = textoPrueba;
                salir = true;
            } else {
                System.out.println(Colores.cambiarColor("ERROR - se han detectado caracteres no permitidos", Colores.RED));
            }
        }
        return textoFinal;
    }

    //Otra sobrecarga pero incluyendo poner el mensaje de un color Y el patrón
    public static String introduceDato(String mensaje, Scanner entrada, String patron, Colores colorMsj) {
        String textoFinal = null;
        boolean salir = false;

        while (!salir) {
            System.out.print(Colores.cambiarColor(mensaje + ": ", colorMsj));
            String textoPrueba = entrada.nextLine();
            if (busqueda(patron, textoPrueba)) {
                textoFinal = textoPrueba;
                salir = true;
            } else {
                System.out.println(Colores.cambiarColor("ERROR - se han detectado caracteres no permitidos", Colores.RED));
            }
        }
        return textoFinal;
    }

    public static double calculaMedia(double sumaNotas, ArrayList lista) {
        return sumaNotas / lista.size();
    }

    public static String fechaHoraHoy() {
        LocalDateTime objetoFechaHora = LocalDateTime.now();
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MMM/yy, kk:mm:ss");
        return objetoFechaHora.format(formatoFechaHora);
    }

}
