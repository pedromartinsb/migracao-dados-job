package com.springbatch.migracaodadosjob.step;

import com.springbatch.migracaodadosjob.dominio.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrarPessoaStepConfig {

    @Bean
    public Step migrarPessoaStep(
            JobRepository jobRepository,
            ItemReader<Pessoa> arquivoPessoaReader,
            ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter,
            FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter) {
        return new StepBuilder("migrarPessoaStep", jobRepository)
                .<Pessoa, Pessoa>chunk(10000)
                .reader(arquivoPessoaReader)
                .writer(pessoaClassifierWriter)
                .stream(arquivoPessoasInvalidasWriter)
                .build();
    }
}
