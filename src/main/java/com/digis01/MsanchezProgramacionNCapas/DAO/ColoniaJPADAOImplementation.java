package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Colonia;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaJPADAOImplementation implements IColoniaJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result ColoniaByIdMunicipio(int IdMunicipio) {
        Result result = new Result();

        try {

            Colonia coloniaJPA = entityManager.find(Colonia.class, IdMunicipio);

            if (coloniaJPA != null) {
                
                TypedQuery<Colonia> queryColonias = entityManager.createQuery("FROM Colonia where Municipio.IdMunicipio = :IdMunicipio ", Colonia.class);
                queryColonias.setParameter("IdMunicipio", IdMunicipio);

                result.object = queryColonias.getResultList();
                result.correct = true;
                result.status = 200;
                
            } else {
                result.errorMessage = "Colonias no encontradas, Id incorrecto";
                result.status = 400;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 400;
        }

        return result;
    }
}
