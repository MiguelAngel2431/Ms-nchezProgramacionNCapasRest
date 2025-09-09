package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.MunicipioJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "RestController de Municipio", description = "Controlador enfocado a métodos de Municipio (dropdownList en cascada)")
@RestController
@RequestMapping("municipioapi")
//@CrossOrigin(origins = "*")
public class MunicipioRestController {

    @Autowired
    private MunicipioJPADAOImplementation municipioJPADAOImplementation;

    //Lista de Municipios x idEstado
    @Operation(summary = "Obtener todos los municipios acorde al estado", description = "Método que retorna todos los municipios en base al ID del estado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Los municipios fueron obtenidos satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID del estado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener los municipios")
    })
    @GetMapping("/Estado/{IdEstado}")
    public ResponseEntity MunicipioByIdEstado(@Parameter(description = "ID único del estado", required = true) @PathVariable int IdEstado) {

        Result result;

        result = municipioJPADAOImplementation.MunicipioByIdEstado(IdEstado);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }
}
