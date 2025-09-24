
package com.digis01.MsanchezProgramacionNCapas.RestControllerRepository;

import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.Service.ServicePais;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="RestController de País (JPA Repository)", description="Controlador enfocado a métodos de País (dropdownList) con JPA Repository")
@RestController
@RequestMapping("paisapijpa")
public class PaisRestControllerRepository {
    
    @Autowired
   private ServicePais servicePais;
    
   //Obtener a todos los paises
    @Operation(summary = "Obtener todos los paises", description = "Método que retorna todos los países")
    @GetMapping()
    private ResponseEntity GetAll() {

        Result result = servicePais.GetAll();
        return ResponseEntity.ok(result);
        //return ResponseEntity.status(result.status).body(result);
    } 
    
}
