
package com.digis01.MsanchezProgramacionNCapas.RestControllerRepository;

import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryDireccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import com.digis01.MsanchezProgramacionNCapas.Service.ServiceDireccion;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("direccionapijpa")
public class DireccionRestControllerRepository {
    
    @Autowired
    private ServiceDireccion serviceDireccion;
    
    //Obtener una direccion
    @GetMapping("/{IdDireccion}")
    public ResponseEntity GetById(@PathVariable int IdDireccion) {

        Result result = serviceDireccion.GetById(IdDireccion);
        return ResponseEntity.ok(result);
    }
    
    //Agregar una direccion
    @PostMapping()
    public ResponseEntity Add(@RequestBody Usuario usuario) {

        Result result = serviceDireccion.Add(usuario);
        return ResponseEntity.ok(result);
    }
    
    //Actualizar una direccion
    @PutMapping("/{IdUsuario}")
    public ResponseEntity Update(@RequestBody Usuario usuario) {

        Result result = serviceDireccion.Update(usuario);
        return ResponseEntity.ok(result);
    }
            
    
}
