package com.dseliel.crud.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dseliel.crud.entities.Filme;
import com.dseliel.crud.repository.FilmeRepository;

@Service
public class FilmeService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	public FilmeRepository repo;

	public List<Filme> findAll() {
		return repo.findAll();
	}

	public Filme findById(Long id) {
		return repo.findById(id).get();
	}

	public Filme adicionar(Filme obj) {
		return repo.save(obj);

	}

	public void deletar(Long id) {
		repo.deleteById(id);
	}

	public Filme atualizar(Long id, Filme filme) {

		Filme newFilme = repo.findById(id).get();

		newFilme.setNome(filme.getNome());
		newFilme.setCategoria(filme.getCategoria());

		return repo.save(filme);

	}

}
