
package com.digis01.MsanchezProgramacionNCapas.RestControllerRepository;

import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.Service.ServiceMunicipio;
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

@Tag(name = "RestController de Municipio (JPA Repository)", description = "Controlador enfocado a métodos de Municipio (dropdownList en cascada) con JPA Repository")
@RestController
@RequestMapping("municipioapijpa")
public class MunicipioRestControllerRepository {
    
    @Autowired
    private ServiceMunicipio serviceMunicipio;
    
    //Obtener todos los municipios en base al estado
    @Operation(summary = "Obtener todos los municipios acorde al estado", description = "Método que retorna todos los municipios en base al ID del estado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Los municipios fueron obtenidos satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID del estado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener los municipios")
    })
    @GetMapping("/Estado/{IdEstado}")
    private ResponseEntity MunicipioByIdEstado(@Parameter(description = "ID único del estado", required = true) @PathVariable int IdEstado) {

        Result result = serviceMunicipio.GetByIdEstado(IdEstado);
        return ResponseEntity.ok(result);
        //return ResponseEntity.status(result.status).body(result);
    }
}
