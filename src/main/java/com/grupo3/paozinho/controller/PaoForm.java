package com.grupo3.paozinho.controller;

import com.grupo3.paozinho.model.Pao;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaoForm {

    private String nome;
    private String marca;
    private String tipo;

    public PaoForm(String nome, String marca, String tipo) {
        this.nome = nome;
        this.marca = marca;
        this.tipo = tipo;
    }

    public Pao convert(){
        return new Pao(this.nome, this.marca, this.tipo);
    }
}
