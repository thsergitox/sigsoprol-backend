package com.sigsoprol.controller;

import com.sigsoprol.model.Pedido;
import com.sigsoprol.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/all")
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        try {
            List<Pedido> pedidos = pedidoService.findAll();
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        try {
            Optional<Pedido> pedido = pedidoService.findById(id);
            return pedido.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        try {
            Pedido newPedido = pedidoService.save(pedido);
            return new ResponseEntity<>(newPedido, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Optional<Pedido> existingPedido = pedidoService.findById(id);
            if (existingPedido.isPresent()) {
                pedido.setId(id);
                Pedido updatedPedido = pedidoService.save(pedido);
                return new ResponseEntity<>(updatedPedido, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePedido(@PathVariable Long id) {
        try {
            pedidoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
