package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Colonia;
import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionJPADAOImplementation implements IDireccionJPADAO {

    @Autowired
    private EntityManager entityManager;

    //Obtener las direcciones del ususario (SELECT)
    @Override
    public Result GetById(int IdDireccion) {

        Result result = new Result();

        try {

            Direccion direccionJPA = entityManager.find(Direccion.class, IdDireccion);

            if (direccionJPA != null) {
                result.object = direccionJPA;
                result.correct = true;
                result.status = 200;
            } else {
                result.errorMessage = "Direccion no encontrada, Id incorrecto";
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

    //Editar direccion (UPDATE)
    @Transactional
    @Override
    public Result Update(com.digis01.MsanchezProgramacionNCapas.JPA.Usuario usuarioJPA) {
        Result result = new Result();

        try {

            Direccion direccionJPA = new Direccion(usuarioJPA);

            if (direccionJPA != null) {
                direccionJPA.setCalle(usuarioJPA.Direcciones.get(0).getCalle());
                direccionJPA.setNumeroInterior(usuarioJPA.Direcciones.get(0).getNumeroInterior());
                direccionJPA.setNumeroExterior(usuarioJPA.Direcciones.get(0).getNumeroExterior());

                direccionJPA.Colonia = new Colonia();
                direccionJPA.Colonia.setIdColonia(usuarioJPA.Direcciones.get(0).Colonia.getIdColonia());

                entityManager.merge(direccionJPA);

                result.correct = true;
                result.object = direccionJPA;
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

    //Eliminar direccion (DELETE)
    @Transactional
    @Override
    public Result EliminarDireccion(int IdDireccion) {
        Result result = new Result();

        try {

            Direccion direccionJPA = entityManager.find(Direccion.class, IdDireccion);

            if (direccionJPA != null) {
                entityManager.remove(direccionJPA);

                result.correct = true;
                result.object = direccionJPA;
                result.status = 200;
            } else {
                result.errorMessage = "Direccion no encontrada, Id incorrecto";
                result.status = 400;
            }

        } catch (Exception ex) {
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }

        return result;
    }

    //Agregar direccion (INSERT)
    @Transactional
    @Override
    public Result Add(com.digis01.MsanchezProgramacionNCapas.JPA.Usuario usuarioJPA) {
        Result result = new Result();

        try {
            Direccion direccion = usuarioJPA.Direcciones.get(0);

            Usuario usuarioRef = entityManager.getReference(Usuario.class, usuarioJPA.getIdUsuario());
            Colonia coloniaRef = entityManager.getReference(Colonia.class, direccion.Colonia.getIdColonia());

            Direccion direccionJPA = new Direccion();
            direccionJPA.setCalle(direccion.getCalle());
            direccionJPA.setNumeroInterior(direccion.getNumeroInterior());
            direccionJPA.setNumeroExterior(direccion.getNumeroExterior());
            direccionJPA.setUsuario(usuarioRef);
            direccionJPA.setColonia(coloniaRef);
            entityManager.persist(direccionJPA);

            //Direccion direccion = new Direccion();
            //direccion.Usuario = new Usuario();
            //direccion.Usuario.setIdUsuario(direccionJPA.Usuario.getIdUsuario());
            //entityManager.persist(direccion);
            result.correct = true;

        } catch (Exception ex) {
            result.correct = true;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
}
