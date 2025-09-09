
package com.digis01.MsanchezProgramacionNCapas.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Schema(description = "Representa un rol que desempeña el usuario en el sistema")
@Entity
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol")
    private int IdRol;
    
    
    @Column(name = "nombre")
    @Schema(description = "Nombre del rol", example = "Administrador", required = true)
    private String Nombre;
    
    //Constructores
    public Rol(){}
    
    public Rol(com.digis01.MsanchezProgramacionNCapas.JPA.Rol rolML) {
        this.IdRol = rolML.getIdRol();
        this.Nombre = rolML.getNombre();
        
    }
    
    public Rol(int idRol, String nombre) {
        this.IdRol = idRol;
        this.Nombre = nombre;
    }
    
    //Getter y setter de idRol
    public void setIdRol(int idRol) {
        this.IdRol = idRol;
    }
    
    public int getIdRol() {
        return this.IdRol;
    }
    
    //Getter y setter de Nombre
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    
    public String getNombre() {
        return this.Nombre;
    }
    
}
