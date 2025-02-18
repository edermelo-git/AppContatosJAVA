package br.com.etm.AppListContatos.controller;

import br.com.etm.AppListContatos.model.Pessoa;
import br.com.etm.AppListContatos.model.Contato;
import br.com.etm.AppListContatos.repository.PessoaRepository;
import br.com.etm.AppListContatos.repository.ContatoRepository;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ApiRestContatosControlle {
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ContatoRepository contatoRepository;

	@Operation(summary = "Lista todas as pessoas")
	@GetMapping("/pessoas/listar")
	public ResponseEntity<List<Pessoa>> listarPessoas() {
		try {
			List<Pessoa> pessoas = pessoaRepository.findAll();
			return ResponseEntity.ok(pessoas);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Lista todos os contatos")
	@GetMapping("/contatos/listar")
	public ResponseEntity<List<Contato>> listarContatos() {
		try {
			List<Contato> contatos = contatoRepository.findAll();
			return ResponseEntity.ok(contatos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Adiciona novas pessoas")
	@PostMapping("/pessoas/cadastrar")
	public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody Pessoa pessoa) {
		try {
			Pessoa pessoaSalva = pessoaRepository.save(pessoa);
			return ResponseEntity.ok(pessoaSalva);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Operation(summary = "Verifica o status da API")
	@GetMapping
	public ResponseEntity<String> getApi() {
		return ResponseEntity.ok("API Java funcionando vamos lá !!! 😁");
	}
}
