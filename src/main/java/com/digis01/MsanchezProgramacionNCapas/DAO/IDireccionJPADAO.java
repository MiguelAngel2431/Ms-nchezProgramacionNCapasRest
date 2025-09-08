
package com.digis01.MsanchezProgramacionNCapas.DAO;

import com.digis01.MsanchezProgramacionNCapas.JPA.Result;

public interface IDireccionJPADAO {
    Result GetById(int IdDireccion);
    Result Update(com.digis01.MsanchezProgramacionNCapas.JPA.Usuario usuarioJPA);
    Result Add(com.digis01.MsanchezProgramacionNCapas.JPA.Usuario usuario);
    Result EliminarDireccion(int IdDireccion);
}
