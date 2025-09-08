
package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.EstadoJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estadoapi")
//@CrossOrigin(origins = "*")
public class EstadoRestController {
    
    @Autowired
    private EstadoJPADAOImplementation estadoJPADAOImplementation;
    
    //Lista de estados x idPais
    @GetMapping("/Pais/{IdPais}")
    public ResponseEntity EstadoByIdPais(@PathVariable int IdPais) {
        
        Result result;
        
        try {
            
            result = estadoJPADAOImplementation.EstadoByIdPais(IdPais);
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
