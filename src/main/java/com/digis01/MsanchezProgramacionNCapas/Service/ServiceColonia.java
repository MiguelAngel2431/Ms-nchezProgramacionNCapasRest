
package com.digis01.MsanchezProgramacionNCapas.Service;

import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryColonia;
import com.digis01.MsanchezProgramacionNCapas.JPA.Colonia;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceColonia {
    
    @Autowired
    private IRepositoryColonia iRepositoryColonia;
    
    //Obtener las colonias en base al municipio
    public Result GetByIdMunicipio(int IdMunicipio) {

        Result result = new Result();

        try {

            List<Colonia> coloniasFind = iRepositoryColonia.findByMunicipio_IdMunicipio(IdMunicipio);

            if (!coloniasFind.isEmpty()) {
                result.object = coloniasFind;
                result.correct = true;
            } else {
                result.correct = false;
            }

        } catch (Exception ex) {
            result = new Result();
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
         
        }

        return result;
    }
}
