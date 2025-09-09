
package com.digis01.MsanchezProgramacionNCapas.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Schema(description = "Representa un pais en el sistema")
@Entity
public class Pais {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpais")
    private int IdPais;
    
    @Column(name = "nombre")
    private String Nombre;
    
    //Constructores
    public Pais() {}
    
    public Pais(com.digis01.MsanchezProgramacionNCapas.JPA.Pais paisML) {
        this.IdPais = paisML.getIdPais();
        this.Nombre = paisML.getNombre();
        
    }
    
    /*public Pais(int idPais, String nombre) {
        this.IdPais = idPais;
        this.Nombre = nombre;
    }*/
    
    //Getter y Setter de idPais
    public void setIdPais(int idPais) {
        this.IdPais = idPais;
    }
    
    public int getIdPais() {
        return this.IdPais;
    }
    
    //Getter y Setter de nombre
    public void setNombre(String nombre) {
        this.Nombre =nombre;
    }
    
    public String getNombre() {
        return this.Nombre;
    }
}
