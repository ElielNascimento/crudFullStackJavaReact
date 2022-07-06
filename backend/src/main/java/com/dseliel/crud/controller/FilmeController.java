package com.dseliel.crud.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dseliel.crud.entities.Filme;
import com.dseliel.crud.service.FilmeService;

@RestController
@RequestMapping(value = "/filmes")
@CrossOrigin("*")
public class FilmeController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FilmeService filmeService;

	@GetMapping
	private List<Filme> findAll() {
		return filmeService.findAll();
	}
	
	@DeleteMapping("/{id}")
	private void delete(@PathVariable Long id) {
		filmeService.deletar(id);
	}
	
	@PostMapping
	private Filme adicionar (@RequestBody Filme obj) {
		return filmeService.adicionar(obj);
	}

}
