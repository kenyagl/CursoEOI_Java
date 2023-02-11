package es.eoi.boletinCalificaciones;

public enum Materias {
    MATEMATICAS ("Matemáticas", "025"),
    FISICA ("Física   ", "043"), //Los espacios raros es para que se imprima bonito
    QUIMICA ("Química   ", "044"),
    FILOSOFIA ("Filosofía", "067"),
    HISTORIA ("Historia", "088");


    private String nombreAsignatura;
    private String codigoAsignatura;

    Materias(String nombreAsignatura, String codigoAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
        this.codigoAsignatura = codigoAsignatura;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }
}
