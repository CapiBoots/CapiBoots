package com.example.CapiBoots.controladores.file;


import com.example.CapiBoots.controladores.ContenidosCtrl;
import com.example.CapiBoots.modelos.*;
import com.example.CapiBoots.servicios.ContenidosSrvcImpls;
import com.example.CapiBoots.servicios.SeriesSrvcImpls;
import com.example.CapiBoots.servicios.file.DBFileStorageService;
import com.example.CapiBoots.servicios.file.FileSystemStorageService;
import com.example.CapiBoots.servicios.ifxUsuarioSrvc;
import org.apache.catalina.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

/**
 * Controlador encargado de manejar la carga de archivos.
 * La clase utiliza las anotaciones @Controller y @CrossOrigin para definir que es un controlador de Spring y
 * permitir solicitudes CORS desde el servidor web localhost en el puerto 8080.
 * La clase utiliza la inyección de dependencias (@Autowired) para acceder a los servicios necesarios para la carga
 * y almacenamiento de archivos, así como para el acceso a la base de datos de archivos.
 */
@Controller
@CrossOrigin("http://localhost:8080")
public class FileController {

    /**
     * Servicio de almacenamiento de archivos en FileSystem local utilizado por el controlador.
     */
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @Autowired
    private ContenidosSrvcImpls contenidosSrvc;

    @Autowired
    private SeriesSrvcImpls seriesSrcv;

    /**
     * Servicio de almacenamiento de archivos en la base de datos utilizado por el controlador.
     */
    @Autowired
    private DBFileStorageService dbFileStorageService;

    /**
     * Servicio de usuario utilizado por el controlador.
     */
    //   @Autowired
    //  private ifxUsuarioSrvc userService;

    /**
     * Constructor de la clase que recibe el servicio de almacenamiento de archivos como parámetro.
     * La anotación @Autowired se utiliza para inyectar automáticamente el servicio necesario al crear una instancia de la clase.
     *
     * @param fileSystemStorageService el servicio de almacenamiento de archivos a utilizar
     */
    @Autowired
    public FileController(FileSystemStorageService fileSystemStorageService) {
        this.fileSystemStorageService = fileSystemStorageService;
    }


    /**
     * Método que se encarga de listar los archivos que han sido subidos al servidor.
     *
     * @param model el modelo que se va a utilizar para pasar los datos a la vista
     * @return el nombre de la vista a la que se va a redirigir
     * @throws IOException si ocurre un error al cargar los archivos
     */
    @GetMapping("/files")
    public String listAllUploadedFiles(Model model,Authentication authentication) throws IOException {

        //        Obtenemos todos los archivos almacenados en el servicio de almacenamiento predeterminado.
        //       Para cada archivo, generamos una URL que permita descargar el archivo desde el servidor.
        List<FileInfo> files = fileSystemStorageService.loadAll();
//
        //       Obtenemos todos los archivos almacenados en el servicio de almacenamiento de la base de datos.
        //       Para cada archivo, generamos una URL que permita descargar el archivo desde el servidor.
        List<FileInfo> dbFiles = dbFileStorageService.getAllFileInfos();
//
        //      Obtenemos el nombre de usuario del objeto de autenticacion
        //    String username = authentication.getName();
        //        Buscamos al usuario correspondiente al nombre de usuario obtenido anteriormente.
        //      Usuario user = userService.buscaPorNombre(username);
//
        //        Obtenemos todos los archivos asociados al usuario y almacenados en la base de datos
        //        Para cada archivo, generamos una URL que permita descargar el archivo desde el servidor.
        // List<FileInfo> dbUserFiles = dbFileStorageService.getUserFileInfos(user);

        //       Agregamos las URLs de los archivos del servicio de almacenamiento predeterminado al modelo.
        model.addAttribute("files", files);

        //      Agregamos los objetos FileInfo del servicio de almacenamiento de la base de datos al modelo.
        model.addAttribute("DBfiles", dbFiles);

        //    model.addAttribute("dbUserFiles", dbUserFiles);


        //        Devolvemos el nombre de la vista a la que se va a redirigir.
        return "listFicheros";
    }


