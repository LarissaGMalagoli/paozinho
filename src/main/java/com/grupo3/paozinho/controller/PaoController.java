package com.grupo3.paozinho.controller;

import com.grupo3.paozinho.model.Pao;
import com.grupo3.paozinho.repository.PaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pao")
@RequiredArgsConstructor
public class PaoController {

    private final PaoRepository paoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Pao> get(@PathVariable String id){
        Optional<Pao> optionalPao = paoRepository.findById(id);
        if(optionalPao.isEmpty())
            throw new RuntimeException();
        return ResponseEntity.ok(optionalPao.get());
    }

    @PostMapping()
    public ResponseEntity<Pao> post(@RequestBody PaoForm paoForm, UriComponentsBuilder uriBuilder){
        Pao pao = paoForm.convert();
        paoRepository.save(pao);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(pao.getId()).toUri();

        return ResponseEntity.created(uri).body(pao);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Pao> update(@PathVariable String id, @RequestBody PaoForm paoForm, UriComponentsBuilder uriBuilder){
        Optional<Pao> optionalPao = paoRepository.findById(id);
        if(optionalPao.isEmpty())
            throw new RuntimeException();
        Pao pao = optionalPao.get();
        pao.setMarca(paoForm.getMarca());
        pao.setNome(paoForm.getNome());
        pao.setTipo(paoForm.getTipo());
        URI uri = uriBuilder.path("/{id}").buildAndExpand(pao.getId()).toUri();
        return ResponseEntity.ok(pao);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        Optional<Pao> optionalPao = paoRepository.findById(id);
        if(optionalPao.isEmpty())
            throw new RuntimeException();
        paoRepository.delete(optionalPao.get());
        return ResponseEntity.ok("Deleted: " + id);

    }
}
