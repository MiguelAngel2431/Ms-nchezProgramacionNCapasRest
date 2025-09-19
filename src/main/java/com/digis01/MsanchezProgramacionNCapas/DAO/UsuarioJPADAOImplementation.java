package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioJPADAOImplementation implements IUsuarioJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {

        Result result = new Result();

        try {
            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario", Usuario.class);
            result.object = queryUsuario.getResultList();

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
    
    //Busqueda abierta
    @Override
    public Result GetAll(Usuario usuario) {

        Result result = new Result();

        try {
            TypedQuery<Usuario> queryUsuario = entityManager.createQuery("FROM Usuario", Usuario.class);
            List<Usuario> usuarios = queryUsuario.getResultList();
            
            Stream<Usuario> stream = usuarios.stream();
            
            if (usuario.getNombre() != null && !usuario.getNombre().isBlank()) {
                stream = stream.filter(user -> user.getNombre() != null &&
                        user.getNombre().toLowerCase().contains(usuario.getNombre().toLowerCase()));
            }
            
            List<Usuario> filtrados = stream.collect(Collectors.toList());
            
            result.object = filtrados;
            result.correct = true;


            //result.object = queryUsuario.getResultList();

            //result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetById(int IdUsuario) {

        Result result = new Result();

        try {
            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);

            if (usuarioJPA != null) {
                result.object = usuarioJPA;
                result.correct = true;
                result.status = 200;
            } else {
                result.errorMessage = "Usuario no encontrado, Id incorrecto";
                result.status = 400;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }

        return result;
    }

    //Detalles de usuario
    @Override
    public Result GetDetail(int IdUsuario) {

        Result result = new Result();

        try {
            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);

            if (usuarioJPA != null) {
                result.object = usuarioJPA;
                result.correct = true;
                result.status = 200;
            } else {
                result.errorMessage = "Usuario no encontrado, Id incorrecto";
                result.status = 400;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }

        return result;
    }

    //Agregar a un usuario (INSERT)
    @Transactional
    @Override
    public Result Add(com.digis01.MsanchezProgramacionNCapas.JPA.Usuario usuarioJPA) {

        Result result = new Result();

        try {

            usuarioJPA.setStatus(1);

            entityManager.persist(usuarioJPA);

            Direccion direccion = new Direccion();

            direccion = usuarioJPA.Direcciones.get(0);
            direccion.Usuario = new Usuario();
            direccion.Usuario.setIdUsuario(usuarioJPA.getIdUsuario());

            entityManager.persist(direccion);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    //Editar usuario (UPDATE)
    @Transactional
    @Override
    public Result EditarUsuario(com.digis01.MsanchezProgramacionNCapas.JPA.Usuario usuarioJPA) {

        Result result = new Result();

        try {

            //Al usuario que tiene las direcciones vacias, le asignamos las direcciones que vienen directamente de la BD
            Usuario usuarioDB = entityManager.find(Usuario.class, usuarioJPA.getIdUsuario());

            if (usuarioDB != null) {
                usuarioDB.setNombre(usuarioJPA.getNombre());
                usuarioDB.setUserName(usuarioJPA.getUserName());
                usuarioDB.setApellidoPaterno(usuarioJPA.getApellidoPaterno());
                usuarioDB.setApellidoMaterno(usuarioJPA.getApellidoMaterno());
                usuarioDB.setFechaNacimiento(usuarioJPA.getFechaNacimiento());
                usuarioDB.setEmail(usuarioJPA.getEmail());
                usuarioDB.setPassword(usuarioJPA.getPassword());
                usuarioDB.setSexo(usuarioJPA.getSexo());
                usuarioDB.setTelefono(usuarioJPA.getTelefono());
                usuarioDB.setCelular(usuarioJPA.getCelular());
                usuarioDB.setCurp(usuarioJPA.getCurp());
                usuarioDB.setImagen(usuarioJPA.getImagen());
                usuarioDB.setStatus(usuarioJPA.getStatus());

                usuarioDB.Rol = new Rol();
                usuarioDB.Rol.setIdRol(usuarioJPA.Rol.getIdRol());

                entityManager.merge(usuarioDB);
                result.correct = true;
                result.object = usuarioDB;

                result.status = 200;

            } else {
                result.errorMessage = "Usuario no encontrado, Id incorrecto";
                result.status = 400;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }

        return result;
    }

    //Eliminar usuario (DELETE)
    @Transactional
    @Override
    public Result EliminarUsuario(int IdUsuario) {

        Result result = new Result();

        try {

            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);

            if (usuarioJPA != null) {
                entityManager.remove(usuarioJPA);

                result.correct = true;
                result.object = usuarioJPA;
                result.status = 200;
            } else {
                result.errorMessage = "Usuario no encontrado, Id incorrecto";
                result.status = 400;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }

        return result;
    }

    // Logica para cambiar el status
    @Transactional
    @Override
    public Result BajaLogica(int IdUsuario) {
        Result result = new Result();

        try {

            Usuario usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
            //usuarioJPA.setStatus(usuarioJPA.getStatus() == 1 ? usuarioJPA.setStatus(0) : usuarioJPA.setStatus(1));

            usuarioJPA.setStatus(usuarioJPA.getStatus() == 1 ? 0 : 1);

            entityManager.merge(usuarioJPA);

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
}
