package com.example.crud_vuelos.services;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.example.crud_vuelos.models.Vuelo;
import com.example.crud_vuelos.repositories.VueloRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service // Marca esta clase como un "Servicio" gestionado por Spring
public class VueloService {
    // atributo donde guardamos el objeto VuelosRepository
    private final VueloRepository vueloRepository;

    //Inyeccion de dependencias por constructor
    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    // obtener todos los vuelos  ordenados por fecha de salida.
    public List<Vuelo> getAllVuelos() {
        return vueloRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Vuelo::getFechaSalida))
                .collect(Collectors.toList());
    }

    //Busca un vuelo por su ID.
    public Vuelo getVueloById(int id) {
        return vueloRepository.findAll()
                .stream()
                .filter(vuelo -> vuelo.getId() == id)
                .findFirst()
                .orElse(null); // devuelve null si no encuentra
    }

    //Guarda un nuevo vuelo.
    public Vuelo saveVuelo(Vuelo vuelo) {
        validarVuelo(vuelo); // sirve para la excepcion si los datos son invalidos
        return vueloRepository.save(vuelo);
    }

    //elimina un vuelo por ID
    public boolean deleteVuelo(int id) {
        return vueloRepository.delete(id);
    }

    // Actualizar un vuelo existente
    public Vuelo updateVuelo(int id, Vuelo vuelo) {
        validarVuelo(vuelo);
        return vueloRepository.update(id, vuelo);
    }

    // Metodo privado para validar que los datos del vuelo sean correctos
    private void validarVuelo(Vuelo vuelo) {
        if (vuelo.getNombreVuelo() == null || vuelo.getNombreVuelo().isEmpty()) {
            throw new IllegalArgumentException("El nombre del vuelo no puede ser nulo o vacio.");
        }
        if (vuelo.getEmpresa() == null || vuelo.getEmpresa().isEmpty()) {
            throw new IllegalArgumentException("La empresa no puede ser nula o vacia.");
        }
        if (vuelo.getLugarSalida() == null || vuelo.getLugarSalida().isEmpty()) {
            throw new IllegalArgumentException("El lugar de salida no puede ser nulo o vacio.");
        }
        if (vuelo.getLugarLlegada() == null || vuelo.getLugarLlegada().isEmpty()) {
            throw new IllegalArgumentException("El lugar de llegada no puede ser nulo o vacio.");
        }
        if (vuelo.getFechaSalida() == null || vuelo.getFechaLlegada() == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas.");
        }
        if (vuelo.getFechaSalida().isAfter(vuelo.getFechaLlegada())) {
            throw new IllegalArgumentException("La fecha de salida no puede ser posterior a la fecha de llegada.");
        }
    }

    // Filtrar vuelos por empresa, lugar de llegada o fecha de salida
    public List<Vuelo> filtrarVuelos(String empresa, String lugarLlegada, LocalDate fecha) {
        return vueloRepository.findAll()
                .stream()
                .filter(vuelo -> empresa == null || vuelo.getEmpresa().equalsIgnoreCase(empresa))
                .filter(vuelo -> lugarLlegada == null || vuelo.getLugarLlegada().equalsIgnoreCase(lugarLlegada))
                .filter(vuelo -> fecha == null || vuelo.getFechaSalida().equals(fecha))
                .collect(Collectors.toList());
    }
}



