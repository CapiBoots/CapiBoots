package com.example.CapiBoots.servicios;

import com.example.CapiBoots.modelos.Categorias;
import com.example.CapiBoots.repositorios.CategoriasRepositorios;

import java.util.List;

public class CategoriasSrvcImpls implements ifxCategoriasSrvc {
    public CategoriasRepositorios catrepo;
    @Override
    public Categorias buscaId(Long id) {
        return catrepo.findById(id).orElse(null);
    }
    @Override
    public List<Categorias> listaCat() {
        return catrepo.findAll();
    }
}
