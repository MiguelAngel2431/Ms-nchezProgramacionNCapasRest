package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.RolJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.UsuarioJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
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

@RestController
@RequestMapping("usuarioapi")
//@CrossOrigin(origins = "*")
public class UsuarioRestController {

    @Autowired
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;

    //UsuarioDireccionGetAll();
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
    @GetMapping("/details/{IdUsuario}")
    public ResponseEntity GetDetail(@PathVariable int IdUsuario) {

        Result result;

        result = usuarioJPADAOImplementation.GetDetail(IdUsuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    //Detalles de usuario;
    @GetMapping("/{IdUsuario}")
    public ResponseEntity GetById(@PathVariable int IdUsuario) {

        Result result;

        result = usuarioJPADAOImplementation.GetById(IdUsuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    //Agregar usuario
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
    @PutMapping("/{IdUsuario}")
    public ResponseEntity EditarUsuario(@RequestBody Usuario usuario, @PathVariable int IdUsuario) {

        Result result;

        usuario.setIdUsuario(IdUsuario);
        result = usuarioJPADAOImplementation.EditarUsuario(usuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    //Eliminar usuario
    @DeleteMapping("/{IdUsuario}")
    public ResponseEntity EliminarUsuario(@PathVariable int IdUsuario) {

        Result result;

        result = usuarioJPADAOImplementation.EliminarUsuario(IdUsuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    //Baja logica
    @PutMapping("/bajaLogica/{IdUsuario}")
    public ResponseEntity BajaLogica(@RequestBody Usuario usuario, @PathVariable int IdUsuario) {

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
