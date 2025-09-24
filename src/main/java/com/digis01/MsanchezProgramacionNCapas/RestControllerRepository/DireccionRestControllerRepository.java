
package com.digis01.MsanchezProgramacionNCapas.RestControllerRepository;

import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryDireccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import com.digis01.MsanchezProgramacionNCapas.Service.ServiceDireccion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="RestController de Direccion (JPA Repository)", description="Controlador enfocado a métodos de direcciones (con JPA Repository)")
@RestController
@RequestMapping("direccionapijpa")
public class DireccionRestControllerRepository {
    
    @Autowired
    private ServiceDireccion serviceDireccion;
    
    //Obtener una direccion
    @Operation(summary = "Obtener una dirección", description = "Método que retorna una direccion en específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "La direccion fue obtenida satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener la direccion")
    })
    @GetMapping("/{IdDireccion}")
    public ResponseEntity GetById(@Parameter(description = "ID único de la dirección", required = true) @PathVariable int IdDireccion) {

        Result result = serviceDireccion.GetById(IdDireccion);
        return ResponseEntity.ok(result);
    }
    
    //Agregar una direccion
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
                "          \"idColonia\": 19\n" +
                "        },\n" +
                "        \"numeroExterior\": \"144\",\n" +
                "        \"calle\": \"AV nava\",\n" +
                "        \"numeroInterior\": \"133\"\n" +
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

        Result result = serviceDireccion.Add(usuario);
        return ResponseEntity.ok(result);
    }
    
    //Actualizar una direccion
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
                "        \"idDireccion\": \"289\"\n" +
                "      }\n" +
                "    ],\n" +
                "\n" +
                "    \"idUsuario\": 321\n" +
                "  \n" +
                "  }"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al actualizar la dirección")
    })
    @PutMapping("/{IdUsuario}")
    public ResponseEntity Update(@RequestBody Usuario usuario) {

        Result result = serviceDireccion.Update(usuario);
        return ResponseEntity.ok(result);
    }
    
    //Eliminar una direccion
    @Operation(summary = "Eliminar una dirección", description = "Método que elimina una direccion en base a su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "La dirección ha sido eliminada satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al eliminar la dirección")
    })
    @DeleteMapping("/{IdDireccion}")
    public ResponseEntity Delete(@Parameter(description = "ID único de la dirección", required = true) @PathVariable int IdDireccion) {
        
        Result result = serviceDireccion.Delete(IdDireccion);
        return ResponseEntity.ok(result);
    }
            
    
}
