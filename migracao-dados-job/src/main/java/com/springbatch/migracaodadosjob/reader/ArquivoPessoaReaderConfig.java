package com.springbatch.migracaodadosjob.reader;

import com.springbatch.migracaodadosjob.dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Date;

@Configuration
public class ArquivoPessoaReaderConfig {

    @Bean
    public FlatFileItemReader<Pessoa> arquivoPessoaReader() {
        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("arquivoPessoaReader")
                .resource(new FileSystemResource("files/pessoas.csv"))
                .delimited()
                .names("nome", "email", "dataNascimento", "idade", "id")
                .addComment("--")
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    private FieldSetMapper<Pessoa> fieldSetMapper() {
        return fieldSet -> {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(fieldSet.readString("nome"));
            pessoa.setEmail(fieldSet.readString("email"));
            pessoa.setDataNascimento(new Date((fieldSet.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss").getTime())));
            pessoa.setIdade(fieldSet.readInt("idade"));
            pessoa.setIdade(fieldSet.readInt("id"));
            return pessoa;
        };
    }
}
