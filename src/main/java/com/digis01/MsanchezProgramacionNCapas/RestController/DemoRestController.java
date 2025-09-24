
package com.digis01.MsanchezProgramacionNCapas.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Tag(name="RestController Demo", description="Controlador enfocado a practicar")
@RestController
@RequestMapping("demo")
public class DemoRestController {
    
    @Operation(summary = "Obtener todos los usuarios", description = "Método para retornar todos los usuarios")
    @GetMapping
    public Usuario Index() {
        return new Usuario();
    }
    //Suma de dos numeros
    @Operation(summary = "Suma de dos números", description = "Método para sumar dos numeros enteros")
    @GetMapping("/suma")
    public String Suma(@RequestParam int numeroA, @RequestParam int numeroB) {
        return "La suma es: " + (numeroA + numeroB);
    }
    
    //Saludo + informacion
    @Operation(summary = "Saludo + información", description = "Método para saludar a un usuario y obtener toda su información")
    @PostMapping("Saludo")
    public String Saludo(@RequestBody Usuario usuario) {
        return "Hola " + usuario.getNombre() + " " +
                usuario.getApellidoPaterno() + " " +
                usuario.getApellidoMaterno() + "\n" +
                "tu username es: " + usuario.getUserName() + "\n" +
                "tu fecha de nacimiento es: " + usuario.getFechaNacimiento() + "\n" +
                "tu email es: " + usuario.getEmail() + "\n" +
                "tu sexo es: " + usuario.getSexo() + "\n" +
                "tu telefono es: " + usuario.getTelefono() + "\n" +
                "tu celular es: " + usuario.getCelular() + "\n" +
                "tu CURP es: " + usuario.getCurp() + "\n" +
                "tu rol es : " + usuario.Rol.getNombre() + "\n" +
                "con direccion: " + usuario.Direcciones.get(0).getCalle() + " " +
                usuario.Direcciones.get(0).getNumeroInterior() + " " +
                usuario.Direcciones.get(0).getNumeroExterior() +
                ", colonia " + usuario.Direcciones.get(0).Colonia.getNombre() + ", " +
                usuario.Direcciones.get(0).Colonia.municipio.getNombre() + ", " +
                usuario.Direcciones.get(0).Colonia.municipio.estado.getNombre() + ", " +
                usuario.Direcciones.get(0).Colonia.municipio.estado.pais.getNombre();
                          
    }
    
    //Suma de elementos de un arreglo
    @Operation(summary = "Suma de elementos de un arreglo", description = "Método para sumar todos los elementos de un arreglo")
    @PostMapping("/sumaArreglo")
    public Map<String, Integer> SumaArreglo(@RequestBody int[] numeros) {
        int suma = 0;
        for (int numero : numeros) {
            suma += numero;
        }
        
        return Map.of("El resultado es: ", suma);
    }
    
    //Actualizacion de elemento de elemento de arreglo
    @Operation(summary = "Actualizar un elemento de un arreglo", description = "Método para actualizar el nombre de un elemento del arreglo")
    @PatchMapping("/actualizar")
    public String[] actualizacionNombre(@RequestParam int indice, 
            @RequestParam String nombreNuevo) {
        
        String[] nombresPersonas = {"Karina", "Manuel", "Laura", "Angel"};
        
        nombresPersonas[indice] = nombreNuevo;
        
        return nombresPersonas;
    }
}
