package es.eoi.facturacion;

public class LineaDeFactura {
    private int unidades;
    private String nombreCacharro;
    private float precioUn;
    private float precioLinea;

    public LineaDeFactura(int unidades, Productos producto) {
        this.unidades = unidades;
        this.nombreCacharro = producto.getDescripcion();
        this.precioUn = producto.getPrecio();
        this.precioLinea = unidades * this.precioUn;
    }

    public String imprimeLinea() {
        return "\t\t" + unidades + "\t\t" + nombreCacharro + "\t\t" +
                precioUn + "\t" + precioLinea;
    }

    public float getPrecioLinea() {
        return precioLinea;
    }
}
