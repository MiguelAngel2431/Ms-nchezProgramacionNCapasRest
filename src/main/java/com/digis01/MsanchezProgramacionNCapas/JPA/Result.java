
package com.digis01.MsanchezProgramacionNCapas.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class Result {
    public boolean correct;
    public String errorMessage;
    public Exception ex;
    public Object object;
    public List<Object> objects;
    
    public List<ErrorCM> listaErrores;

    // (Opcional) Getters y Setters si los necesitas para serialización/deserialización
    public List<ErrorCM> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<ErrorCM> listaErrores) {
        this.listaErrores = listaErrores;
    }
    
    @JsonIgnore
    public int status;
}
