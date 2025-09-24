
package com.digis01.MsanchezProgramacionNCapas.RestControllerRepository;

import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.Service.ServiceRol;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="RestController de Rol (JPA Repository)", description="Controlador enfocado a métodos de Rol (dropdownList) con JPA Repository")
@RestController
@RequestMapping("rolapijpa")
public class RolRestControllerRepositoy {
    
    @Autowired
    private ServiceRol serviceRol;
    
    //Obtener a todos los roles
    @Operation(summary = "Obtener todos los roles", description = "Método que retorna todos los roles")
    @GetMapping()
    private ResponseEntity GetAll() {

        Result result = serviceRol.GetAll();
        return ResponseEntity.ok(result);
        //return ResponseEntity.status(result.status).body(result);
    }
    
}
