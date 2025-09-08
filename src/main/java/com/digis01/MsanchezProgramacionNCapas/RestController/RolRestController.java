
package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.RolJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rolapi")
public class RolRestController {
    
    @Autowired
    private RolJPADAOImplementation rolJPADAOImplementation;
    
    //Lista de roles
    @GetMapping
    public ResponseEntity RolGetAll() {
        
        Result result;
        
        try {
            
            result = rolJPADAOImplementation.GetAll();
            result.correct = true;
            return ResponseEntity.status(200).body(result);
            
        } catch (Exception ex) {
            result = new Result();
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.status(500).body(result);
        }
    }
    
}
