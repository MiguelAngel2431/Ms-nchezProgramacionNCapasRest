
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Rol;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolJPADAOImplementation implements IRolJPADAO {
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<Rol> queryRol = entityManager.createQuery("FROM Rol", Rol.class);
            result.object = queryRol.getResultList();
            
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
    
}
