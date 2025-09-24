
package com.digis01.MsanchezProgramacionNCapas.Service;

import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryMunicipio;
import com.digis01.MsanchezProgramacionNCapas.JPA.Municipio;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceMunicipio {
    
    @Autowired
    private IRepositoryMunicipio iRepositoryMunicipio;
    
    //Obtener a un usuario
    public Result GetByIdEstado(int IdEstado) {

        Result result = new Result();

        try {

            List<Municipio> municipiosFind = iRepositoryMunicipio.findByEstado_IdEstado(IdEstado);

            if (!municipiosFind.isEmpty()) {
                result.object = municipiosFind;
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
