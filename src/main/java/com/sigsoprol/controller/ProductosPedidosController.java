package com.sigsoprol.controller;

import com.sigsoprol.model.ProductosPedidos;
import com.sigsoprol.service.ProductosPedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productospedidos")
public class ProductosPedidosController {

    @Autowired
    private ProductosPedidosService productosPedidosService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductosPedidos>> getAllProductosPedidos() {
        try {
            List<ProductosPedidos> productosPedidos = productosPedidosService.findAll();
            return new ResponseEntity<>(productosPedidos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosPedidos> getProductosPedidosById(@PathVariable Long id) {
        try {
            Optional<ProductosPedidos> productosPedidos = productosPedidosService.findById(id);
            return productosPedidos.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ProductosPedidos> createProductosPedidos(@RequestBody ProductosPedidos productosPedidos) {
        try {
            ProductosPedidos newProductosPedidos = productosPedidosService.save(productosPedidos);
            return new ResponseEntity<>(newProductosPedidos, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductosPedidos> updateProductosPedidos(@PathVariable Long id, @RequestBody ProductosPedidos productosPedidos) {
        try {
            Optional<ProductosPedidos> existingProductosPedidos = productosPedidosService.findById(id);
            if (existingProductosPedidos.isPresent()) {
                productosPedidos.setId(id);
                ProductosPedidos updatedProductosPedidos = productosPedidosService.save(productosPedidos);
                return new ResponseEntity<>(updatedProductosPedidos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProductosPedidos(@PathVariable Long id) {
        try {
            productosPedidosService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