    /**
     * Método que se encarga de descargar un archivo desde el servidor.
     *
     * @param filename el nombre del archivo que se va a descargar
     * @return una respuesta HTTP con el archivo a descargar en el cuerpo de la respuesta '@GetMapping("/files/{filename:.+}")' es una anotación utilizada en un método dentro de un controlador de Spring MVC que indica que el método manejará solicitudes GET para una URL que incluya una variable de ruta {filename}. El . + en la variable de ruta indica que la variable puede contener un punto y cualquier otro carácter después de él. Esto es necesario porque algunos nombres de archivo pueden contener puntos en su nombre y la expresión regular predeterminada utilizada por Spring no permitiría estos caracteres. Por ejemplo, si la URL solicitada es /files/myfile.txt, la variable de ruta {filename} será igual a myfile.txt. Si la URL solicitada es /files/myfile.pdf, la variable de ruta {filename} también será igual a myfile.pdf.
     */
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {
        //       Cargamos el archivo como un recurso a través del servicio de almacenamiento predeterminado.
        Resource file = fileSystemStorageService.loadAsResource(filename);
        // Obtenermos el tipo del archivo para la var MIMETYPE
        String mime = Files.probeContentType(file.getFile().toPath());


        //       Construimos una respuesta HTTP con el archivo a descargar en el cuerpo de la respuesta.
        //       También establecemos el encabezado "Content-Disposition" con el nombre de archivo para indicar que se debe descargar.
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\"" + file.getFilename() + "\"").contentType(MediaType.valueOf(mime)).body(file);
    }



    /**
     * Método que se encarga de manejar la subida de un archivo al servidor.
     *
     * @param file               el archivo que se va a subir
     * @param redirectAttributes los atributos que se van a utilizar para pasar información entre solicitudes
     * @return el nombre de la vista a la que se va a redirigir
     */
    //Contenidos Contenido-Logo-Fondo
    @PostMapping("/contenido/nuevo-contenido/uploadToFileSystem/{id}")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable Long id, Model modelo) {

        Optional<Contenidos> contOptional = contenidosSrvc.buscarContenidoId(id);
        if (contOptional.isPresent()) {
            Contenidos cont1 = contOptional.get();
            modelo.addAttribute("contenido", cont1);
            cont1.setRutaVideo("/files/" + file.getOriginalFilename());
            contenidosSrvc.guardar(cont1);
        } else {
            return "error";
        }
        //        Guardamos el archivo en el servicio de almacenamiento predeterminado.
        fileSystemStorageService.save(file);


        return "forms/subir-contenido";
    }
    @PostMapping("/contenido/nuevo-contenido/subir-logo/{id}")
    public String subirLogo(@RequestParam("file") MultipartFile file, @PathVariable Long id, Model modelo) {

        Optional<Contenidos> contOptional = contenidosSrvc.buscarContenidoId(id);
        if (contOptional.isPresent()) {
            Contenidos cont1 = contOptional.get();
            modelo.addAttribute("contenido", cont1);
            cont1.setImagenLogo("/files/" + file.getOriginalFilename());
            contenidosSrvc.guardar(cont1);
        } else {
            return "error";
        }
        //        Guardamos el archivo en el servicio de almacenamiento predeterminado.
        fileSystemStorageService.save(file);


        return "forms/subir-contenido";
    }
    @PostMapping("/contenido/nuevo-contenido/subir-fondo/{id}")
    public String subirFondo(@RequestParam("file") MultipartFile file, @PathVariable Long id, Model modelo) {

        Optional<Contenidos> contOptional = contenidosSrvc.buscarContenidoId(id);
        if (contOptional.isPresent()) {
            Contenidos cont1 = contOptional.get();
            modelo.addAttribute("contenido", cont1);
            cont1.setImagenFondo("background-image: url('/files/" + file.getOriginalFilename() + "');");
            contenidosSrvc.guardar(cont1);
        } else {
            return "error";
        }
        //        Guardamos el archivo en el servicio de almacenamiento predeterminado.
        fileSystemStorageService.save(file);


        return "forms/subir-contenido";
    }
    //Series Serie-Logo-Fondo
    @PostMapping("/serie/nueva-serie/uploadToFileSystem/{id}")
    public String subirSerie(@RequestParam("file") MultipartFile file, @PathVariable Long id, Model modelo) {

        Optional<Series> seriOptional = seriesSrcv.buscaId(id);
        if (seriOptional.isPresent()) {
            Series seri = seriOptional.get();
            modelo.addAttribute("seri", seri);
            seri.setRutaVideo("/files/" + file.getOriginalFilename());
            seriesSrcv.guardar(seri);
        } else {
            return "error";
        }
        //        Guardamos el archivo en el servicio de almacenamiento predeterminado.
        fileSystemStorageService.save(file);


        return "forms/subir-serie";
    }
    @PostMapping("/serie/nueva-serie/subir-logo/{id}")
    public String subirLogoSerie(@RequestParam("file") MultipartFile file, @PathVariable Long id, Model modelo) {

        Optional<Series> seriOptional = seriesSrcv.buscaId(id);
        if (seriOptional.isPresent()) {
            Series seri = seriOptional.get();
            modelo.addAttribute("seri", seri);
            seri.setImagenLogo("/files/" + file.getOriginalFilename());
            seriesSrcv.guardar(seri);
        } else {
            return "error";
        }
        //        Guardamos el archivo en el servicio de almacenamiento predeterminado.
        fileSystemStorageService.save(file);


        return "forms/subir-serie";
    }
    @PostMapping("/serie/nueva-serie/subir-fondo/{id}")
    public String subirFondoSerie(@RequestParam("file") MultipartFile file, @PathVariable Long id, Model modelo) {

        Optional<Series> seriOptional = seriesSrcv.buscaId(id);
        if (seriOptional.isPresent()) {
            Series seri = seriOptional.get();
            modelo.addAttribute("seri", seri);
            seri.setImagenFondo("background-image: url('/files/" + file.getOriginalFilename() + "');");
            seriesSrcv.guardar(seri);
        } else {
            return "error";
        }
        //        Guardamos el archivo en el servicio de almacenamiento predeterminado.
        fileSystemStorageService.save(file);


        return "forms/subir-serie";
    }

    @GetMapping("/serie/nueva-serie/subir-archivo/{id}")
    public String subirSe(@PathVariable Long id, Model modelo){

        Optional<Series> seriOptional = seriesSrcv.buscaId(id);
        if (seriOptional.isPresent()) {
            modelo.addAttribute("seri", seriOptional.get());
        } else {
            return "error";
        }
        return "forms/subir-serie";
    }

    @GetMapping("/contenido/nuevo-contenido/subir-archivo/{id}")
    public String subir(@PathVariable Long id, Model modelo){

        Optional<Contenidos> contOptional = contenidosSrvc.buscarContenidoId(id);
        if (contOptional.isPresent()) {
            modelo.addAttribute("contenido", contOptional.get());
        } else {
            return "error";
        }
        return "forms/subir-contenido";
    }


    /**
     * Método que recibe una solicitud POST para cargar un archivo a la base de datos.
     *
     * @param file               el archivo cargado en el formulario
     * @param redirectAttributes objeto utilizado para agregar atributos a la redirección
     * @param authentication     objeto que representa la información de autenticación del usuario que realiza la solicitud
     * @return una cadena de texto con la vista redirigida
     */
    @PostMapping("/uploadToDatabase")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             RedirectAttributes redirectAttributes,
                             Authentication authentication) {

        String message = "";
        try {
            //           Almacenar el archivo en la base de datos
            dbFileStorageService.store(file);

            //            Agregar mensaje a los atributos de la redirección
            redirectAttributes.addFlashAttribute("message",
                    "¡Archivo " + file.getOriginalFilename() + " cargado exitosamente a la base de datos!");

//             Redirigir a la lista de archivos
            return "redirect:/files";

        } catch (Exception e) {
            //            Agregar mensaje de error a los atributos de la redirección
            redirectAttributes.addFlashAttribute("errorMsg", e.getLocalizedMessage());

            //            Redirigir a la página de error
            return "error";
        }
    }


    /**
     * Método que recibe una solicitud POST para cargar un archivo propio de un usuario a la base de datos.
     *
     * @param file               el archivo cargado en el formulario
     * @param redirectAttributes objeto utilizado para agregar atributos a la redirección
     * @param authentication     objeto que representa la información de autenticación del usuario que realiza la solicitud
     * @return una cadena de texto con la vista redirigida
     */
    //  @PostMapping("/uploadUserFileToDatabase")
    //  public String uploadUserFileToDatabase(@RequestParam("file") MultipartFile file,
    //     RedirectAttributes redirectAttributes,
    //   Authentication authentication) {

    //   String message = "";
    //  try {
    //          Obtenemos el nombre de usuario del objeto de autenticacion
    //     String username = authentication.getName();
    //         Buscamos al usuario correspondiente al nombre de usuario obtenido anteriormente.
    //     User user = userService.findUserByEmail(username);
    //      User user = userService.findUserByEmail(username);

    //       Almacenamos el archivo del usuario en la base de datos
    //    dbFileStorageService.storeUserFile(file,user);

    //     Agregar mensaje a los atributos de la redirección
    //       redirectAttributes.addFlashAttribute("message",
    //            "¡Archivo " + file.getOriginalFilename() + " cargado exitosamente a la base de datos!");

    //   Redirigir a la lista de archivos
    //  return "redirect:/files";

    //  } catch (Exception e) {
    //  Agregar mensaje de error a los atributos de la redirección
    //    redirectAttributes.addFlashAttribute("errorMsg", e.getLocalizedMessage());

    //Redirigir a la página de error
    //   return "error";
    // }
    //   }



    /**
     * Método que se encarga de manejar la subida de un archivo de usuario al servidor.
     *
     * @param file               el archivo que se va a subir
     * @param redirectAttributes los atributos que se van a utilizar para pasar información entre solicitudes
     * @param authentication     la información de autenticación del usuario que está realizando la solicitud
     * @return el nombre de la vista a la que se va a redirigir
     */
