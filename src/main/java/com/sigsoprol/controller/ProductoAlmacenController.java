package com.sigsoprol.controller;

import com.sigsoprol.model.ProductoAlmacen;
import com.sigsoprol.service.ProductoAlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productoalmacen")
public class ProductoAlmacenController {

    @Autowired
    private ProductoAlmacenService productoAlmacenService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductoAlmacen>> getAllProductoAlmacen() {
        try {
            List<ProductoAlmacen> productosAlmacen = productoAlmacenService.findAll();
            return new ResponseEntity<>(productosAlmacen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoAlmacen> getProductoAlmacenById(@PathVariable Long id) {
        try {
            Optional<ProductoAlmacen> productoAlmacen = productoAlmacenService.findById(id);
            return productoAlmacen.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ProductoAlmacen> createProductoAlmacen(@RequestBody ProductoAlmacen productoAlmacen) {
        try {
            ProductoAlmacen newProductoAlmacen = productoAlmacenService.save(productoAlmacen);
            return new ResponseEntity<>(newProductoAlmacen, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductoAlmacen> updateProductoAlmacen(@PathVariable Long id, @RequestBody ProductoAlmacen productoAlmacen) {
        try {
            Optional<ProductoAlmacen> existingProductoAlmacen = productoAlmacenService.findById(id);
            if (existingProductoAlmacen.isPresent()) {
                productoAlmacen.setId(id);
                ProductoAlmacen updatedProductoAlmacen = productoAlmacenService.save(productoAlmacen);
                return new ResponseEntity<>(updatedProductoAlmacen, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProductoAlmacen(@PathVariable Long id) {
        try {
            productoAlmacenService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
