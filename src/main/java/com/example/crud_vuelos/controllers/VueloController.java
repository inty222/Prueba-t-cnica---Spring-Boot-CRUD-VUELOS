package com.example.crud_vuelos.controllers;

import com.example.crud_vuelos.models.Vuelo;
import com.example.crud_vuelos.services.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 * Controlador REST para la gestion de vuelos.
 * Expone los endpoints de la API relacionados con la entidad Vuelo,
 * permite consultar, crear, actualizar y eliminar vuelos.
 */
@RestController
@RequestMapping("/vuelos") // ruta base de la API
public class VueloController {

    @Autowired
    private VueloService vueloService;

    // GET /vuelos con filtros y ordenacion
    @GetMapping
    public List<Vuelo> getAllVuelos(@RequestParam(required = false) String empresa,
                                    @RequestParam(required = false) String lugarLlegada,
                                    @RequestParam(required = false) String fechaSalida,
                                    @RequestParam(required = false) String ordenarPor) {

        LocalDate fecha = null;
        if (fechaSalida != null) {
            fecha = LocalDate.parse(fechaSalida); // convertimos string a LocalDate
        }

        // Filtrar ,si hay filtros o devolver todo
        List<Vuelo> vuelos;
        if (empresa != null || lugarLlegada != null || fecha != null) {
            vuelos = vueloService.filtrarVuelos(empresa, lugarLlegada, fecha);
        } else {
            vuelos = vueloService.getAllVuelos();
        }

        //  Ordenar si se recibe el parámetro ordenarPor
        if ("empresa".equalsIgnoreCase(ordenarPor)) {
            vuelos.sort(Comparator.comparing(Vuelo::getEmpresa));
        } else if ("lugarLlegada".equalsIgnoreCase(ordenarPor)) {
            vuelos.sort(Comparator.comparing(Vuelo::getLugarLlegada));
        }

        return vuelos;
    }

    // Obtener vuelos por ID.
    @GetMapping("/{id}")
    public Vuelo getById(@PathVariable int id) {
        return vueloService.getVueloById(id);
    }

    // Crear un nuevo vuelo.
    @PostMapping
    public Vuelo createVuelo(@RequestBody Vuelo vuelo) {
        return vueloService.saveVuelo(vuelo);
    }

    // Actualizar un vuelo existente.
    @PutMapping("/{id}")
    public Vuelo updateVuelo(@PathVariable int id, @RequestBody Vuelo vuelo) {
        return vueloService.updateVuelo(id, vuelo);
    }

    // Eliminar un vuelo.
    @DeleteMapping("/{id}")
    public boolean deleteVuelo(@PathVariable int id) {
        return vueloService.deleteVuelo(id);
    }
}
