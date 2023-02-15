package es.eoi.boletinCalificaciones;

public class Calificaciones {
    private String nomAsignatura;
    private String codAsignatura;
    private double nota;
    private String calificacion;

    public Calificaciones(double nota, Materias materia) {
        this.nomAsignatura = materia.getNombreAsignatura();
        this.codAsignatura = materia.getCodigoAsignatura();
        this.nota = nota;
        this.calificacion = calculoCalificacion();
    }

    public Calificaciones(double nota) {
        this.nota = nota;
        this.calificacion = calculoCalificacion();
    }

    public String calculoCalificacion() {
        if (this.nota < 5.0){
            calificacion = "    Suspenso"; //los espacios raros son para que quede bonito al imprimir por consola
        }else if(this.nota < 7.0){
            calificacion = "    Aprobado";
        }else if(this.nota < 9.0){
            calificacion = "    Notable";
        }else if(this.nota < 10.0){
            calificacion = "    Sobresaliente";
        }else{
            calificacion = "Matrícula de honor";
        }

        return calificacion;
    }

    public String imprimeNotas() {
        return "\t\t" + codAsignatura +
                "\t\t" + nomAsignatura +
                "\t\t" + nota +
                "\t\t" + calificacion;
    }

    public double getNota() {
        return nota;
    }
}
