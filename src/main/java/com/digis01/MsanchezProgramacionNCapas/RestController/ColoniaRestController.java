package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.ColoniaJPADAOImplementation;
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

@Tag(name = "RestController de Colonia", description = "Controlador enfocado a métodos de Colonia (dropdownList en cascada)")
@RestController
@RequestMapping("coloniaapi")
//@CrossOrigin(origins = "*")
public class ColoniaRestController {

    @Autowired
    private ColoniaJPADAOImplementation coloniaJPADAOImplementation;

    //Lista de Colonias x idMunicipio
    @Operation(summary = "Obtener todas las colonias acorde al municipio", description = "Método que retorna todas las colonias en base al ID del municipio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Las colonias fueron obtenidas satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID del municipio no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener las colonias")
    })
    @GetMapping("/Municipio/{IdMunicipio}")
    public ResponseEntity ColoniaByIdMunicipio(@Parameter(description = "ID único del municipio", required = true) @PathVariable int IdMunicipio) {

        Result result;

        result = coloniaJPADAOImplementation.ColoniaByIdMunicipio(IdMunicipio);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

}
