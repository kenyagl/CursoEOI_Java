package es.eoi.boletinCalificaciones;

public class Alumno {
    private String nombre;
    private String apellidos;
    private String direccion;
    private String contacto;
    private String grupo;

    public Alumno(String nombre, String apellidos, String direccion, String contacto, String grupo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.contacto = contacto;
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "\n\nAlumno: " + nombre + " " + apellidos +
                "\n\t\t" + direccion +
                "\n\t\t" + contacto +
                "\n\t\t" + grupo;
    }
    //No hacen falta ni getters ni setters para este programa
}