//    @PostMapping("/uploadUserFileToFileSystem")
//    public String handleUserFileUpload(@RequestParam("file") MultipartFile file,
//                                       RedirectAttributes redirectAttributes,
//                                       Authentication authentication) {
//
// //        Obtenemos el nombre de usuario del usuario autenticado.
//        String username = authentication.getName();
////
// //        Buscamos al usuario correspondiente al nombre de usuario obtenido anteriormente.
//        User user = userService.findUserByEmail(username);
//
//  //      Obtenemos el ID del usuario.
//       Long userId = user.getId();
//
//  //       Guardamos el archivo en el servicio de almacenamiento de archivos de usuario.
//        fileSystemStorageService.saveUserFile(file, userId);
//
//  //       Agregamos un mensaje de éxito a los atributos de redirección.
//        redirectAttributes.addFlashAttribute("message",
//                "¡Se ha subido correctamente el archivo de usuario " + file.getOriginalFilename() + "!");
//
//  //       Redirigimos al usuario a la vista que lista los archivos subidos al servidor.
//        return "redirect:/files";
//    }
////
////
    /**
     * Método que se encarga de obtener el archivo de la base de datos con el id proporcionado.
     *
     * @param id El id del archivo a obtener de la base de datos.
     * @return ResponseEntity con el archivo obtenido y las cabeceras necesarias para la descarga del archivo.
     */
    @GetMapping("/databasefiles/{id}")
    public ResponseEntity<byte[]> getDatabaseFile(@PathVariable String id) {
        //       Obtiene el archivo de la base de datos utilizando el servicio correspondiente.
        FileDB fileDB = dbFileStorageService.getFile(id);

        //        Retorna un ResponseEntity con el archivo obtenido y las cabeceras necesarias para la descarga del archivo.
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
                .body(fileDB.getData());
    }
