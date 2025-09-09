package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.RolJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.UsuarioJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="RestController de Usuario", description="Controlador enfocado a métodos del usuario")
@RestController
@RequestMapping("usuarioapi")
//@CrossOrigin(origins = "*")
public class UsuarioRestController {

    @Autowired
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;

    //UsuarioDireccionGetAll();
    @Operation(summary = "Obtener todos los usuarios", description = "Método para retornar todos los usuarios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Los usuarios fueron obtenidos satisfactoriamente"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener a los usuarios")
    })
    @GetMapping()
    public ResponseEntity GetAll() {

        Result result;

        try {

            result = usuarioJPADAOImplementation.GetAll();
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

    
    //Detalles de usuario y sus direcciones;
    @Operation(summary = "Obtener un usuario y sus direcciones (detalles de usuario)", description = "Método para retornar a un usuario y sus direcciones")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue obtenido satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener al usuario")
    })
    @GetMapping("/details/{IdUsuario}")
    public ResponseEntity GetDetail(@Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result;

        result = usuarioJPADAOImplementation.GetDetail(IdUsuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    
    //Datos del usuario;
    @Operation(summary = "Obtener a un usuario", description = "Método para retornar datos de un usuario (sin direcciones)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue obtenido satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener al usuario")
    })
    @GetMapping("/{IdUsuario}")
    public ResponseEntity GetById(@Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result;

        result = usuarioJPADAOImplementation.GetById(IdUsuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    
    //Agregar usuario
    @Operation(summary = "Agregar a un usuario", description = "Método para agregar a un usuario nuevo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue agregado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el cuerpo del Json esta mal escrito o mal declarado"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener al usuario")
    })
    @PostMapping()
    public ResponseEntity Add(@RequestBody Usuario usuario) {

        Result result;

        try {

            result = usuarioJPADAOImplementation.Add(usuario);
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

    
    //Editar usuario
    @Operation(summary = "Actualizar a un usuario", description = "Método para actualizar datos de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue actualizado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el algo en el cuerpo del JSON esta mal escrito o declarado. "
                + "Ej. cuerpo del JSON: "
                + "{\n" +
                "    \n" +
                "    \"Rol\": {\n" +
                "      \"idRol\": 2\n" +
                "    },\n" +
                "    \"password\": \"AngPerez9\",\n" +
                "    \"idUsuario\": 9,\n" +
                "    \"fechaNacimiento\": \"2012-11-04T06:00:00.000+00:00\",\n" +
                "    \"email\": \"APerez9@outlook.com\",\n" +
                "    \"sexo\": \"F \",\n" +
                "    \"apellidoPaterno\": \"Perez\",\n" +
                "    \"telefono\": \"595913549\",\n" +
                "    \"userName\": \"APerez9\",\n" +
                "    \"nombre\": \"Angelica\",\n" +
                "    \"apellidoMaterno\": \"Rodriguez\",\n" +
                "    \"imagen\": null,\n" +
                "    \"curp\": null,\n" +
                "    \"celular\": \"55123456789\",\n" +
                "    \"status\": 0\n" +
                "  }\n"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al actualizar al usuario")
    })
    @PutMapping("/{IdUsuario}")
    public ResponseEntity EditarUsuario(@RequestBody Usuario usuario, @Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result;

        usuario.setIdUsuario(IdUsuario);
        result = usuarioJPADAOImplementation.EditarUsuario(usuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    
    //Eliminar usuario
    @Operation(summary = "Eliminar a un usuario", description = "Método para eliminar a un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue eliminado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al eliminar al usuario")
    })
    @DeleteMapping("/{IdUsuario}")
    public ResponseEntity EliminarUsuario(@Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result;

        result = usuarioJPADAOImplementation.EliminarUsuario(IdUsuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    
    //Baja logica
    @Operation(summary = "Proceso de baja lógica", description = "Método para actualizar el estado (activo o inactivo) de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El estado del usuario fue cambiado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el algo en el cuerpo del JSON esta mal escrito o declarado (solo necesitas el campo 'status')."
                + "Ej. cuerpo Json: "
                + "{\n" +
                "  \"status\": 0\n" +
                "}"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al actualizar el estado del usuario")
    })
    @PutMapping("/bajaLogica/{IdUsuario}")
    public ResponseEntity BajaLogica(@RequestBody Usuario usuario, @Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result;

        try {
            //usuario.setIdUsuario(IdUsuario);
            result = usuarioJPADAOImplementation.BajaLogica(IdUsuario);
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

}
