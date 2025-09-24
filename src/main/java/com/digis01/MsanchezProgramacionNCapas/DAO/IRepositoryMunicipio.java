
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Municipio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRepositoryMunicipio extends JpaRepository<Municipio, Integer> {
    @Query("SELECT m FROM Municipio m WHERE m.estado.IdEstado = :idEstado")
    List<Municipio> findByEstado_IdEstado(@Param("idEstado")int idEstado);
}
