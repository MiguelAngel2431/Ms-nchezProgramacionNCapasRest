package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Municipio;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioJPADAOImplementation implements IMunicipioJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result MunicipioByIdEstado(int IdEstado) {
        Result result = new Result();

        try {
            Municipio municipioJPA = entityManager.find(Municipio.class, IdEstado);

            if (municipioJPA != null) {
                
                TypedQuery<Municipio> queryMunicipios = entityManager.createQuery("FROM Municipio where Estado.IdEstado = :IdEstado ", Municipio.class);
                queryMunicipios.setParameter("IdEstado", IdEstado);

                result.object = queryMunicipios.getResultList();
                result.correct = true;
                result.status = 200;

            } else {
                result.errorMessage = "Municipios no encontrados, Id incorrecto";
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
