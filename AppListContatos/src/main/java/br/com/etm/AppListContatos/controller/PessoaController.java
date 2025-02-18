package br.com.etm.AppListContatos.controller;

import br.com.etm.AppListContatos.dto.PessoaMalaDiretaDTO;
import br.com.etm.AppListContatos.model.Pessoa;
import br.com.etm.AppListContatos.repository.PessoaRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Operation(summary = "Cria uma nova Pessoa")
    @PostMapping
    public Pessoa criarPessoa(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Operation(summary = "Retorna os dados de uma Pessoa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(@PathVariable Long id) {
        return pessoaRepository.findById(id)
                .map(pessoa -> ResponseEntity.ok().body(pessoa))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Retorna os dados de uma Pessoa por ID para mala direta")
    @GetMapping("/maladireta/{id}")
    public ResponseEntity<PessoaMalaDiretaDTO> obterPessoaParaMalaDireta(@PathVariable Long id) {
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    String malaDireta = String.format("%s – CEP: %s – %s/%s",
                            pessoa.getEndereco(),
                            pessoa.getCep(),
                            pessoa.getCidade(),
                            pessoa.getUf());
                    PessoaMalaDiretaDTO dto = new PessoaMalaDiretaDTO(pessoa.getId(), pessoa.getNome(), malaDireta);
                    return ResponseEntity.ok().body(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Lista todas as Pessoas")
    @GetMapping
    public List<Pessoa> listarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    @Operation(summary = "Atualiza uma Pessoa existente")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    pessoa.setNome(pessoaAtualizada.getNome());
                    pessoa.setEndereco(pessoaAtualizada.getEndereco());
                    pessoa.setCep(pessoaAtualizada.getCep());
                    pessoa.setCidade(pessoaAtualizada.getCidade());
                    pessoa.setUf(pessoaAtualizada.getUf());
                    Pessoa updated = pessoaRepository.save(pessoa);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove uma Pessoa por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@PathVariable Long id) {
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    pessoaRepository.delete(pessoa);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}