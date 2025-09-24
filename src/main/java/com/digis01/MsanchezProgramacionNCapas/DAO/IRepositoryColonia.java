
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Colonia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRepositoryColonia extends JpaRepository<Colonia, Integer> {
    @Query("SELECT c FROM Colonia c WHERE c.municipio.IdMunicipio = :idMunicipio")
    List<Colonia> findByMunicipio_IdMunicipio(@Param("idMunicipio")int idMunicipio);
}
