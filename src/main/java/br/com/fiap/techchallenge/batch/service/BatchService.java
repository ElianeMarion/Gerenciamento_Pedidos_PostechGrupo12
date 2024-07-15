package br.com.fiap.techchallenge.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class BatchService {

    private final Path fileStorageLocation;
    private final JobLauncher jobLauncher;
    private final Job job;

    public BatchService(@Value("${file.upload-dir}") String fileUploadDir, @Qualifier("jobLauncherAsync") JobLauncher jobLauncher, Job job){
        this.fileStorageLocation = Paths.get(fileUploadDir);
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void uploadCsvFile(MultipartFile file) throws Exception {

        Path tempDir = Paths.get(System.getProperty("user.dir"), "temp");
        Files.createDirectories(tempDir);
        Path tempFile = tempDir.resolve(Objects.requireNonNull(file.getOriginalFilename()));
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("csvFile", tempFile.toString())
                .toJobParameters();
        jobLauncher.run(job, jobParameters);
    }
}
