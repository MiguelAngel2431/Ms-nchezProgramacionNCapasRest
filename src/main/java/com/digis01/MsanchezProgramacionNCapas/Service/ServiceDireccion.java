package com.digis01.MsanchezProgramacionNCapas.Service;

import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryDireccion;
import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryUsuario;
import com.digis01.MsanchezProgramacionNCapas.JPA.Colonia;
import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Rol;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ServiceDireccion {

    @Autowired
    private IRepositoryDireccion iRepositoryDireccion;
    @Autowired
    private IRepositoryUsuario iRepositoryUsuario;

    //Obtener una direccion
    public Result GetById(int IdDireccion) {

        Result result = new Result();

        try {

            Optional<Direccion> direccion = iRepositoryDireccion.findById(IdDireccion);

            if (direccion.isPresent()) {
                result.object = direccion;
                result.correct = true;
            }

        } catch (Exception ex) {
            result = new Result();
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;

        }

        return result;

    }

    //Agregar a un usuario
    public Result Add(@RequestBody Usuario usuario) {

        Result result = new Result();

        try {
            Optional<Usuario> usuarioOpt = iRepositoryUsuario.findById(usuario.getIdUsuario());

            if (usuarioOpt.isPresent()) {

                Direccion direccion = new Direccion();
                direccion.setCalle(usuario.Direcciones.get(0).getCalle());
                direccion.setNumeroInterior(usuario.Direcciones.get(0).getNumeroInterior());
                direccion.setNumeroExterior(usuario.Direcciones.get(0).getNumeroExterior());
                
                direccion.Colonia = new Colonia();
                direccion.Colonia.setIdColonia(usuario.Direcciones.get(0).Colonia.getIdColonia());
                
                direccion.Usuario = new Usuario();
                direccion.Usuario.setIdUsuario(usuario.getIdUsuario());
                
                Direccion savedDirection = iRepositoryDireccion.save(direccion);

                result.object = savedDirection;
                result.correct = true;
            }

        } catch (Exception ex) {
            result = new Result();
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;

        }

        return result;
    }
    
    //Actualizar a un usuario
    public Result Update(@RequestBody Usuario usuario) {

        Result result = new Result();

        try {
            
            Direccion dir = new Direccion();
            dir.setIdDireccion(usuario.Direcciones.get(0).getIdDireccion());
            Optional<Direccion> direccionFind = iRepositoryDireccion.findById(dir.getIdDireccion());

            if (direccionFind.isPresent()) {
                
                Direccion direccion = new Direccion();
                
                direccion.setCalle(usuario.Direcciones.get(0).getCalle());
                direccion.setNumeroInterior(usuario.Direcciones.get(0).getNumeroInterior());
                direccion.setNumeroExterior(usuario.Direcciones.get(0).getNumeroExterior());
                
                direccion.Colonia = new Colonia();
                direccion.Colonia.setIdColonia(usuario.Direcciones.get(0).Colonia.getIdColonia());
                
                direccion.Usuario = new Usuario();
                direccion.Usuario.setIdUsuario(usuario.getIdUsuario());
                
                Direccion savedDirection = iRepositoryDireccion.save(direccion);

                result.object = savedDirection;
                result.correct = true;
                
            }

        } catch (Exception ex) {
            result = new Result();
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;

        }

        return result;

    }
}
