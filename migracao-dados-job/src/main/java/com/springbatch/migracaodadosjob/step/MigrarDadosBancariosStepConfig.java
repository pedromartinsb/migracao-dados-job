package com.springbatch.migracaodadosjob.step;

import com.springbatch.migracaodadosjob.dominio.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrarDadosBancariosStepConfig {

    @Bean
    public Step migrarDadosBancariosStep(
            JobRepository jobRepository,
            ItemReader<Pessoa> arquivoDadosBancariosReader,
            ItemWriter<Pessoa> bancoDadosBancariosWriter) {
        return new StepBuilder("migrarDadosBancariosStep", jobRepository)
                .<Pessoa, Pessoa>chunk(10000)
                .reader(arquivoDadosBancariosReader)
                .writer(bancoDadosBancariosWriter)
                .build();
    }
}
