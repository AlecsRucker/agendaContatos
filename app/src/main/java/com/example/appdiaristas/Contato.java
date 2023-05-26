package com.example.appdiaristas;

import java.io.Serializable;

import java.util.List;

import java.io.Serializable;
import java.util.List;

public class Contato implements Serializable {
    private String nome;
    private List<Telefone> telefones;

    public Contato(String nome, List<Telefone> telefones) {
        this.nome = nome;
        this.telefones = telefones;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*public List<Telefone> getTelefones() {
        return telefones;
    }*/

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Telefone getTelefones(int index) {
        if (telefones != null && index >= 0 && index < telefones.size()) {
            return telefones.get(index);
        }
        return null;
    }


}