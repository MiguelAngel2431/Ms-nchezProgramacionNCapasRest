package com.digis01.MsanchezProgramacionNCapas.RestController;

import com.digis01.MsanchezProgramacionNCapas.DAO.RolJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.DAO.UsuarioJPADAOImplementation;
import com.digis01.MsanchezProgramacionNCapas.JPA.Colonia;
import com.digis01.MsanchezProgramacionNCapas.JPA.Direccion;
import com.digis01.MsanchezProgramacionNCapas.JPA.ErrorCM;
import com.digis01.MsanchezProgramacionNCapas.JPA.Result;
import com.digis01.MsanchezProgramacionNCapas.JPA.Rol;
import com.digis01.MsanchezProgramacionNCapas.JPA.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "RestController de Usuario", description = "Controlador enfocado a métodos del usuario")
@RestController
@RequestMapping("usuarioapi")
//@CrossOrigin(origins = "*")
public class UsuarioRestController {

    @Autowired
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;

    //UsuarioDireccionGetAll();
    @Operation(summary = "Obtener todos los usuarios", description = "Método para retornar todos los usuarios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Los usuarios fueron obtenidos satisfactoriamente"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener a los usuarios")
    })
    @GetMapping()
    public ResponseEntity GetAll() {

        Result result;

        try {

            result = usuarioJPADAOImplementation.GetAll();
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

    //Detalles de usuario y sus direcciones;
    @Operation(summary = "Obtener un usuario y sus direcciones (detalles de usuario)", description = "Método para retornar a un usuario y sus direcciones")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue obtenido satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener al usuario")
    })
    @GetMapping("/details/{IdUsuario}")
    public ResponseEntity GetDetail(@Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result;

        result = usuarioJPADAOImplementation.GetDetail(IdUsuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    //Datos del usuario;
    @Operation(summary = "Obtener a un usuario", description = "Método para retornar datos de un usuario (sin direcciones)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue obtenido satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener al usuario")
    })
    @GetMapping("/{IdUsuario}")
    public ResponseEntity GetById(@Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result;

        result = usuarioJPADAOImplementation.GetById(IdUsuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    //Agregar usuario
    @Operation(summary = "Agregar a un usuario", description = "Método para agregar a un usuario nuevo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue agregado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el algo en el cuerpo del JSON esta mal escrito o declarado. "
                + "Ej. de cuerpo del Json: "
                + "{\n"
                + "\n"
                + "  \"Rol\": {\n"
                + "\n"
                + "    \"idRol\": 3\n"
                + "\n"
                + "  },\n"
                + "\n"
                + "  \"Direcciones\": [\n"
                + "\n"
                + "    {\n"
                + "      \n"
                + "      \"Colonia\": {\n"
                + "\n"
                + "        \"idColonia\": 1\n"
                + "        \n"
                + "      },\n"
                + "\n"
                + "      \"numeroInterior\": \"1\",\n"
                + "      \"numeroExterior\": \"2\",\n"
                + "      \"calle\": \"AV GREEN LEAF\"\n"
                + "      \n"
                + "    }\n"
                + "\n"
                + "  ],\n"
                + "\n"
                + "  \"telefono\": \"551234\",\n"
                + "\n"
                + "  \"apellidoPaterno\": \"Lopez\",\n"
                + "\n"
                + "  \"nombre\": \"Carlos\",\n"
                + "\n"
                + "  \"apellidoMaterno\": \"Lopez\",\n"
                + "\n"
                + "  \"fechaNacimiento\": \"2025-09-09T16:08:29.814Z\",\n"
                + "\n"
                + "  \"email\": \"clopez@gmaail.com\",\n"
                + "\n"
                + "  \"userName\": \"clopez34\",\n"
                + "\n"
                + "  \"sexo\": \"M \",\n"
                + "\n"
                + "  \"celular\": \"12345\",\n"
                + "\n"
                + "  \"curp\": \"dfdft45\",\n"
                + "\n"
                + "  \"imagen\": null,\n"
                + "\n"
                + "  \"password\": \"123\",\n"
                + "\n"
                + "  \"status\": 1\n"
                + "\n"
                + "}"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al obtener al usuario")
    })
    @PostMapping()
    public ResponseEntity Add(@RequestBody Usuario usuario) {

        Result result;

        try {

            result = usuarioJPADAOImplementation.Add(usuario);
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

    //Editar usuario
    @Operation(summary = "Actualizar a un usuario", description = "Método para actualizar datos de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue actualizado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el algo en el cuerpo del JSON esta mal escrito o declarado. "
                + "Ej. cuerpo del JSON: "
                + "{\n"
                + "    \n"
                + "    \"Rol\": {\n"
                + "      \"idRol\": 2\n"
                + "    },\n"
                + "    \"password\": \"AngPerez9\",\n"
                + "    \"idUsuario\": 9,\n"
                + "    \"fechaNacimiento\": \"2012-11-04T06:00:00.000+00:00\",\n"
                + "    \"email\": \"APerez9@outlook.com\",\n"
                + "    \"sexo\": \"F \",\n"
                + "    \"apellidoPaterno\": \"Perez\",\n"
                + "    \"telefono\": \"595913549\",\n"
                + "    \"userName\": \"APerez9\",\n"
                + "    \"nombre\": \"Angelica\",\n"
                + "    \"apellidoMaterno\": \"Rodriguez\",\n"
                + "    \"imagen\": null,\n"
                + "    \"curp\": null,\n"
                + "    \"celular\": \"55123456789\",\n"
                + "    \"status\": 0\n"
                + "  }\n"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al actualizar al usuario")
    })
    @PutMapping("/{IdUsuario}")
    public ResponseEntity EditarUsuario(@RequestBody Usuario usuario, @Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result;

        usuario.setIdUsuario(IdUsuario);
        result = usuarioJPADAOImplementation.EditarUsuario(usuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    //Eliminar usuario
    @Operation(summary = "Eliminar a un usuario", description = "Método para eliminar a un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El usuario fue eliminado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al eliminar al usuario")
    })
    @DeleteMapping("/{IdUsuario}")
    public ResponseEntity EliminarUsuario(@Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result;

        result = usuarioJPADAOImplementation.EliminarUsuario(IdUsuario);
        result.correct = true;
        return ResponseEntity.status(result.status).body(result);

    }

    //Baja logica
    @Operation(summary = "Proceso de baja lógica", description = "Método para actualizar el estado (activo o inactivo) de un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El estado del usuario fue cambiado satisfactoriamente"),
        @ApiResponse(responseCode = "400", description = "El ID ingresado no existe o el algo en el cuerpo del JSON esta mal escrito o declarado (solo necesitas el campo 'status')."
                + "Ej. cuerpo Json: "
                + "{\n"
                + "  \"status\": 0\n"
                + "}"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al actualizar el estado del usuario")
    })
    @PutMapping("/bajaLogica/{IdUsuario}")
    public ResponseEntity BajaLogica(@RequestBody Usuario usuario, @Parameter(description = "ID único del usuario", required = true) @PathVariable int IdUsuario) {

        Result result;

        try {
            //usuario.setIdUsuario(IdUsuario);
            result = usuarioJPADAOImplementation.BajaLogica(IdUsuario);
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

    //Mandar el archivo a validacion (si no tiene errores)
    @Operation(summary = "Carga masiva (cargar archivo)", description = "Método para cargar el archivo (excel o txt) y validar si contiene errores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El archivo es correcto"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al subir el archivo")
    })
    @PostMapping("/cargamasiva")
    public ResponseEntity<Result> CargaMasiva(@RequestParam("file") MultipartFile file, Model model, HttpSession session) {

        Result result = new Result();

        try {

            String root = System.getProperty("user.dir");
            String rutaArchivo = "/src/main/resources/archivos/";
            String fechaSubida = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String rutaFinal = root + rutaArchivo + fechaSubida + file.getOriginalFilename();

            String nombreArchivo = file.getOriginalFilename();
            String rutaLog = "C:\\Users\\digis\\OneDrive\\Documentos\\Miguel Angel Sánchez González\\MsanchezProgramacionNCapas\\com.digis01_MsanchezProgramacionNCapasRESTT\\src\\main\\java\\com\\digis01\\MsanchezProgramacionNCapas\\RestController\\LogCargaMasiva.txt";

            //Encriptamos la ruta del archivo con sha1
            String hash = encriptarSHA1(rutaFinal);

            try {
                file.transferTo(new File(rutaFinal));
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }

            //Txt
            if (file.getOriginalFilename().split("\\.")[1].equals("txt")) {
                List<Usuario> usuarios = ProcesarTXT(new File(rutaFinal));
                List<ErrorCM> errores = ValidarDatos(usuarios);

                //Si la lisra de errores esta vacia...
                if (errores.isEmpty()) {

                    EstadoProceso status = EstadoProceso.Procesar;
                    String observacion = "Listo para ser procesado";

                    String linea = hash + "|" + nombreArchivo + "|" + status + "|" + fechaSubida + "|" + observacion;

                    //Enviar parametros al txt
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaLog, true))) {
                        writer.write(linea);
                        writer.newLine(); // salto de línea
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    result.correct = true;
                    result.object = hash;

                    //Si la lista contiene errores...
                } else {

                    EstadoProceso status = EstadoProceso.Error;
                    String observacion = "Archivo con errores";

                    String linea = hash + "|" + nombreArchivo + "|" + status + "|" + fechaSubida + "|" + observacion;

                    //Enviar parametros al txt
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaLog, true))) {
                        writer.write(linea);
                        writer.newLine(); // salto de línea
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    result.correct = false;
                    result.listaErrores = errores;
                }

                //Excel
            } else {
                List<Usuario> usuarios = ProcesarExcel(new File(rutaFinal));
                List<ErrorCM> errores = ValidarDatos(usuarios);

                if (errores.isEmpty()) {

                    EstadoProceso status = EstadoProceso.Procesar;
                    String observacion = "Listo para ser procesado";

                    String linea = hash + "|" + nombreArchivo + "|" + status + "|" + fechaSubida + "|" + observacion;

                    //Enviar parametros al txt
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaLog, true))) {
                        writer.write(linea);
                        writer.newLine(); // salto de línea
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    result.correct = true;
                    result.object = hash;

                } else {

                    EstadoProceso status = EstadoProceso.Error;
                    String observacion = "Archivo con errores";

                    String linea = hash + "|" + nombreArchivo + "|" + status + "|" + fechaSubida + "|" + observacion;

                    //Enviar parametros al txt
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaLog, true))) {
                        writer.write(linea);
                        writer.newLine(); // salto de línea
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    result.correct = false;
                    result.listaErrores = errores;
                }
            }

            return ResponseEntity.status(200).body(result);

        } catch (Exception ex) {
            result = new Result();
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.status(500).body(result);
        }

        //return "CargaMasiva";
    }
    
    //Procesar el archivo libre de errores
    @Operation(summary = "Carga masiva (insersión de datos)", description = "Método para insertar los datos del archivo en la BD, obtiendolos mediante el hash")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos insertados"),
        @ApiResponse(responseCode = "500", description = "Algo salió mal al subir el archivo")
    })
    @GetMapping("/cargamasiva/procesar/{hash}")
    public ResponseEntity<Result> ProcesarArchivo(@Parameter(description = "Hash (cadena encriptada)", required = true) @PathVariable("hash") String hash) {

        Result result = new Result();

        String rutaLog = "C:\\Users\\digis\\OneDrive\\Documentos\\Miguel Angel Sánchez González\\MsanchezProgramacionNCapas\\com.digis01_MsanchezProgramacionNCapasRESTT\\src\\main\\java\\com\\digis01\\MsanchezProgramacionNCapas\\RestController\\LogCargaMasiva.txt";

        try {
            //Leer y encontrar la linea de codigo
            List<String> lineas = Files.readAllLines(Paths.get(rutaLog));
            Optional<String> lineaArchivo = lineas.stream().filter(
                    linea -> linea.startsWith(hash)).findFirst();

            //Si la linea existe
            if (lineaArchivo.isPresent()) {

                String[] partes = lineaArchivo.get().split("\\|");
                String nombreArchivo = partes[1];
                String estadoArchivo = partes[2];
                String rutaArchivo = System.getProperty("user.dir") + "/src/main/resources/archivos/" + partes[3] + partes[1];

                //Validar que el estado del log no venga en error
                if (!estadoArchivo.equals("Error") && !estadoArchivo.equals("Procesar")) {
                    result.correct = false;
                    result.errorMessage = "El estado del archivo no es valido para procesar";
                    return ResponseEntity.status(400).body(result);
                }

                File archivo = new File(rutaArchivo);

                List<Usuario> usuarios;

                if (nombreArchivo.endsWith(".txt")) {
                    usuarios = ProcesarTXT(archivo);
                } else {
                    usuarios = ProcesarExcel(archivo);
                }

                //Validar que el archivo no haya sido cargado con anterioridad
                boolean archivoYaExiste = false;

                for (String linea : lineas) {
                    String[] campos = linea.split("\\|");
                    if (campos.length >= 3) { //Solo requiero acceder a partes 1 y 2
                        String archivoEnLog = campos[1]; // nombreArchivo
                        String estado = campos[2]; // estado
                        
                        //Si  encuentra una coincidencia
                        if (archivoEnLog.equalsIgnoreCase(nombreArchivo)
                                && (estado.equals("Procesado"))) {

                            archivoYaExiste = true;
                            break; // se detiene cuando encuentra la coincidencia

                        }
                    }
                }

                //Obtenemos la fecha de subida del archivo
                String fechaSubidaStr = partes[3];
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

                LocalDateTime fechaHoraSubida = LocalDateTime.parse(fechaSubidaStr, dateTimeFormatter);
                LocalDateTime fechaHoraAhora = LocalDateTime.now();

                //Calculamos la direfencia de tiempo
                Duration diferencia = Duration.between(fechaHoraSubida, fechaHoraAhora);

                //Validamos que el tiempo no haya exedido dos minutos
                if (estadoArchivo.equals("Procesar") && diferencia.toMinutes() >= 2) {

                    EstadoProceso status = EstadoProceso.Error;
                    String observacion = "Tiempo expirado para procesamiento";

                    String lineaNueva = hash + "|" + nombreArchivo + "|" + status + "|" + partes[3] + "|" + observacion;

                    //Remplaza la linea del log
                    List<String> nuevasLineas = new ArrayList<>();

                    for (String linea : lineas) {
                        if (linea.startsWith(hash)) {
                            nuevasLineas.add(lineaNueva);
                        } else {
                            nuevasLineas.add(linea);
                        }
                    }

                    //Sobreescribimos el archivo Log
                    Files.write(Paths.get(rutaLog), nuevasLineas);

                } else {

                    //Validamos si el archivo ya existe
                    if (archivoYaExiste) {
                        result.correct = false;
                        result.errorMessage = "Este archivo ya fue cargado anteriormente y no puede duplicarse.";
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
                    }

                    //Acutalzar el log
                    EstadoProceso status = EstadoProceso.Procesado;
                    String observacion = "Archivo procesado";

                    String lineaNueva = hash + "|" + nombreArchivo + "|" + status + "|" + partes[3] + "|" + observacion;

                    //Enviar parametros al txt
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaLog, true))) {
                        writer.write(lineaNueva);
                        writer.newLine(); // salto de línea
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Hacer insersion a la BD
                    for (Usuario usuario : usuarios) {
                        usuarioJPADAOImplementation.Add(usuario);
                    }

                    result.correct = true;
                    result.object = rutaArchivo;

                }

            }

            return ResponseEntity.status(200).body(result);

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
            return ResponseEntity.status(500).body(result);
        }
    }

