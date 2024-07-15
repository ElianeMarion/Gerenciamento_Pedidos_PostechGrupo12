package br.com.fiap.techchallenge.batch.configuration;

import br.com.fiap.techchallenge.batch.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
@Slf4j
@EnableFeignClients(basePackages = "br.com.fiap.techchallenge.batch.service")
public class BatchConfiguration {

    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;
    private final List<Product> productList = new ArrayList<>();
    private final RestTemplate restTemplate;
    @Value("${product.api.url}")
    private String apiUrl;


    @Bean
    Job job(Step step){
        return new JobBuilder("job", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step(ItemReader<Product> reader, ItemProcessor<Product, List<Product>> processor, ItemWriter<List<Product>> writer){
        return new StepBuilder("step", jobRepository)
                .<Product,List<Product>>chunk(20, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    FlatFileItemReader<Product> reader(@Value("#{jobParameters['csvFile']}") String csvFile){

        return new FlatFileItemReaderBuilder<Product>()
                .name("reader")
                .resource(new FileSystemResource(csvFile))
                .delimited()
                .names("name", "description", "price", "QuantityStock")
                .targetType(Product.class)
                .build();
    }

    @Bean
    ItemProcessor<Product, List<Product>> processor(){
        return item -> {
            productList.add(item);
            return productList;
        };
    }



    @Bean
    public ItemWriter<List<Product>> writer() {
        return item -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<List<Product>> requestEntity = new HttpEntity<>(productList, headers);
            restTemplate.postForObject(apiUrl, requestEntity, String.class);
        };
    }

    @Bean
    JobLauncher jobLauncherAsync(JobRepository jobRepository) throws Exception {
        var jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }


}
