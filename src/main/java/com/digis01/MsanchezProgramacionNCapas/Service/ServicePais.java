
package com.digis01.MsanchezProgramacionNCapas.Service;

import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryPais;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicePais {
    
    @Autowired
    private IRepositoryPais iRepositoryPais;
    
    public Result GetAll() {
        
        Result result = new Result();
        
        try {
            
            result.object = iRepositoryPais.findAll();
            result.correct = true;
            
        } catch (Exception ex) {
            result = new Result();
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
}
