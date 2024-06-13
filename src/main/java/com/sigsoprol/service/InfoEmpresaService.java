package com.sigsoprol.service;

import com.sigsoprol.model.InfoEmpresa;
import com.sigsoprol.repository.InfoEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoEmpresaService {

    @Autowired
    private InfoEmpresaRepository infoEmpresaRepository;

    public List<InfoEmpresa> findAll() {
        return infoEmpresaRepository.findAll();
    }

    public Optional<InfoEmpresa> findById(Long id) {
        return infoEmpresaRepository.findById(id);
    }

    public InfoEmpresa save(InfoEmpresa infoEmpresa) {
        return infoEmpresaRepository.save(infoEmpresa);
    }

    public void deleteById(Long id) {
        infoEmpresaRepository.deleteById(id);
    }
}
