package org.example;

/**
 * autores: genesis escobar, jair patiño
 * descripcion: clase que representa un paquete nacional, heredando de Paquete.
 * incluye validación de zona, calculo de costo y resumen del paquete.
 */

public class PaqueteNacional extends Paquete{
    private String zona;

    /**
     * Constructor de PaqueteNacional
     * @param peso Peso del paquete en kg
     * @param largo Largo en cm
     * @param ancho Ancho en cm
     * @param alto Alto en cm
     * @param contenido Descripcion del contenido
     * @param zona Zona de envio (COSTA, SIERRA, ORIENTE, GALAPAGOS)
     */
    public PaqueteNacional(double peso, double largo, double ancho, double alto, String contenido, String zona) {
        super(peso, largo, ancho, alto, contenido);
        setZona(zona); //  // asigna la zona usando el setter con validacion
    }

    /**
     * Setter de zona con validación
     * @param zona Zona a asignar
     * @throws IllegalArgumentException si la zona es nula o inválida
     */

    public void setZona(String zona) {
        if (zona == null) {
            throw new IllegalArgumentException("La zona no puede ser nula");
        }
        zona = zona.toUpperCase().trim();// normaliza el texto a mayusculas
        if (!zona.equals("COSTA") && !zona.equals("SIERRA") && !zona.equals("ORIENTE") && !zona.equals("GALAPAGOS")) {
            throw new IllegalArgumentException("Zona invalida. Debe ser: COSTA, SIERRA, ORIENTE o GALAPAGOS");
        }
        this.zona = zona;//devuelve zona
    }

    /**
     * Getter de zona
     * @return zona del paquete
     */

    public String getZona() {
        return zona;
    }

    /**
     * Calcula el costo del envio segun peso, zona y volumen
     * @return costo total del paquete
     */
    @Override
    public double calcularCosto() {
        // tarifa base = 3.00 + (1.20 * peso)
        double costo = 3.00 + (1.20 * getPeso());

        // recargo por zona
        switch (zona) {
            case "ORIENTE":
                costo += 2.00;
                break;
            case "GALAPAGOS":
                costo += 6.00;
                break;
            // COSTA o SIERRA sin recargo
        }

        // recargo volumétrico definido en la clase padre
        costo += recargoVolumetrico();

        return costo;
    }
    /**
     * Genera un resumen del paquete, incluyendo datos de la clase base y la zona
     * @return resumen en formato String
     */
    @Override
    public String resumen() {
        return super.resumen() + "\nZona: " + zona;
    }

}
