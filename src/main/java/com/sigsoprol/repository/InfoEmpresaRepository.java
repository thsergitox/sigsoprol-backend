
package com.sigsoprol.repository;

import com.sigsoprol.model.InfoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoEmpresaRepository extends  JpaRepository<InfoEmpresa, Long>{
    
}
