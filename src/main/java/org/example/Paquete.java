package org.example;

/**
 * autores: genesis escobar, jair patiño
 * descripcion: representa un paquete generico
 * //es abstracta porque el calculo de costo depende del tipo de paquete.
 */

public abstract class Paquete {
    private double peso; //kg
    private String contenido;
    private double largo, ancho, alto;

    /**
     * Constructor de Paquete
     * Inicializa todos los atributos y aplica validaciones
     * @param peso Peso del paquete en kg
     * @param largo Largo en cm
     * @param ancho Ancho en cm
     * @param alto Alto en cm
     * @param contenido Descripcion del contenido
     */
    public Paquete(double peso, double largo, double ancho, double alto, String contenido) {
        setPeso(peso);// valida y asigna
        setLargo(largo);
        setAncho(ancho);
        setAlto(alto);
        setContenido(contenido);
    }
    //metodos de VALIDACIONES con IllegalArgumentException

    /**
     * Valida que un valor numerico este dentro de un rango permitido
     * @param valor Valor a validar
     * @param min Valor minimo permitido
     * @param max Valor maximo permitido
     * @param nombre Nombre del atributo para mostrar en el mensaje
     * @throws IllegalArgumentException si el valor esta fuera del rango
     */
    private void validarRango(double valor, double min, double max, String nombre) {
        if (valor < min || valor > max) {
            throw new IllegalArgumentException(nombre + " debe estar entre " + min + " y " + max);
        }
    }


    /**
     * Valida que un String no sea nulo ni vacío
     * @param valor String a validar
     * @param nombre Nombre del atributo para mostrar en el mensaje
     * @throws IllegalArgumentException si es nulo o vacío
     */
    private void validarString(String valor, String nombre) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nombre + " no puede ser vacío o nulo");
        }
    }

    //--------- setters con validacion

    // setter para peso, verifica rango entre 0.01 y 50
    public void setPeso(double peso) {
        validarRango(peso, 0.01, 50.0, "Peso");
        this.peso = peso;
    }

    //setter para largo, verifica que el rango sea entre 1 y 200
    public void setLargo(double largo) {
        validarRango(largo, 1, 200, "Largo");
        this.largo = largo;
    }

    //setter ancho, verifica que el rango sea entre 1 y 200
    public void setAncho(double ancho) {
        validarRango(ancho, 1, 200, "Ancho");
        this.ancho = ancho;
    }

    //setter alto, verifica que el rango sea entre 1 y 200
    public void setAlto(double alto) {
        validarRango(alto, 1, 200, "Alto");
        this.alto = alto;
    }


    //setter para contenido, verifica que no sea nulo o vacio
    public void setContenido(String contenido) {
        validarString(contenido, "Contenido");
        this.contenido = contenido;
    }
    // getters validacion
    public double getPeso() {
        return peso;
    }

    public double getLargo() {
        return largo;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }

    public String getContenido() {
        return contenido;
    }

    /**
     * Metodo que calcula el volumen del paquete en litros
     * @return volumen en litros
     */
    public final double volumenLitros() {
        return (largo * ancho * alto) / 1000.0;
    }

    /**
     * Metodo que calcula un posible recargo volumétrico
     * @return 5.0 si el volumen > 100L, 0.0 si no
     */
    protected double recargoVolumetrico() {
        return volumenLitros() > 100 ? 5.0 : 0.0;
    }

    /**
     * Metodo abstracto para calcular el costo total
     * Cada subclase (PaqueteNacional, PaqueteInternacional) lo implementa segun sus reglas
     * @return costo total del paquete
     */
    public abstract double calcularCosto();

    /**
     * Genera un resumen completo del paquete
     * Incluye tipo, peso, volumen, contenido y costo total
     * @return String con el resumen
     */
    public String resumen() {
        return "Tipo: " + this.getClass().getSimpleName() + //sirve para identificar dinamicamente el tipo de objeto
                "\nPeso: " + peso + " kg" +
                "\nVolumen: " + volumenLitros() + " L" +
                "\nContenido: " + contenido +
                "\nCosto Total: $" + calcularCosto();
    }

}
