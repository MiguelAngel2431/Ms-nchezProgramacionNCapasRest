
package com.digis01.MsanchezProgramacionNCapas.RestControllerRepository;

import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.Service.ServiceEstado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "RestController de Estado (JPA Repository)", description = "Controlador enfocado a métodos de Estado (dropdownList en cascada) con JPA Repository")
@RestController
@RequestMapping("estadoapijpa")
public class EstadoRestControllerRepository {
    
    @Autowired
    private ServiceEstado serviceEstado;
    
    //Obtener listas de estados en base al pais
    @Operation(summary = "Obtener todos los estados acorde al país", description = "Método que retorna todos los estados en base al ID del país")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Los estados fueron obtenidos satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID del pais no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener los estados")
    })
    @GetMapping("/Pais/{IdPais}")
    private ResponseEntity EstadoByIdPais(@Parameter(description = "ID único del país", required = true) @PathVariable int IdPais) {

        Result result = serviceEstado.GetByIdPais(IdPais);
        return ResponseEntity.ok(result);
        //return ResponseEntity.status(result.status).body(result);
    }
}
