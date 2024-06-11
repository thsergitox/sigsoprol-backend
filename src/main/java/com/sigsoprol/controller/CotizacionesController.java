package com.sigsoprol.controller;

import com.sigsoprol.model.Cotizaciones;
import com.sigsoprol.service.CotizacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionesController {

    @Autowired
    private CotizacionesService cotizacionesService;

    @GetMapping("/all")
    public ResponseEntity<List<Cotizaciones>> getAllCotizaciones() {
        try {
            List<Cotizaciones> cotizaciones = cotizacionesService.findAll();
            return new ResponseEntity<>(cotizaciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cotizaciones> getCotizacionesById(@PathVariable Long id) {
        try {
            Optional<Cotizaciones> cotizaciones = cotizacionesService.findById(id);
            return cotizaciones.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Cotizaciones> createCotizaciones(@RequestBody Cotizaciones cotizaciones) {
        try {
            Cotizaciones newCotizaciones = cotizacionesService.save(cotizaciones);
            return new ResponseEntity<>(newCotizaciones, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cotizaciones> updateCotizaciones(@PathVariable Long id, @RequestBody Cotizaciones cotizaciones) {
        try {
            Optional<Cotizaciones> existingCotizaciones = cotizacionesService.findById(id);
            if (existingCotizaciones.isPresent()) {
                cotizaciones.setId(id);
                Cotizaciones updatedCotizaciones = cotizacionesService.save(cotizaciones);
                return new ResponseEntity<>(updatedCotizaciones, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCotizaciones(@PathVariable Long id) {
        try {
            cotizacionesService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
