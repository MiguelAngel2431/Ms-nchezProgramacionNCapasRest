
package com.digis01.MsanchezProgramacionNCapas.Service;

import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryEstado;
import com.digis01.MsanchezProgramacionNCapas.JPA.Estado;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceEstado {
    
    @Autowired
    private IRepositoryEstado iRepositoryEstado;
    
    //Obtener a un usuario
    public Result GetByIdPais(int IdPais) {

        Result result = new Result();

        try {

            List<Estado> estadosFind = iRepositoryEstado.findByPais_IdPais(IdPais);

            if (!estadosFind.isEmpty()) {
                result.object = estadosFind;
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
