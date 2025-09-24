
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryPais extends JpaRepository<Pais, Integer> {
    
}
