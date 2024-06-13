package com.sigsoprol.controller;

import com.sigsoprol.model.InfoEmpresa;
import com.sigsoprol.service.InfoEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/infoempresa")
public class InfoEmpresaController {

    @Autowired
    private InfoEmpresaService infoEmpresaService;

    @GetMapping("/all")
    public ResponseEntity<List<InfoEmpresa>> getAllInfoEmpresa() {
        try {
            List<InfoEmpresa> infoEmpresa = infoEmpresaService.findAll();
            return new ResponseEntity<>(infoEmpresa, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoEmpresa> getInfoEmpresaById(@PathVariable Long id) {
        try {
            Optional<InfoEmpresa> infoEmpresa = infoEmpresaService.findById(id);
            return infoEmpresa.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<InfoEmpresa> createInfoEmpresa(@RequestBody InfoEmpresa infoEmpresa) {
        try {
            InfoEmpresa newInfoEmpresa = infoEmpresaService.save(infoEmpresa);
            return new ResponseEntity<>(newInfoEmpresa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InfoEmpresa> updateInfoEmpresa(@PathVariable Long id, @RequestBody InfoEmpresa infoEmpresa) {
        try {
            Optional<InfoEmpresa> existingInfoEmpresa = infoEmpresaService.findById(id);
            if (existingInfoEmpresa.isPresent()) {
                infoEmpresa.setId(id);
                InfoEmpresa updatedInfoEmpresa = infoEmpresaService.save(infoEmpresa);
                return new ResponseEntity<>(updatedInfoEmpresa, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteInfoEmpresa(@PathVariable Long id) {
        try {
            infoEmpresaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
