package es.eoi.facturacion;
import java.text.DateFormat;
import java.util.*;

public class Facturacion {
    //Nombre comercio y dirección, NIF
    //Datos del cliente: nombre completo, NIF, dirección, contacto //por consola
    //Fecha y lugar
    //Nº factura (aleatorio 10 dígitos)
    // Unidades (UDS), producto, precio unitario, precio total línea //por consola
    //total suma todo
    //iva (21%)
    //A pagar
    //Investigar: estructura de datos no array para ir añadiendo los datos sin definir longitud --> LIST


    public static void main(String[] args) {

        //Datos negocio
        String nombreComercio = "Electrónica EOI";
        String dirNegocio = "C/ Java 1234";
        String NIFNegocio = "123456789P";
        String tlf = "914790401";

        //1- Introducir datos cliente por consola
        Scanner entrada = new Scanner(System.in);
        String nombre = introduceDato("Nombre del cliente", entrada);
        String apellidos = introduceDato("Apellidos", entrada);
        String direccion = introduceDato("Dirección", entrada);
        String contacto = introduceDato("Contacto", entrada);
        String NIF = introduceDato("NIF", entrada);


        Cliente cliente1 = new Cliente(nombre, apellidos, direccion, contacto, NIF);


        //2. Lugar y fecha

        String lugar = introduceDato("Lugar", entrada);

        Locale locale = new Locale("es", "ES");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
        String fecha = dateFormat.format(new Date());
        String hora = timeFormat.format(new Date());

        //LocalDate fecha = LocalDate.now(ZoneId.of("Europe/Madrid"));


        //3. Nº factura
        long numFactura = (long) (Math.random()*Math.pow(10,10));

        //4. Lista de factura
        System.out.println("\n\n Introduce los productos: ");
        boolean seguirCobrando = true;
        Productos producto;
        //LineaDeFactura[] facturaFinal = new LineaDeFactura[10];
        List<LineaDeFactura> facturaFinal = new ArrayList(); //interfaz que crea un "array expandible" con cosas que los arrays normales no hacen
        //hay que decirle de qué está compuesta (en este caso LineaDeFactura) porque el default es un objeto

        while (seguirCobrando) {
            int unidades = Integer.parseInt(introduceDato("Número de unidades (0 -> salir)",  entrada));
            if (unidades == 0) {
                seguirCobrando = false;
            } else {
                String varProducto = "Introducir producto: \n" +
                        "1 = PORTÁTIL \n" +
                        "2 = RATÓN \n" +
                        "3 = DISCO DURO \n" +
                        "4 = MONITOR \n \t\t";

                switch (introduceDato(varProducto, entrada)) {
                    case "1":
                        producto = Productos.PORTATIL;
                        break;
                    case "2":
                        producto = Productos.RATON;
                        break;
                    case "3":
                        producto = Productos.DISCODURO;
                        break;
                    case "4":
                        producto = Productos.MONITOR;
                        break;
                    default:
                        producto = Productos.RATON;
                }

                LineaDeFactura linea = new LineaDeFactura(unidades, producto);

                facturaFinal.add(linea);
            }
        }
        entrada.close();

        //5. Totales
        System.out.println("\n----------------------------------------------------");
        System.out.println("\n\n" + lugar + ", " + fecha + ", " + hora);
        System.out.println("\n\nComercio: \t" +
                "nombre: \t" + nombreComercio + '\n' +
                "\t\t\t Dirección: \t" + dirNegocio + '\n' +
                "\t\t\t Contacto: \t" + tlf + '\n' +
                "\t\t\t NIF: \t" + NIFNegocio);
        System.out.println("\n");
        cliente1.datosClientes();
        System.out.println("\nNº Factura = " + numFactura);

        System.out.println("----------------------------------------------------");
        System.out.println("\n\tUnidades" + "\tProducto" + "\t\t\t\tPrecio" + "\tTotal" + "\n");

        float totalFactura = 0;
        for (LineaDeFactura lin : facturaFinal) {
                System.out.println(lin.imprimeLinea());
                totalFactura += lin.getPrecioLinea();
        }

        float tipoIVA = 21;

        float IVA = totalFactura * tipoIVA / 100;
        float cantidadPagar = totalFactura + IVA;

        System.out.println("----------------------------------------------------");
        System.out.printf("\n\t\t\t\t\t\t\t\tTotal factura \t %.2f \n\t\t\t\t\t\t\t\tIVA \t\t\t %.2f \n\t\t\t\t\t\t\t\tA pagar \t\t %.2f",
                totalFactura, IVA, cantidadPagar);
        System.out.println("\n\n");

        //System.out.printf() --> imprime con formato
        //System.out.printf("A pagar = %.2f", cantidadPagar); //saca el número con dos decimales
    }

    public static String introduceDato(String mensaje, Scanner entrada) {
        System.out.print(mensaje + ": ");
        return entrada.nextLine();
    }
}