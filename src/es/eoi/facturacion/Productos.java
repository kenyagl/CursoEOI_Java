package es.eoi.facturacion;

public enum Productos{
    PORTATIL("Macbook Pro 15''", 1999.99f),
    RATON("Ratón intel inalámbrico", 100.5f),
    DISCODURO("Disco duro externo 1T", 199.50f),
    MONITOR("Monitor 17''", 99.99f);

    private String descripcion;
    private float precio;

    Productos(String descripcion, float precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }
}
