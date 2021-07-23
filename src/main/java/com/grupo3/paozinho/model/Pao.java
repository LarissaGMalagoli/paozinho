package com.grupo3.paozinho.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pao")
@NoArgsConstructor
@Getter
@Setter
public class Pao {

    @Id
    private String id;
    private String nome;
    private String marca;
    private String tipo;

    public Pao(String nome, String marca, String tipo) {
        this.nome = nome;
        this.marca = marca;
        this.tipo = tipo;
    }
}
