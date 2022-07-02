package com.dseliel.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.dseliel.crud.entities.Filme;
import com.dseliel.crud.repository.FilmeRepository;

@SpringBootTest
public class AlunoServiceTest {

	private static final String TIPO = "AÇÃO";

	private static final String NOME = "VINGADORES";

	private static final long ID = 1L;

	@InjectMocks
	private FilmeService service;

	@Mock
	private FilmeRepository repo;

	@Mock
	private Filme filme;

	@BeforeEach
	void setUp() {

		MockitoAnnotations.openMocks(this);
		startFilme();

	}

	@Test
	void whenFindAllReturnAnListOfFilmes() {

		Mockito.when(repo.findAll()).thenReturn(List.of(filme, filme));

		List<Filme> response = service.findAll();

		Assertions.assertNotNull(response);

		assertEquals(2, response.size());
		assertEquals(Filme.class, response.get(0).getClass());

		Assertions.assertEquals(ID, response.get(0).getId());
		Assertions.assertEquals(NOME, filme.getNome());
		Assertions.assertEquals(TIPO, filme.getTipo());

	}

	private void startFilme() {
		filme = new Filme(ID, NOME, TIPO);
	}

}