package com.digis01.MsanchezProgramacionNCapas.RestControllerRepository;

import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryUsuario;
import com.digis01.MsanchezProgramacionNCapas.DAO.UsuarioJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Rol;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import com.digis01.MsanchezProgramacionNCapas.Service.ServiceUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "RestController de Usuario (JPA Repository)", description = "Controlador enfocado a métodos del usuario (con JPA Repository)")
@RestController
@RequestMapping("usuarioapijpa")
public class UsuarioRestControllerRepository {

    @Autowired
    private ServiceUsuario serviceUsuario;

    //Obtener a todos los usuarios
//    @GetMapping()
//    private ResponseEntity GetAll() {
//
//        Result result = serviceUsuario.GetAllRespository();
//        return ResponseEntity.ok(result);
//        //return ResponseEntity.status(result.status).body(result);
//    }
    
    //Obtener a todos los usuarios
    @Operation(summary = "Obtener todos los usuarios", description = "Método para retornar todos los usuarios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Los usuarios fueron obtenidos satisfactoriamente"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener a los usuarios")
    })
    @GetMapping()
    private ResponseEntity GetAll(@ModelAttribute Usuario usuarioFiltro) {

        Result result = serviceUsuario.GetAllRespository(usuarioFiltro);
        return ResponseEntity.ok(result);
        //return ResponseEntity.status(result.status).body(result);
    }
    
    //Obtener a un usuario
    @Operation(summary = "Obtener a un usuario", description = "Método para retornar datos de un usuario (sin direcciones)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue obtenido satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener al usuario")
    })
    @GetMapping("/{IdUsuario}")
    private ResponseEntity GetById(@Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result = serviceUsuario.GetById(IdUsuario);
        return ResponseEntity.ok(result);
        //return ResponseEntity.status(result.status).body(result);
    }
    
    //Obtener detalles de un usuario (usuario y sus direcciones)
    @Operation(summary = "Obtener un usuario y sus direcciones (detalles de usuario)", description = "Método para retornar a un usuario y sus direcciones")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue obtenido satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener al usuario")
    })
    @GetMapping("/details/{IdUsuario}")
    private ResponseEntity GetDetail(@Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result = serviceUsuario.GetDetail(IdUsuario);
        return ResponseEntity.ok(result);
        //return ResponseEntity.status(result.status).body(result);
    }
    
    //Agregar un usuario
    @Operation(summary = "Agregar a un usuario", description = "Método para agregar a un usuario nuevo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue agregado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el algo en el cuerpo del JSON esta mal escrito o declarado. "
                + "Ej. de cuerpo del Json: "
                + "{\n"
                + "\n"
                + "  \"Rol\": {\n"
                + "\n"
                + "    \"idRol\": 3\n"
                + "\n"
                + "  },\n"
                + "\n"
                + "  \"Direcciones\": [\n"
                + "\n"
                + "    {\n"
                + "      \n"
                + "      \"Colonia\": {\n"
                + "\n"
                + "        \"idColonia\": 1\n"
                + "        \n"
                + "      },\n"
                + "\n"
                + "      \"numeroInterior\": \"1\",\n"
                + "      \"numeroExterior\": \"2\",\n"
                + "      \"calle\": \"AV GREEN LEAF\"\n"
                + "      \n"
                + "    }\n"
                + "\n"
                + "  ],\n"
                + "\n"
                + "  \"telefono\": \"551234\",\n"
                + "\n"
                + "  \"apellidoPaterno\": \"Lopez\",\n"
                + "\n"
                + "  \"nombre\": \"Carlos\",\n"
                + "\n"
                + "  \"apellidoMaterno\": \"Lopez\",\n"
                + "\n"
                + "  \"fechaNacimiento\": \"2025-09-09T16:08:29.814Z\",\n"
                + "\n"
                + "  \"email\": \"clopez@gmaail.com\",\n"
                + "\n"
                + "  \"userName\": \"clopez34\",\n"
                + "\n"
                + "  \"sexo\": \"M \",\n"
                + "\n"
                + "  \"celular\": \"12345\",\n"
                + "\n"
                + "  \"curp\": \"dfdft45\",\n"
                + "\n"
                + "  \"imagen\": null,\n"
                + "\n"
                + "  \"password\": \"123\",\n"
                + "\n"
                + "  \"status\": 1\n"
                + "\n"
                + "}"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener al usuario")
    })
    @PostMapping()
    public ResponseEntity Add(@RequestBody Usuario usuario) {

        Result result = serviceUsuario.Add(usuario);
        return ResponseEntity.ok(result);
    }
    
    //Actualizar un usuario
    @Operation(summary = "Actualizar a un usuario", description = "Método para actualizar datos de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue actualizado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el algo en el cuerpo del JSON esta mal escrito o declarado. "
                + "Ej. cuerpo del JSON: "
                + "{\n"
                + "    \n"
                + "    \"Rol\": {\n"
                + "      \"idRol\": 2\n"
                + "    },\n"
                + "    \"password\": \"AngPerez9\",\n"
                + "    \"idUsuario\": 9,\n"
                + "    \"fechaNacimiento\": \"2012-11-04T06:00:00.000+00:00\",\n"
                + "    \"email\": \"APerez9@outlook.com\",\n"
                + "    \"sexo\": \"F \",\n"
                + "    \"apellidoPaterno\": \"Perez\",\n"
                + "    \"telefono\": \"595913549\",\n"
                + "    \"userName\": \"APerez9\",\n"
                + "    \"nombre\": \"Angelica\",\n"
                + "    \"apellidoMaterno\": \"Rodriguez\",\n"
                + "    \"imagen\": null,\n"
                + "    \"curp\": null,\n"
                + "    \"celular\": \"55123456789\",\n"
                + "    \"status\": 0\n"
                + "  }\n"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al actualizar al usuario")
    })
    @PutMapping("/{IdUsuario}")
    public ResponseEntity Update(@RequestBody Usuario usuario) {

        Result result = serviceUsuario.Update(usuario);
        return ResponseEntity.ok(result);
    }
    
    //Eliminar un usuario
    @Operation(summary = "Eliminar a un usuario", description = "Método para eliminar a un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue eliminado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al eliminar al usuario")
    })
    @DeleteMapping("/{IdUsuario}")
    public ResponseEntity EliminarUsuario(@PathVariable int IdUsuario) {
        
        Result result = serviceUsuario.Delete(IdUsuario);
        return ResponseEntity.ok(result);
    }
    
    //Proceso de baja logica
    @Operation(summary = "Proceso de baja lógica", description = "Método para actualizar el estado (activo o inactivo) de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El estado del usuario fue cambiado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el algo en el cuerpo del JSON esta mal escrito o declarado (solo necesitas el campo 'status')."
                + "Ej. cuerpo Json: "
                + "{\n"
                + "  \"status\": 0\n"
                + "}"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al actualizar el estado del usuario")
    })
    @PutMapping("/bajaLogica/{IdUsuario}")
    public ResponseEntity BajaLogica(@RequestBody Usuario usuario, @PathVariable int IdUsuario) {
        
        Result result = serviceUsuario.BajaLogica(IdUsuario);
        return ResponseEntity.ok(result);
    }
}
