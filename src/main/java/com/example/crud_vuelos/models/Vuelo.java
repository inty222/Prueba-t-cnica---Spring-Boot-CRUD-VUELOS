package com.example.crud_vuelos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Clase que representa un Vuelo en el sistema.
 * Contiene informacion basica como la empresa, origen, destino y fechas.
 */
@Data                // Genera automáticamente getters, setters.
@AllArgsConstructor  // Genera un constructor con todos los atributos de la clase como parametros.
@NoArgsConstructor   // Genera un constructor vacio, sin parametros.
public class Vuelo {
    private int id;
    private String nombreVuelo;
    private String empresa;
    private String lugarSalida;
    private String lugarLlegada;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
}
