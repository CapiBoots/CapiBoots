/*
package com.example.CapiBoots.serviciosTest.fileTest;


import com.example.CapiBoots.modelos.FileDB;
import com.example.CapiBoots.modelos.FileInfo;
import com.example.CapiBoots.repositorios.FileDBRepository;
import com.example.CapiBoots.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

*/
/**
 * The type Db file storage service.
 *//*

@WebMvcTest
public class DBFileStorageServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileDBRepository fileDBRepository;
    @MockBean
    private UsuarioRepositorio userRepository;

    */
/**
     * Store file db.
     *
     * @param file the file
     * @return the file db
     *//*

    public FileDB store(MultipartFile file)  {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB;
        try {
            fileDB = new FileDB(null,fileName, file.getContentType(), file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        fileDB = fileDBRepository.save(fileDB);

        return fileDB;
    }

    */
/**
     * Método que guarda un fichero de un usuario en base de datos
     *
     * @param file the file
     * @return the file db
     *//*

//    public FileDB storeUserFile(MultipartFile file, User user)  {
//
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        FileDB fileDB;
//        try {
//
//            fileDB = new FileDB(null,fileName, file.getContentType(), file.getBytes());
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        fileDB = fileDBRepository.save(fileDB);
//        user.getFilesDB().add(fileDB);
//        userRepository.save(user);
//
//        return fileDB;
//    }



    */
/**
     * Gets file.
     *
     * @param id the id
     * @return the file
     *//*

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    */
/**
     * Gets all files.
     *
     * @return the all files
     *//*

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    */
/**
     * Gets all file infos.
     *
     * @return the all file infos
     *//*

    public List<FileInfo> getAllFileInfos() {
        return fileDBRepository.findAll().stream().map(file -> {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(file.getFileName());
            fileInfo.setId(file.getId());
            fileInfo.setUrl("/files/" + file.getId());
            fileInfo.setType(file.getType());
            fileInfo.setSize(file.getData().length);
            return fileInfo;
        }).collect(Collectors.toList());
    }

//    public List<FileInfo> getUserFileInfos(User user) {
//
//        return user.getFilesDB().stream().map(file -> {
//            FileInfo fileInfo = new FileInfo();
//            fileInfo.setFileName(file.getFileName());
//            fileInfo.setId(file.getId());
//            fileInfo.setUrl("/files/" + file.getId());
//            fileInfo.setType(file.getType());
//            fileInfo.setSize(file.getData().length);
//            return fileInfo;
//        }).collect(Collectors.toList());
//
//    }



    */
/**
     * Delete file.
     *
     * @param id the id
     *//*

//    public void deleteFile(String id) {
//        Optional<FileDB> file = fileDBRepository.findById(id);
//        if(file.isPresent()) {
//            FileDB fileOk =file.get();
//            fileOk.getUsers().clear();
//            fileOk = fileDBRepository.save(fileOk);
//            fileDBRepository.delete(fileOk);
//        }
//
//
//
//
//    }



    */
/**
     * Método que guarda un fichero de un usuario en base de datos
     *
     * @param file the file
     *
     *//*

//    public void desasociarUserFile(String id, User user)  {
//
//        Optional<FileDB> file = fileDBRepository.findById(id);
//        if(file.isPresent())
//        {
//            user.getFilesDB().remove(file.get());
//            userRepository.save(user);
//        }
//        else
//        {
//            throw new RuntimeException("El fichero no existe");
//        }



 //   }

}
*/
