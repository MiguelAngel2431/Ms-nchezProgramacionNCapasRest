package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.EstadoJPADAOImplementation;
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

@Tag(name = "RestController de Estado", description = "Controlador enfocado a métodos de Estado (dropdownList en cascada)")
@RestController
@RequestMapping("estadoapi")
//@CrossOrigin(origins = "*")
public class EstadoRestController {

    @Autowired
    private EstadoJPADAOImplementation estadoJPADAOImplementation;

    //Lista de estados x idPais
    @Operation(summary = "Obtener todos los estados acorde al país", description = "Método que retorna todos los estados en base al ID del país")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Los estados fueron obtenidos satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID del pais no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener los estados")
    })
    @GetMapping("/Pais/{IdPais}")
    public ResponseEntity EstadoByIdPais(@Parameter(description = "ID único del país", required = true) @PathVariable int IdPais) {

        Result result;

        result = estadoJPADAOImplementation.EstadoByIdPais(IdPais);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

}