//    //Procesar el archivo libre de errores
//    @GetMapping("/cargamasiva/procesar")
//    public ResponseEntity<Result> CargaMasivaProcesar(@RequestParam("ruta") String ruta) {
//
//        Result result = new Result();
//
//        try {
//
//            // Decodificamos la ruta
//            String rutaDecodificada = URLDecoder.decode(ruta, StandardCharsets.UTF_8);
//
//            List<Usuario> usuarios;
//
//            if (rutaDecodificada.toLowerCase().endsWith(".txt")) {
//                usuarios = ProcesarTXT(new File(rutaDecodificada));
//            } else {
//                usuarios = ProcesarExcel(new File(ruta));
//            }
//
//            for (Usuario usuario : usuarios) {
//                usuarioJPADAOImplementation.Add(usuario);
//            }
//
//            result.correct = true;
//            return ResponseEntity.status(200).body(result);
//
//        } catch (Exception ex) {
//
//            System.out.println(ex.getLocalizedMessage());
//            result.correct = false;
//            return ResponseEntity.status(500).body(result);
//        }
//
//    }
    //Metodo para validar si el TXT contiene errores (YA FUNCIONA, YA NO LE MUEVAS)
    private List<Usuario> ProcesarTXT(File file) {
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String linea = "";
            List<Usuario> usuarios = new ArrayList<>();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            //Mientras haya usuarios... 
            while ((linea = bufferedReader.readLine()) != null) {
                String[] campos = linea.split("\\|");
                Usuario usuario = new Usuario();
                usuario.setUserName(campos[0]);
                usuario.setNombre(campos[1]);
                usuario.setApellidoPaterno(campos[2]);
                usuario.setApellidoMaterno(campos[3]);
                usuario.setEmail(campos[4]);
                usuario.setPassword(campos[5]);

                Date fecha = campos[6] == "" ? null : simpleDateFormat.parse(campos[6]);
                usuario.setFechaNacimiento(fecha);
                //usuario.setFechaNacimiento(campos[6] != "" ? simpleDateFormat.parse(campos[6]) : simpleDateFormat.parse(campos[6]));

                usuario.setSexo(campos[7]);
                usuario.setTelefono(campos[8]);
                usuario.setCelular(campos[9]);
                usuario.setCurp(campos[10]);

                usuario.Rol = new Rol();
                usuario.Rol.setIdRol(campos[11] != "" ? Integer.parseInt(campos[11]) : 0);

                usuario.Direcciones = new ArrayList<>();
                Direccion direccion = new Direccion();

                direccion.setCalle(campos[12]);
                direccion.setNumeroInterior(campos[13]);
                direccion.setNumeroExterior(campos[14]);

                direccion.Colonia = new Colonia();
                direccion.Colonia.setIdColonia(campos[15] != "" ? Integer.parseInt(campos[15]) : 0);

                usuarios.add(usuario);
                usuario.Direcciones.add(direccion);

            }

            return usuarios;

        } catch (Exception ex) {
            System.out.println("Error");
            return null;
        }
    }

    //Metodo para validar si el EXCEL contiene errores (YA FUNCIONA, YA NO LE MUEVAS)
    private List<Usuario> ProcesarExcel(File file) {
        List<Usuario> usuarios = new ArrayList<>();

        //Abrimos el excel de forma implicita
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            for (Row row : sheet) {
                Usuario usuario = new Usuario();

                usuario.setUserName(row.getCell(0) != null ? row.getCell(0).toString() : "");
                usuario.setNombre(row.getCell(1) != null ? row.getCell(1).toString() : "");
                usuario.setApellidoPaterno(row.getCell(2) != null ? row.getCell(2).toString() : "");
                usuario.setApellidoMaterno(row.getCell(3) != null ? row.getCell(3).toString() : "");
                usuario.setEmail(row.getCell(4) != null ? row.getCell(4).toString() : "");
                usuario.setPassword(row.getCell(5) != null ? row.getCell(5).toString() : "");

                if (row.getCell(6) != null) {
                    if (row.getCell(6).getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(row.getCell(6))) {
                        usuario.setFechaNacimiento(row.getCell(6).getDateCellValue());
                    } else {
                        usuario.setFechaNacimiento(simpleDateFormat.parse(row.getCell(6).toString()));
                    }
                }
                //usuario.setFechaNacimiento(row.getCell(6) != null ? row.getCell(6) : "");
                usuario.setSexo(row.getCell(7) != null ? row.getCell(7).toString() : "");

                //String numero = row.getCell(8).toString();
                DataFormatter dataFormatter = new DataFormatter();
                usuario.setTelefono(row.getCell(8) != null ? dataFormatter.formatCellValue(row.getCell(8)) : "");
                usuario.setCelular(row.getCell(9) != null ? dataFormatter.formatCellValue(row.getCell(9)) : "");
                usuario.setCurp(row.getCell(10) != null ? row.getCell(10).toString() : "");

                usuario.Rol = new Rol();
                usuario.Rol.setIdRol(row.getCell(11) != null ? (int) row.getCell(11).getNumericCellValue() : 0);

                usuario.Direcciones = new ArrayList<>();
                Direccion direccion = new Direccion();

                direccion.setCalle(row.getCell(12) != null ? row.getCell(12).toString() : "");
                direccion.setNumeroInterior(row.getCell(13) != null ? dataFormatter.formatCellValue(row.getCell(13)) : "");
                direccion.setNumeroExterior(row.getCell(14) != null ? dataFormatter.formatCellValue(row.getCell(14)) : "");

                direccion.Colonia = new Colonia();
                direccion.Colonia.setIdColonia(row.getCell(15) != null ? (int) row.getCell(15).getNumericCellValue() : 0);

                usuarios.add(usuario);
                usuario.Direcciones.add(direccion);
            }

            return usuarios;

        } catch (Exception ex) {
            return null;
        }

    }

    //Lógica para validar datos de procesar excel / txt
    private List<ErrorCM> ValidarDatos(List<Usuario> usuarios) {
        List<ErrorCM> errores = new ArrayList<>();
        int linea = 1;
        for (Usuario usuario : usuarios) {
            if (usuario.getUserName() == null || usuario.getUserName() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getUserName(), "Username es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.getNombre() == null || usuario.getNombre() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getNombre(), "Nombre es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.getApellidoPaterno() == null || usuario.getApellidoPaterno() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getApellidoPaterno(), "Apellido paterno es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.getApellidoMaterno() == null || usuario.getApellidoMaterno() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getApellidoMaterno(), "Apellido materno es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.getEmail() == null || usuario.getEmail() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getEmail(), "Email es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.getPassword() == null || usuario.getPassword() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getPassword(), "Password es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.getSexo() == null || usuario.getSexo() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getSexo(), "Sexo es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.getTelefono() == null || usuario.getTelefono() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getTelefono(), "Telefono es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.getCelular() == null || usuario.getCelular() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getCelular(), "Campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.getCurp() == null || usuario.getCurp() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getCurp(), "Celular es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.Rol.getIdRol() == 0) {
                ErrorCM errorCM = new ErrorCM(linea, String.valueOf(usuario.Rol.getIdRol()), "Id Rol es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.Direcciones.get(0).getCalle() == null || usuario.Direcciones.get(0).getCalle() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.Direcciones.get(0).getCalle(), "Direccion es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.Direcciones.get(0).getNumeroInterior() == null || usuario.Direcciones.get(0).getNumeroInterior() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.Direcciones.get(0).getCalle(), "Numero interior es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.Direcciones.get(0).getNumeroExterior() == null || usuario.Direcciones.get(0).getNumeroExterior() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.Direcciones.get(0).getCalle(), "Numero exterior es un campo obligatorio");
                errores.add(errorCM);

            }

            if (usuario.Direcciones.get(0).Colonia.getIdColonia() == 0) {
                ErrorCM errorCM = new ErrorCM(linea, String.valueOf(usuario.Direcciones.get(0).Colonia.getIdColonia()), "Id Colonia es un campo obligatorio");
                errores.add(errorCM);

            }

            //Expresiones Regex
            String SoloLetras = "^[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ\\s]*$"; // Nombre, ApellidoPaterno, ApellidoMaterno
            String SoloNumeros = "^[0-9]*$";
            String SoloLetrasNumeros = "^[a-zA-Z0-9\\s]*$"; //Curp
            String ValidarUserName = "^[^_|0-9|\\s]+[a-zA-Z0-9]+$"; //UserName
            String ValidarEmail = "^[a-zA-Z-0-9]+[^-|\\s]+@[a-zA-Z]+\\.[a-z]+"; //Email
            String ValidarCalle = "^[^@$!%*?&]+$"; //Calle

            if (usuario.getUserName().matches(ValidarUserName)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getUserName(), "Username inválido");
                errores.add(errorCM);
            }
            if (usuario.getNombre().matches(SoloLetras)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getNombre(), "No se permiten números");
                errores.add(errorCM);
            }
            if (usuario.getApellidoPaterno().matches(SoloLetras)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getApellidoPaterno(), "No se permiten números");
                errores.add(errorCM);
            }
            if (usuario.getApellidoMaterno().matches(SoloLetras)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getApellidoMaterno(), "No se permiten números");
                errores.add(errorCM);
            }
            if (usuario.getEmail().matches(ValidarEmail)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getEmail(), "Formato de correo inválido");
                errores.add(errorCM);
            }
            if (usuario.getSexo().matches(SoloLetras)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getSexo(), "Solo se permiten letras");
                errores.add(errorCM);
            }
            if (usuario.getTelefono().matches(SoloNumeros)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getTelefono(), "Solo se permiten numeros");
                errores.add(errorCM);
            }
            if (usuario.getCelular().matches(SoloNumeros)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getCelular(), "Solo se permiten numeros");
                errores.add(errorCM);
            }
            if (usuario.getCurp().matches(SoloLetrasNumeros)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getCurp(), "Formato de CURP inválido");
                errores.add(errorCM);
            }
            /*if (String.valueOf(usuario.Rol.getIdRol()).matches(SoloNumeros)) {   
            } else {
                ErrorCM errorCM = new ErrorCM(linea, String.valueOf(usuario.Rol.getIdRol()), "Solo los numeros estan permitidos");
                errores.add(errorCM);
            }*/
            if (usuario.Direcciones.get(0).getCalle().matches(ValidarCalle)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.Direcciones.get(0).getCalle(), "No se mermiten caracteres especiales");
                errores.add(errorCM);
            }
            if (usuario.Direcciones.get(0).getNumeroInterior().matches(SoloNumeros)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.Direcciones.get(0).getNumeroInterior(), "Solo los numeros están permitidos");
                errores.add(errorCM);
            }
            if (usuario.Direcciones.get(0).getNumeroExterior().matches(SoloNumeros)) {
            } else {
                ErrorCM errorCM = new ErrorCM(linea, usuario.Direcciones.get(0).getNumeroExterior(), "Solo los numeros están permitidos");
                errores.add(errorCM);
            }

            linea++;

        }

        return errores;
    }

    //Método para encriptar la ruta con sha1
    public static String encriptarSHA1(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] mensajeDigest = md.digest(texto.getBytes());

            // Convertimos los bytes a hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : mensajeDigest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Error al encriptar con SHA-1", ex);
        }
    }

    //Método enum
    public enum EstadoProceso {
        Error,
        Procesar,
        Procesado
    }
}
