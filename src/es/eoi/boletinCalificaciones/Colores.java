package es.eoi.boletinCalificaciones;

public enum Colores {
    RESET ("\u001B[0m"),
    BLACK ("\u001B[30m"),
    RED ("\u001B[31m"),
    GREEN ("\u001B[32m"),
    YELLOW ("\u001B[33m"),
    BLUE ("\u001B[34m"),
    PURPLE ("\u001B[35m"),
    CYAN ("\u001B[36m"),
    WHITE ("\u001B[37m"),

    BOLD ("\033[0;1m");

    private final String codColor;

    Colores(String codColor) {
        this.codColor = codColor;
    }

    public String getCodColor() {
        return codColor;
    }

    public static String cambiarColor(String texto, Colores color) {
        return color.getCodColor() + texto + Colores.RESET.getCodColor();
    }

    public static String negrita(String texto) {
        return BOLD.getCodColor() + texto + Colores.RESET.getCodColor();
    }
}
