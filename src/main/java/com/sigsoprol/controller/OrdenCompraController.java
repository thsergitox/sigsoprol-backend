package com.sigsoprol.controller;

import com.sigsoprol.model.OrdenCompra;
import com.sigsoprol.service.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordencompra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    @GetMapping("/all")
    public ResponseEntity<List<OrdenCompra>> getAllOrdenCompra() {
        try {
            List<OrdenCompra> ordenCompra = ordenCompraService.findAll();
            return new ResponseEntity<>(ordenCompra, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> getOrdenCompraById(@PathVariable Long id) {
        try {
            Optional<OrdenCompra> ordenCompra = ordenCompraService.findById(id);
            return ordenCompra.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<OrdenCompra> createOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        try {
            OrdenCompra newOrdenCompra = ordenCompraService.save(ordenCompra);
            return new ResponseEntity<>(newOrdenCompra, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrdenCompra> updateOrdenCompra(@PathVariable Long id, @RequestBody OrdenCompra ordenCompra) {
        try {
            Optional<OrdenCompra> existingOrdenCompra = ordenCompraService.findById(id);
            if (existingOrdenCompra.isPresent()) {
                ordenCompra.setId(id);
                OrdenCompra updatedOrdenCompra = ordenCompraService.save(ordenCompra);
                return new ResponseEntity<>(updatedOrdenCompra, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteOrdenCompra(@PathVariable Long id) {
        try {
            ordenCompraService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
