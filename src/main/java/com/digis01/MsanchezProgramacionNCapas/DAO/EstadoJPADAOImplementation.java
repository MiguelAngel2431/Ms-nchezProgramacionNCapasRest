package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Estado;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoJPADAOImplementation implements IEstadoJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result EstadoByIdPais(int IdPais) {
        Result result = new Result();

        try {

            Estado estadoJPA = entityManager.find(Estado.class, IdPais);

            if (estadoJPA != null) {
                TypedQuery<Estado> queryEstados = entityManager.createQuery("FROM Estado where Pais.IdPais = :IdPais ", Estado.class);
                queryEstados.setParameter("IdPais", IdPais);
                
                result.object = queryEstados.getResultList();
                result.correct = true;
                result.status = 200;
                
            } else {
                result.errorMessage = "Estados no encontrados, Id incorrecto";
                result.status = 400;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }

        return result;
    }
}
