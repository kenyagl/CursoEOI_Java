package es.eoi.facturacion;

public class Cliente {
    //Atributos: nombre, apellidos, direccion, contacto, NIF

    private String nombre;
    private String apellidos;
    private String direccion;
    private String contacto;
    private String NIF;

    //Constructor

    public Cliente(String nombre, String apellidos, String direccion, String contacto, String NIF) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.contacto = contacto;
        this.NIF = NIF;
    }

    //toString mejorado para sacar nombre del cliente

    public void datosClientes() {
        System.out.println("Cliente: \t" +
                "Nombre: \t" + nombre + " " + apellidos + '\n' +
                "\t\t\t Dirección: \t" + direccion + '\n' +
                "\t\t\t Contacto: \t" + contacto + '\n' +
                "\t\t\t NIF: \t" + NIF);
    }
}
