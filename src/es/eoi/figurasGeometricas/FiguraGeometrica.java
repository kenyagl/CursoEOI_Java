package es.eoi.figurasGeometricas;

public class FiguraGeometrica {

    //Atributos
    String nombre;
    int numLados;
    float lado;

    //Métodos de la clase (comportamientos)
    //Perímetro, superficie, info

    public FiguraGeometrica(String nombre, int numLados, float lado) {
        this.nombre = nombre;
        this.numLados = numLados;
        this.lado = lado;
    }

    public double perimetro() {
        if (numLados > 0) {
            return numLados * lado;
        } else {
            return Math.PI * lado;
        }
    }

    public double superficie() {
        double sup = 0;
        if (numLados == 0) {
            //pi*r^2
            sup = Math.PI * Math.pow(lado, 2);
       // } else if (numLados == 3) {
            //base*altura/2
            //double h = Math.sqrt(Math.pow(lado, 2) - Math.pow((lado/2), 2)); //pitágoras
            //sup = (lado * h) / 2;
        //} else if (numLados == 4) {
           // sup =  lado * lado; //funciona también como polígono regular, pero es ligeramente menos exacto por el uso de PI
        } else if (numLados >= 3) {
            //perimetro * apotema/2
            double alfa = 2*Math.PI / numLados; //en radianes para que funcione en el math
            double apotema = lado / (2 * Math.tan((alfa / 2))); //calculo apotema = lado / (2 * tangente (alfa/2))

            sup = (perimetro() * apotema)/2;
        }
        return sup;
    }

    public String info() {
        return "es.eoi.figurasGeometricas.FiguraGeometrica{" +
                "\nnombre='" + nombre + '\'' +
                ",\nnumLados=" + numLados +
                ",\nlado=" + lado +
                '}';
    }
}
