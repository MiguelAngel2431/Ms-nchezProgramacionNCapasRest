package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.ColoniaJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.DireccionJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.EstadoJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.MunicipioJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.PaisJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="RestController de Direccion", description="Controlador enfocado a métodos de direcciones")
@RestController
@RequestMapping("direccionapi")
public class DireccionRestController {

    @Autowired
    private DireccionJPADAOImplementation direccionJPADAOImplementation;
    
    //Mostrar datos de direccion
    @Operation(summary = "Obtener una dirección", description = "Método que retorna una direccion en específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "La direccion fue obtenida satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener la direccion")
    })
    @GetMapping("/{IdDireccion}")
    public ResponseEntity GetById(@Parameter(description = "ID único de la dirección", required = true) @PathVariable int IdDireccion) {

        Result result;

        result = direccionJPADAOImplementation.GetById(IdDireccion);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    
    //Agregar direccion
    @Operation(summary = "Agregar una dirección", description = "Método que agrega una nueva dirección")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "La direccion fue agregada satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el algo en el cuerpo del JSON esta mal escrito o declarado. "
                + "Ej. de cuerpo del Json: "
                + "{\n" +
                "    \n" +
                "    \"Direcciones\": [\n" +
                "      {\n" +
                "        \"Colonia\": {\n" +
                "          \"idColonia\": 19,\n" +
                "        },\n" +
                "        \"numeroExterior\": \"144\",\n" +
                "        \"calle\": \"AV nava\",\n" +
                "        \"numeroInterior\": \"133\",\n" +
                "        \"idDireccion\": 210\n" +
                "      }\n" +
                "    ],\n" +
                "\n" +
                "    \"idUsuario\": 203\n" +
                "\n" +
                "  }"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener la direccion")
    })
    @PostMapping()
    public ResponseEntity Add(@RequestBody Usuario usuario) {

        Result result;

        try {
            usuario.getIdUsuario();

            result = direccionJPADAOImplementation.Add(usuario);
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

    
    //Editar direccion
    @Operation(summary = "Actualizar una dirección", description = "Método que actualiza datos de una dirección en base a su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "La dirección fué actualizada satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el algo en el cuerpo del JSON esta mal escrito o declarado. "
                + "Ej. de cuerpo del Json: "
                + "{\n" +
                "    \n" +
                "    \"Direcciones\": [\n" +
                "      {\n" +
                "        \"Colonia\": {\n" +
                "          \n" +
                "          \"idColonia\": 19\n" +
                "        \n" +
                "        },\n" +
                "        \"numeroExterior\": \"144\",\n" +
                "        \"calle\": \"calle status VALI ACT\",\n" +
                "        \"numeroInterior\": \"133\"\n" +
                "      }\n" +
                "    ],\n" +
                "\n" +
                "    \"idUsuario\": 321\n" +
                "  \n" +
                "  }"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al actualizar la dirección")
    })
    @PutMapping("/{IdDireccion}")
    public ResponseEntity EditarDireccion(@RequestBody Usuario usuario, @Parameter(description = "ID único de la dirección", required = true) @PathVariable int IdDireccion) {

        Result result;

            usuario.Direcciones.get(0).setIdDireccion(IdDireccion);
            result = direccionJPADAOImplementation.Update(usuario);
            result.correct = true;
            return ResponseEntity.status(200).body(result);

      
    }

    
    //Eliminar direccion
    @Operation(summary = "Eliminar una dirección", description = "Método que elimina una direccion en base a su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "La dirección ha sido eliminada satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al eliminar la dirección")
    })
    @DeleteMapping("/{IdDireccion}")
    public ResponseEntity EliminarDireccion(@Parameter(description = "ID único de la dirección", required = true) @PathVariable int IdDireccion) {

        Result result;

        result = direccionJPADAOImplementation.EliminarDireccion(IdDireccion);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

}
