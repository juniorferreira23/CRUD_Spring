package com.uninassau.Api_spring.controller;

import com.uninassau.Api_spring.dto.CreatePessoaDto;
import com.uninassau.Api_spring.dto.UpdatePessoaDto;
import com.uninassau.Api_spring.entity.Pessoa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uninassau.Api_spring.service.PessoaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/pessoas")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody CreatePessoaDto createPessoaDto) {
        var pessoaId = pessoaService.createPessoa(createPessoaDto);
        return ResponseEntity.created(URI.create("/V1/pessoas/" + pessoaId.toString())).build();
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable("pessoaId") String pessoaId) {
        var pessoa = pessoaService.getPessoaById(pessoaId);
        System.out.println(pessoa);
        if(pessoa.isPresent()){
            return ResponseEntity.ok(pessoa.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listPessoas() {
        var pessoas = pessoaService.listPessoas();

        return ResponseEntity.ok(pessoas);
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Void> deleteById(@PathVariable("pessoaId") String pessoaId){
        pessoaService.deleteById(pessoaId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{pessoaId}")
    public  ResponseEntity<Void> updatePessoaById(@PathVariable("pessoaId") String pessoaId,
                                                  @RequestBody UpdatePessoaDto updatePessoaDto){
        pessoaService.updatePessoaById(pessoaId, updatePessoaDto);
        return ResponseEntity.noContent().build();
    }
}
