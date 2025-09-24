package com.digis01.MsanchezProgramacionNCapas.Service;

import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryDireccion;
import com.digis01.MsanchezProgramacionNCapas.DAO.IRepositoryUsuario;
import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Rol;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ServiceUsuario {

    @Autowired
    private IRepositoryUsuario iRepositoryUsuario;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IRepositoryDireccion iRespositoryDireccion;

    //Obtener todos los usuarios
//    public Result GetAllRespository() {
//
//        Result result = new Result();
//
//        try {
//
//            //Optional<Usuario> usuarioRepository = iRepositoryUsuario.findAll();
//            result.object = iRepositoryUsuario.findAll();
//            result.correct = true;
//
//        } catch (Exception ex) {
//            result = new Result();
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//
//        }
//
//        return result;
//    }
    public Result GetAllRespository(Usuario filtro) {

        Result result = new Result();

        try {

            // Obtener todos los usuarios sin filtro
            List<Usuario> usuarios = iRepositoryUsuario.findAll();

            // Filtrar con Streams según campos no nulos/no vacíos
            List<Usuario> usuariosFiltrados = usuarios.stream()
                    .filter(u -> filtro.getNombre() == null || filtro.getNombre().isEmpty()
                        || u.getNombre().toLowerCase().contains(filtro.getNombre().toLowerCase()))
                    .filter(u -> filtro.getApellidoPaterno() == null || filtro.getApellidoPaterno().isEmpty()
                        || u.getApellidoPaterno().toLowerCase().contains(filtro.getApellidoPaterno().toLowerCase()))
                    .filter(u -> filtro.getApellidoMaterno() == null || filtro.getApellidoMaterno().isEmpty()
                        || u.getApellidoMaterno().toLowerCase().contains(filtro.getApellidoMaterno().toLowerCase()))
                    
                    .collect(Collectors.toList());

            result.object = usuariosFiltrados;
            result.correct = true;

        } catch (Exception ex) {
            result = new Result();
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;

        }

        return result;
    }

    //Obtener a un usuario
    public Result GetById(int IdUsuario) {

        Result result = new Result();

        try {

            Optional<Usuario> usuario = iRepositoryUsuario.findById(IdUsuario);

            if (usuario.isPresent()) {
                result.object = usuario;
                result.correct = true;
            }

            //result.object = iRepositoryUsuario.findById(IdUsuario);
            //result.correct = true;
            //return ResponseEntity.status(result.status).body(result);
            //return ResponseEntity.status(200).body(result);
        } catch (Exception ex) {
            result = new Result();
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            //return ResponseEntity.status(500).body(result);
        }

        return result;
    }

    //Obtener a un usuario (y sus direcciones)
    public Result GetDetail(int IdUsuario) {

        Result result = new Result();

        try {

            Optional<Usuario> usuario = iRepositoryUsuario.findById(IdUsuario);

            if (usuario.isPresent()) {
                result.object = usuario;
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
            usuario.setStatus(1);
            usuario.Direcciones.get(0).Usuario = usuario;
            Usuario savedUser = iRepositoryUsuario.save(usuario);

            result.object = savedUser;
            result.correct = true;

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
            Optional<Usuario> usuarioFind = iRepositoryUsuario.findById(usuario.getIdUsuario());

            if (usuarioFind.isPresent()) {

                Usuario usuarioExistente = usuarioFind.get();

                usuarioExistente.setNombre(usuario.getNombre());
                usuarioExistente.setApellidoPaterno(usuario.getApellidoPaterno());
                usuarioExistente.setApellidoMaterno(usuario.getApellidoMaterno());
                usuarioExistente.setCurp(usuario.getCurp());
                usuarioExistente.setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioExistente.setUserName(usuario.getUserName());
                usuarioExistente.setEmail(usuario.getEmail());
                usuarioExistente.setPassword(usuario.getPassword());
                usuarioExistente.setTelefono(usuario.getTelefono());
                usuarioExistente.setCelular(usuario.getCelular());
                usuarioExistente.setSexo(usuario.getSexo());

                usuarioExistente.Rol = new Rol();
                usuarioExistente.Rol.setIdRol(usuario.Rol.getIdRol());

                Usuario updatedUser = iRepositoryUsuario.save(usuarioExistente);
                result.object = updatedUser;
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

    //Eliminar a un usuario
    public Result Delete(int IdUsuario) {
        Result result = new Result();

        try {
            Optional<Usuario> usuarioFind = iRepositoryUsuario.findById(IdUsuario);

            if (usuarioFind.isPresent()) {
                iRepositoryUsuario.deleteById(IdUsuario);
                result.correct = true;
            }

            result.correct = true;

        } catch (Exception ex) {
            result = new Result();
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;

        }

        return result;
    }

    //Baja lógica
    @Transactional
    public Result BajaLogica(int IdUsuario) {

        Result result = new Result();

        try {

            Optional<Usuario> usuarioFind = iRepositoryUsuario.findById(IdUsuario);

            if (usuarioFind.isPresent()) {

                Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
                //usuarioJPA.setStatus(usuarioJPA.getStatus() == 1 ? usuarioJPA.setStatus(0) : usuarioJPA.setStatus(1));

                usuarioJPA.setStatus(usuarioJPA.getStatus() == 1 ? 0 : 1);

                entityManager.merge(usuarioJPA);
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
