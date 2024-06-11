package com.sigsoprol.service;

import com.sigsoprol.model.Cotizaciones;
import com.sigsoprol.repository.CotizacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CotizacionesService {

    @Autowired
    private CotizacionesRepository cotizacionesRepository;

    public List<Cotizaciones> findAll() {
        return cotizacionesRepository.findAll();
    }

    public Optional<Cotizaciones> findById(Long id) {
        return cotizacionesRepository.findById(id);
    }

    public Cotizaciones save(Cotizaciones cotizaciones) {
        return cotizacionesRepository.save(cotizaciones);
    }

    public void deleteById(Long id) {
        cotizacionesRepository.deleteById(id);
    }
}
