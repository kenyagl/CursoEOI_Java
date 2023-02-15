package es.eoi.boletinCalificaciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;

public class Boletin {
    public static void main(String[] args) {
        //Datos del centro
        String nomCentro = "Centro de Enseñanzas Tecnológicas";
        String dirCentro = "Marqués de Cubas, 45 - 37001 Salamanca";
        String telfCentro = "923225566";
        String correoCentro = "contacto@cet.es";

        //Introducir datos alumno por consola
        Scanner entrada = new Scanner(System.in);
        String nombre = introduceDato("Introduzca el nombre del alumno", entrada);
        String apellidos = introduceDato("Introduzca los apellidos del alumno", entrada);
        String direccion = introduceDato("Introduzca la dirección del alumno", entrada);
        String contacto = introduceDato("Introduzca el email del alumno", entrada);
        String grupo = introduceDato("Introduzca el grupo del alumno", entrada);

        Alumno miAlumno = new Alumno(nombre, apellidos, direccion, contacto, grupo);


        //Listado de notas por asignatura
        boolean seguir = true;
        Materias materia = null;
        double nota;
        ArrayList<Calificaciones> listaCalificaciones = new ArrayList<>();

        while (seguir) {
            String codigo = introduceDato("Introduzca el código de la asignatura (00 -> salir) " +
                    "\n25 -> Matemáticas" +
                    "\n43 -> Física" +
                    "\n44 -> Química" +
                    "\n67 -> Filosofía" +
                    "\n88 -> Historia", entrada);
            if (codigo.equals("00")) {
                seguir = false;
            }else{
                boolean err = false;
                switch (codigo) {
                    case "25" -> materia = Materias.MATEMATICAS;
                    case "43" -> materia = Materias.FISICA;
                    case "44" -> materia = Materias.QUIMICA;
                    case "67" -> materia = Materias.FILOSOFIA;
                    case "88" -> materia = Materias.HISTORIA;
                    default -> err = true;
                }
                if (!err){
                    boolean notaValida = true;
                    nota = Double.parseDouble(introduceDato("Introduzca la nota para la asignatura de " + materia.getNombreAsignatura(), entrada));
                    if(nota > 10 | nota < 0){
                        notaValida = false;
                        System.out.println("ERROR en la nota - Introduzca un número entre 0 y 10");
                    }
                    if(notaValida) {
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
        System.out.println("\n\n\t\t\t\tBoletín de Calificaciones Trimestral");

        System.out.println(miAlumno.toString());

        //Imprimir lugar y fecha
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\tSalamanca, a " + fechaHoraHoy());

        //Imprimir calificaciones individuales
        double sumaNotas = 0;

        System.out.println("\n\t\t\t\t\t\tCALIFICACIONES");
        System.out.println("\n\t\tCod\t\tAsignatura\t\tNota\t\tCalificación\n");

        for (Calificaciones lineaCalificacion:listaCalificaciones) {
            System.out.println(lineaCalificacion.imprimeNotas());
            sumaNotas += lineaCalificacion.getNota();
        }

        //Calcular e imprimir media y calificación finales

        double notaMedia = calculaMedia(sumaNotas, listaCalificaciones);
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tNota media:   %.1f", notaMedia);
        String calificacionFinal = calculoCalificacion(notaMedia);
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tCalificación: " + calificacionFinal);

        //AMPLIAR: Poner colores + ¿se pueden poner textos en negrita/más grandes por consola?

        System.out.println("\n\n------------------------------------------------------------------------------------");
    }

    public static String introduceDato(String mensaje, Scanner entrada) {
        System.out.print(mensaje + ": ");
        return entrada.nextLine();
    }

    public static double calculaMedia (double sumaNotas, ArrayList lista){
        return sumaNotas / lista.size();
    }

    public static String calculoCalificacion (double nota){
        //PREGUNTA: esto es un copia-pega de la clase "calificaciones". ¿Cómo hago para usarlo aquí?
        //He intentado importarlo pero seguía sin funcionar - investigaré
        String calificacion;

        if (nota < 5.0){
            calificacion = "Suspenso";
        }else if(nota < 7.0){
            calificacion = "Aprobado";
        }else if(nota < 9.0){
            calificacion = "Notable";
        }else if(nota < 10.0){
            calificacion = "Sobresaliente";
        }else{
            calificacion = "Matrícula de honor";
        }

        return calificacion;
    }

    public static String fechaHoraHoy(){
        LocalDateTime objetoFechaHora = LocalDateTime.now();
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MMM/yy, kk:mm:ss");
        return objetoFechaHora.format(formatoFechaHora);
    }
}
