package com.uninassau.Api_spring.service;

import com.uninassau.Api_spring.dto.CreatePessoaDto;
import com.uninassau.Api_spring.dto.UpdatePessoaDto;
import com.uninassau.Api_spring.entity.Pessoa;
import com.uninassau.Api_spring.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public UUID createPessoa(CreatePessoaDto createPessoaDto) {
        // DTO -> ENTITY
        var entity = new Pessoa(
                UUID.randomUUID(),
                createPessoaDto.cpf(),
                createPessoaDto.nome(),
                createPessoaDto.idade(),
                Instant.now(),
                null
        );

        var pessoaSaved = pessoaRepository.save(entity);

        return pessoaSaved.getPessoaId();
    }

    public Optional<Pessoa> getPessoaById(String pessoaId){
        return pessoaRepository.findById(UUID.fromString(pessoaId));

    }

    public List<Pessoa> listPessoas() {
        return pessoaRepository.findAll();
    }

    public void deleteById(String pessoaId){
        var id = UUID.fromString(pessoaId);
        var pessoaExists = pessoaRepository.existsById(id);

        if(pessoaExists){
            pessoaRepository.deleteById(id);
        }
    }

    public void updatePessoaById(String pessoaId, UpdatePessoaDto updatePessoaDto){
        var id = UUID.fromString(pessoaId);
        var pessoaEntity = pessoaRepository.findById(id);

        if(pessoaEntity.isPresent()){
            var pessoa = pessoaEntity.get();

            if(updatePessoaDto.nome() != null){
                pessoa.setNome(updatePessoaDto.nome());
            }

            pessoaRepository.save(pessoa);
        }
    }
}
