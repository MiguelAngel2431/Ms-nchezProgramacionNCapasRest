
package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.ColoniaJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coloniaapi")
//@CrossOrigin(origins = "*")
public class ColoniaRestController {
    
    @Autowired
    private ColoniaJPADAOImplementation coloniaJPADAOImplementation;
    
    //Lista de Colonias x idMunicipio
    @GetMapping("/Municipio/{IdMunicipio}")
    public ResponseEntity ColoniaByIdMunicipio(@PathVariable int IdMunicipio) {
        
        Result result;
        
        try {
            
            result = coloniaJPADAOImplementation.ColoniaByIdMunicipio(IdMunicipio);
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
