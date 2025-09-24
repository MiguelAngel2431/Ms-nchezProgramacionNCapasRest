
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Estado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRepositoryEstado extends JpaRepository<Estado, Integer> {
    @Query("SELECT e FROM Estado e WHERE e.pais.IdPais = :idPais")
    List<Estado> findByPais_IdPais(@Param("idPais")int idPais);
}
