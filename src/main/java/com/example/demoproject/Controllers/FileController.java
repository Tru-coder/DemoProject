package com.example.demoproject.Controllers;

import com.example.demoproject.Commons.FileResponse;
import com.example.demoproject.Storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final StorageService storageService;

    @GetMapping("/")
    public String listAllFiles(Model model) {

        model.addAttribute("files", storageService.loadAll().map(
                        path -> ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/download/")
                                .path(path.getFileName().toString())
                                .toUriString())
                .collect(Collectors.toList()));

        return "listFiles";
    }

    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource resource = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
       preProductionUploadFile(file);
       return "redirect:/";
    }

    @PostMapping("/upload-multiple-files")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        preProductionUploadMultipleFiles(files);
        return "redirect:/";
    }


    public List<FileResponse> preProductionUploadMultipleFiles( MultipartFile[] files) {
        return Arrays.stream(files)
                .map(file -> preProductionUploadFile(file))
                .collect(Collectors.toList());
    }
    public FileResponse preProductionUploadFile(MultipartFile file) {
        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();

        return new FileResponse(name, uri, file.getContentType(), file.getSize());

    }
}