package com.sigsoprol.controller;

import com.sigsoprol.model.Empleado;
import com.sigsoprol.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/all")
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        try {
            List<Empleado> empleados = empleadoService.findAll();
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        try {
            Optional<Empleado> empleado = empleadoService.findById(id);
            return empleado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado newEmpleado = empleadoService.save(empleado);
            return new ResponseEntity<>(newEmpleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        try {
            Optional<Empleado> existingEmpleado = empleadoService.findById(id);
            if (existingEmpleado.isPresent()) {
                empleado.setId(id);
                Empleado updatedEmpleado = empleadoService.save(empleado);
                return new ResponseEntity<>(updatedEmpleado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmpleado(@PathVariable Long id) {
        try {
            empleadoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
