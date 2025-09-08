package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.ColoniaJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.DireccionJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.EstadoJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.MunicipioJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.PaisJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
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

@RestController
@RequestMapping("direccionapi")
public class DireccionRestController {

    @Autowired
    private DireccionJPADAOImplementation direccionJPADAOImplementation;

    @GetMapping("/{IdDireccion}")
    public ResponseEntity GetById(@PathVariable int IdDireccion) {

        Result result;

        result = direccionJPADAOImplementation.GetById(IdDireccion);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    //Agregar direccion
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
    @PutMapping("/{IdDireccion}")
    public ResponseEntity EditarDireccion(@RequestBody Usuario usuario, @PathVariable int IdDireccion) {

        Result result;

            usuario.Direcciones.get(0).setIdDireccion(IdDireccion);
            result = direccionJPADAOImplementation.Update(usuario);
            result.correct = true;
            return ResponseEntity.status(200).body(result);

      
    }

    //Eliminar direccion
    @DeleteMapping("/{IdDireccion}")
    public ResponseEntity EliminarDireccion(@PathVariable int IdDireccion) {

        Result result;

        result = direccionJPADAOImplementation.EliminarDireccion(IdDireccion);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

}
