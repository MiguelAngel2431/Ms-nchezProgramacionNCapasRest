
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryDireccion extends JpaRepository<Direccion, Integer> {
    //List<Direccion> findDireccionByIdUsuario(int IdUsuario);
}
