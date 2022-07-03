package com.dseliel.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

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

	private Optional<Filme> optionalFilme;

	@BeforeEach
	void setUp() {

		MockitoAnnotations.openMocks(this);
		startFilme();

	}

	@Test
	void buscarUmaListaDeFilmes() {
		Mockito.when(repo.findAll()).thenReturn(List.of(filme));

		List<Filme> response = service.findAll();

		Assertions.assertNotNull(response);

		Assertions.assertEquals(Filme.class, filme.getClass());

		Assertions.assertEquals(ID, filme.getId());
		Assertions.assertEquals(NOME, filme.getNome());
		Assertions.assertEquals(TIPO, filme.getTipo());

	}

	@Test
	void buscarFilmesPeloId() {

		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(optionalFilme);

		Filme response = service.findById(ID);

		Assertions.assertNotNull(response);

		Assertions.assertEquals(response.getClass(), filme.getClass());

		Assertions.assertEquals(ID, filme.getId());
		Assertions.assertEquals(NOME, filme.getNome());
		Assertions.assertEquals(TIPO, filme.getTipo());

	}

	@Test
	void adicionarFilmeComSucesso() {

		Mockito.when(repo.save(Mockito.any())).thenReturn(filme);

		Filme response = service.adicionar(filme);

		assertNotNull(response);

		assertEquals(response.getClass(), filme.getClass());

		Assertions.assertEquals(ID, filme.getId());
		Assertions.assertEquals(NOME, filme.getNome());
		Assertions.assertEquals(TIPO, filme.getTipo());

	}

	@Test
	void deletarFilmePeloId() {
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(optionalFilme);
		Mockito.doNothing().when(repo).deleteById(Mockito.anyLong());
		service.deletar(ID);

		Mockito.verify(repo, times(1)).deleteById(Mockito.anyLong());

	}

	@Test
	void atualizarFilmeComSucesso() {
		Mockito.when(repo.save(Mockito.any())).thenReturn(filme);
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(optionalFilme);

		Filme response = service.atualizar(ID, filme);

		assertNotNull(response);

		Assertions.assertEquals(ID, filme.getId());
		Assertions.assertEquals(NOME, filme.getNome());
		Assertions.assertEquals(TIPO, filme.getTipo());

	}

	private void startFilme() {
		filme = new Filme(ID, NOME, TIPO);
		optionalFilme = Optional.of(new Filme(ID, NOME, TIPO));
	}

}
