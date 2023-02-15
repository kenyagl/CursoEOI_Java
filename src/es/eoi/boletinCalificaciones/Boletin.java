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
        String nomCentro = negrita("Centro de Enseñanzas Tecnológicas");
        String dirCentro = "Marqués de Cubas, 45 - 37001 Salamanca";
        String telfCentro = "923225566";
        String correoCentro = "contacto@cet.es";

        //Introducir datos alumno por consola
        Scanner entrada = new Scanner(System.in);

        //Nombre y apellidos
        System.out.println(negrita(cambiarColor("1) Datos del alumno", Colores.BLUE)));

        String patronNombre = "^[A-Za-zºª-ÁÉÍÓÚáéíóúü]{2,}+$"; //Solo incluye los caracteres que pueden estar en un nombre
        String nombreSinFormato = introduceDato(cambiarColor("\nIntroduzca el nombre del alumno", Colores.BLUE), entrada, patronNombre);
        String nombre = nombreSinFormato.substring(0, 1).toUpperCase() + nombreSinFormato.substring(1).toLowerCase();

        String apellido1SinFormato = introduceDato(cambiarColor("Introduzca el primer apellido", Colores.BLUE), entrada, patronNombre);
        String apellido1 = apellido1SinFormato.substring(0, 1).toUpperCase() + apellido1SinFormato.substring(1).toLowerCase();

        String apellido2SinFormato = introduceDato(cambiarColor("Introduzca el segundo apellido", Colores.BLUE), entrada, patronNombre);
        String apellido2 = apellido2SinFormato.substring(0, 1).toUpperCase() + apellido2SinFormato.substring(1).toLowerCase();

        String apellidos = apellido1 + " " + apellido2;

        //Dirección
        String direccion = introduceDato(cambiarColor("\nIntroduzca la dirección del alumno", Colores.BLUE), entrada);

        //email
        String patronContacto = "^[a-z0-9_]+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,}$";  //RegEx para validar email
        String contacto = introduceDato(cambiarColor("\nIntroduzca el email del alumno", Colores.BLUE), entrada, patronContacto);

        //Grupo
        String patronGrupo = "^[1-9]{1,1}[A-G]{1,1}$"; //Que empiece por un solo dígito y acabe por una sola letra mayúscula
        String grupo = introduceDato(cambiarColor("\nIntroduzca el grupo del alumno = curso + clase (Ej. 4B): ", Colores.BLUE), entrada, patronGrupo);

        //Crear objeto tipo Alumno con los datos
        Alumno miAlumno = new Alumno(nombre, apellidos, direccion, contacto, grupo);


        //Listado de notas por asignatura
        boolean seguir = true;
        Materias materia = null;
        double nota = 0;
        ArrayList<Calificaciones> listaCalificaciones = new ArrayList<>();

        System.out.println(negrita(cambiarColor("\n\n2) Datos de las asignaturas", Colores.PURPLE)));

        while (seguir) {
            String codigo = introduceDato(cambiarColor("\nIntroduzca el código de la asignatura (00 -> salir) " +
                    "\n25 -> Matemáticas" +
                    "\n43 -> Física" +
                    "\n44 -> Química" +
                    "\n67 -> Filosofía" +
                    "\n88 -> Historia", Colores.PURPLE), entrada);
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
                        System.out.println(cambiarColor("\nERROR - introduzca un código válido", Colores.RED));
                    }
                }
                if (!err) {
                    boolean notaValida = false;
                    while (!notaValida) {
                        try {
                            nota = Double.parseDouble(introduceDato(cambiarColor("\nIntroduzca la nota para la asignatura de " + materia.getNombreAsignatura(), Colores.PURPLE), entrada));
                            notaValida = true;
                        } catch (Exception e) {
                            System.out.println(cambiarColor("\nERROR en la nota - Introduzca un número entre 0 y 10", Colores.RED));
                        }
                        if (nota > 10 | nota < 0) {
                            notaValida = false;
                            System.out.println(cambiarColor("\nERROR en la nota - Introduzca un número entre 0 y 10", Colores.RED));
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

        System.out.println("------------------------------------------------------------------------------------");

        //Imprimir datos del centro
        System.out.println("\n" + nomCentro + "\n" + dirCentro + "\nTel. " + telfCentro + " - " + correoCentro);

        //Imprimir título y datos alumno
        System.out.println(negrita("\n\n\t\t\t\tBoletín de Calificaciones Trimestral"));

        System.out.println(miAlumno.toString());

        //Imprimir lugar y fecha
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\tSalamanca, a " + fechaHoraHoy());

        //Imprimir calificaciones individuales
        double sumaNotas = 0;

        System.out.println(negrita("\n\t\t\t\t\t\tCALIFICACIONES"));
        System.out.println("\n\t\tCod\t\tAsignatura\t\tNota\t\tCalificación\n");

        for (Calificaciones lineaCalificacion : listaCalificaciones) {
            System.out.println(lineaCalificacion.imprimeNotas());
            sumaNotas += lineaCalificacion.getNota();
        }

        //Calcular e imprimir media y calificación finales

        double notaMedia = calculaMedia(sumaNotas, listaCalificaciones);
        System.out.printf(negrita("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tNota media:   %.1f"), notaMedia);
        Calificaciones notaFinal = new Calificaciones(notaMedia); //Creo un objeto tipo calificaciones para usar su metodo
        String calificacionFinal = notaFinal.calculoCalificacion();
        System.out.println(negrita("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tCalificación: " + calificacionFinal));

        System.out.println("\n\n------------------------------------------------------------------------------------");
    }


    public static String introduceDato(String mensaje, Scanner entrada) {
        System.out.print(mensaje + ": ");
        return entrada.nextLine();
    }

    public static boolean busqueda(String patron, String texto) {
        Pattern miPatron = Pattern.compile(patron);
        Matcher miMatcher = miPatron.matcher(texto);
        return miMatcher.find();
    }

    public static String cambiarColor(String texto, Colores color) {
        return color.getCodColor() + texto + Colores.RESET.getCodColor();
    }

    public static String negrita(String texto) {
        return "\033[0;1m" + texto;
    }

    //Sobrecargo el método introduceDato para que si le metes un patrón, lo incluya
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
                System.out.println(cambiarColor("ERROR - se han detectado caracteres no permitidos", Colores.RED));
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
