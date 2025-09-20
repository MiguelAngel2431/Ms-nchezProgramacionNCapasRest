package com.digis01.MsanchezProgramacionNCapas.RestControllerRepository;

import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryUsuario;
import com.digis01.MsanchezProgramacionNCapas.DAO.UsuarioJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Rol;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import com.digis01.MsanchezProgramacionNCapas.Service.ServiceUsuario;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarioapijpa")
public class UsuarioRestControllerRepository {

    @Autowired
    private ServiceUsuario serviceUsuario;

    //Obtener a todos los usuarios
    @GetMapping()
    private ResponseEntity GetAll() {

        Result result = serviceUsuario.GetAllRespository();
        return ResponseEntity.ok(result);
        //return ResponseEntity.status(result.status).body(result);
    }
    
    //Obtener a un usuario
    @GetMapping("/{IdUsuario}")
    private ResponseEntity GetById(@PathVariable int IdUsuario) {

        Result result = serviceUsuario.GetById(IdUsuario);
        return ResponseEntity.ok(result);
        //return ResponseEntity.status(result.status).body(result);
    }
    
    //Obtener detalles de un usuario (usuario y sus direcciones)
    @GetMapping("/details/{IdUsuario}")
    private ResponseEntity GetDetail(@PathVariable int IdUsuario) {

        Result result = serviceUsuario.GetDetail(IdUsuario);
        return ResponseEntity.ok(result);
        //return ResponseEntity.status(result.status).body(result);
    }
    
    //Agregar un usuario
    @PostMapping()
    public ResponseEntity Add(@RequestBody Usuario usuario) {

        Result result = serviceUsuario.Add(usuario);
        return ResponseEntity.ok(result);
    }
    
    //Actualizar un usuario
    @PutMapping("/{IdUsuario}")
    public ResponseEntity Update(@RequestBody Usuario usuario) {

        Result result = serviceUsuario.Update(usuario);
        return ResponseEntity.ok(result);
    }
    
    //Eliminar un usuario
    @DeleteMapping("/{IdUsuario}")
    public ResponseEntity EliminarUsuario(@PathVariable int IdUsuario) {
        
        Result result = serviceUsuario.Delete(IdUsuario);
        return ResponseEntity.ok(result);
    }

}
