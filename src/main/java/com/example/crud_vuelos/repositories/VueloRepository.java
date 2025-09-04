package com.example.crud_vuelos.repositories;

import org.springframework.stereotype.Repository;
import com.example.crud_vuelos.models.Vuelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Repositorio de vuelos en memoria.
 * Se encarga de almacenar, recuperar, crear y eliminar vuelos, usando una lista interna.
 */
@Repository
public class VueloRepository {

    //lista interna que almacena todos los vuelos
    private final List<Vuelo> vuelos = new ArrayList<>();


    //Constructor que inicializa el repositorio con 10 vuelos,
    // esto permite que la api tenga datos de prueba para iniciar.
    public VueloRepository() {
        vuelos.add(new Vuelo(1, "h001-v", "Iberia", "Madrid", "Buenos Aires", LocalDate.of(2025, 03, 10), LocalDate.of(2025, 03, 11)));
        vuelos.add(new Vuelo(2, "T101-X", "Turkish", "Estambul", "New York", LocalDate.of(2025, 03, 05), LocalDate.of(2025, 03, 06)));
        vuelos.add(new Vuelo(03, "E300-A", "Emirates", "Dubai", "Tokyo", LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 2)));
        vuelos.add(new Vuelo(4, "A500-B", "Aerolineas Argentinas", "Buenos Aires", "Madrid", LocalDate.of(2025, 2, 20), LocalDate.of(2025, 02, 21)));
        vuelos.add(new Vuelo(5, "L900-C", "Lufthansa", "Berlin", "Toronto", LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 15)));
        vuelos.add(new Vuelo(6, "AF200-D", "Air France", "Paris", "New York", LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 11)));
        vuelos.add(new Vuelo(7, "QF111-E", "Qantas", "Sydney", "Los Angeles", LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 1)));
        vuelos.add(new Vuelo(8, "DL300-F", "Delta", "Atlanta", "Miami", LocalDate.of(2025, 1, 12), LocalDate.of(2025, 1, 12)));
        vuelos.add(new Vuelo(9, "BA400-G", "British Airways", "Londres", "Roma", LocalDate.of(2025, 8, 20), LocalDate.of(2025, 8, 20)));
        vuelos.add(new Vuelo(10, "KL500-H", "KLM", "Amsterdam", "San Pablo", LocalDate.of(2025, 10, 10), LocalDate.of(2025, 10, 11)));

    }

    //Devuelve todos los vuelos almacenados.
    public List<Vuelo> findAll() {
        return vuelos;
    }

    //Busca un vuelo por su ID.
    //Se recorre la lista y se devuelve el primer vuelo que coincida.
    public Vuelo findById(int id) {
        return vuelos.stream().filter(v -> v.getId() == id)    // filtramos por id
                .findFirst()                                     //tomamos el primero que coincida
                .orElse(null);                            // si no hay coincidencia, devolvemos null

    }

    // Guarda un nuevo vuelo en la lista.
// Si el vuelo no tiene ID o el ID ya existe, se le asigna uno nuevo.
    public Vuelo save(Vuelo vuelo) {
        if (vuelo.getId() == 0 || findById(vuelo.getId()) != null) {
            int nuevoId = vuelos.size() + 1;
            vuelo.setId(nuevoId);
        }
        vuelos.add(vuelo);
        return vuelo;
    }

    //Elimina un vuelo segun su ID.
    public Boolean delete(int id) {
        return vuelos.removeIf(v -> v.getId() == id);  //removeIf recorre la lista y elimina el vuelo que cumpla la condicion
    }

    // Actualizar un vuelo (reemplaza los datos del vuelo con mismo ID)
    public Vuelo update(int id, Vuelo nuevoVuelo) {
        for (int i = 0; i < vuelos.size(); i++) {
            if (vuelos.get(i).getId() == id) {
                vuelos.set(i, nuevoVuelo);
                return nuevoVuelo;
            }
        }
        return null; // si no se ha encontrado
    }

}