//
//
    /**
     * Método que elimina un archivo de la base de datos a través de su identificador.
     *
     * @param id El identificador del archivo a eliminar.
     * @return La página de archivos después de eliminar el archivo.
     */
//    @GetMapping("/databasefiles/delete/{id}")
//    public String deleteFileDB(@PathVariable String id) {
//        dbFileStorageService.deleteFile(id);
//        return "redirect:/files";
//    }


    @GetMapping("/files/delete/{fileName}")
    public String deleteFileFromFileSystem(@PathVariable String fileName) {
        fileSystemStorageService.deleteFile(fileName);
        return "redirect:/files";
    }

//    @GetMapping("/databasefiles/desasociarUserFile/{id}")
//    public String deleteFileFromFileSystem(@PathVariable String id, Authentication authentication) {
// //        Obtenemos el nombre de usuario del usuario autenticado.
//        String username = authentication.getName();
// //        Buscamos al usuario correspondiente al nombre de usuario obtenido anteriormente.
//        User user = userService.findUserByEmail(username);
//
//        dbFileStorageService.desasociarUserFile(id, user);
//        return "redirect:/files";
//    }







    /**
     * Controlador de excepción para la excepción FileNotFoundException.
     * Retorna una respuesta con un estado HTTP 404 (no encontrado).
     *
     * @param exc la excepción FileNotFoundException que se ha producido
     * @return ResponseEntity con un estado HTTP 404 (no encontrado)
     */
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(FileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }


}