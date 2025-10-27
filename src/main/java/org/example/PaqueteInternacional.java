package org.example;

/**
 * autores: Génesis Escobar - Jair Patiño
 * descripcion: Subclase que representa un paquete internacional.
 * incluye validaciones especificas de peso, region y calculo de costo.
 */

public class PaqueteInternacional extends Paquete {
    private String region;

    /**
     * Constructor de PaqueteInternacional
     * @param peso Peso del paquete en kg (máximo 30 kg)
     * @param largo Largo en cm
     * @param ancho Ancho en cm
     * @param alto Alto en cm
     * @param contenido Descripción del contenido
     * @param region Región de envío (LATAM, NORTH_AMERICA, EUROPE, ASIA, AFRICA, OCEANIA)
     * @throws IllegalArgumentException si el peso excede 30 kg o la región es inválida
     */
    public PaqueteInternacional(double peso, double largo, double ancho, double alto, String contenido, String region) {
        super(peso, largo, ancho, alto, contenido);

        // Validacion especifica: peso maximo para envios internacionales
        if (peso > 30) {
            throw new IllegalArgumentException("El peso máximo para envíos internacionales es 30 kg");
        }
        setRegion(region);
    }

    //setters con validacion

    /**
     * Asigna la region del paquete con validacion
     * @param region Region a asignar
     * @throws IllegalArgumentException si es nula o no esta en la lista valida
     */
    public void setRegion(String region) {
        if (region == null) {
            throw new IllegalArgumentException("La región no puede ser nula");
        }
        region = region.toUpperCase().trim();// normaliza el texto todo en mayuscula

        if (!region.equals("LATAM") && !region.equals("NORTH_AMERICA") && !region.equals("EUROPE")
                && !region.equals("ASIA") && !region.equals("AFRICA") && !region.equals("OCEANIA")) {
            throw new IllegalArgumentException("Región invalida. Los valores válidos son: LATAM, NORTH_AMERICA, EUROPE, ASIA, AFRICA, OCEANIA");
        }
        this.region = region;
    }

    //getter
    /**
     * Devuelve la región del paquete
     * @return región
     */
    public String getRegion() {
        return region;
    }


    /**
     * Calcula el costo total del paquete internacional
     * Considera:
     * - Tarifa base: 10 + 2.5 * peso
     * - Arancel según tipo de contenido
     * - Recargo por región
     * - Recargo volumétrico (si aplica)
     * @return costo total
     */
    @Override
    public double calcularCosto() {
        double costo = 10.00 + (2.50 * getPeso()); // tarifa base

        // arancel segun tipo de contenido
        String contenido = getContenido().toUpperCase().trim();
        switch (contenido) {
            case "DOCUMENTOS":
                costo += 0;
                break;
            case "ELECTRONICA":
                costo += 8;
                break;
            case "TEXTILES":
                costo += 4;
                break;
            default:
                costo += 6;
        }
        // recargo por region
        switch (region) {
            case "LATAM":
                costo += 5;
                break;
            case "EUROPE":
            case "NORTH_AMERICA":
                costo += 8;
                break;
            case "ASIA":
            case "AFRICA":
            case "OCEANIA":
                costo += 12;
                break;
        }

        // recargo volumetrico
        costo += recargoVolumetrico();

        return costo;
    }
    /**
     * Genera un resumen del paquete internacional
     * Combina el resumen de la clase base con la región
     * @return resumen completo en formato String
     */
    @Override
    public String resumen() {
        return super.resumen() + "\nRegión: " + region;
    }
}
