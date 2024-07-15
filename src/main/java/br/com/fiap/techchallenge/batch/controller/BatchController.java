package br.com.fiap.techchallenge.batch.controller;

import br.com.fiap.techchallenge.batch.configuration.BatchConfiguration;
import br.com.fiap.techchallenge.batch.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("produtos")
public class BatchController {

    private final BatchService batchService;


    @PostMapping("/file")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        batchService.uploadCsvFile(file);
       return "Processamento Iniciado";
    }


}
