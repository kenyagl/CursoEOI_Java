package es.eoi.figurasGeometricas;

import es.eoi.figurasGeometricas.FiguraGeometrica;

public class EjemploFiguraGeometrica {
    public static void main(String[] args) {
        FiguraGeometrica triangulo = new FiguraGeometrica("Triángulo", 3, 2.5f);
        System.out.println("triangulo.info() = " + triangulo.info());
        System.out.println("triangulo.superficie() = " + triangulo.superficie());
        System.out.println("--------------------------------------");

        FiguraGeometrica circulo = new FiguraGeometrica("Círculo", 0, 1.5f);
        System.out.println("circulo.info() = " + circulo.info());
        System.out.println("circulo.superficie() = " + circulo.superficie());
        System.out.println("--------------------------------------");

        FiguraGeometrica cuadrado = new FiguraGeometrica("Cuadrado", 4, 5);
        System.out.println("cuadrado.superficie() = " + cuadrado.superficie());
        System.out.println("--------------------------------------");
        
        FiguraGeometrica pentagono = new FiguraGeometrica("Pentágono", 5, 7);
        System.out.println("pentagono.info() = " + pentagono.info());
        System.out.println("pentagono.perimetro() = " + pentagono.perimetro());
        System.out.println("pentagono.superficie() = " + pentagono.superficie());
        System.out.println("--------------------------------------");

        FiguraGeometrica hexagono = new FiguraGeometrica("Hexágono", 6, 3.4f);
        System.out.println("hexagono.info() = " + hexagono.info());
        System.out.println("hexagono.superficie() = " + hexagono.superficie());
        System.out.println("--------------------------------------");
        
    }
}
