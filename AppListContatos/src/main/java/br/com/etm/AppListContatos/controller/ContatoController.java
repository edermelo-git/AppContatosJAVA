package br.com.etm.AppListContatos.controller;

import br.com.etm.AppListContatos.model.Contato;
import br.com.etm.AppListContatos.model.Pessoa;
import br.com.etm.AppListContatos.repository.ContatoRepository;
import br.com.etm.AppListContatos.repository.PessoaRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Operation(summary = "Adiciona um novo Contato a uma Pessoa")
    @PostMapping
    public ResponseEntity<Contato> adicionarContato(@RequestBody Contato contato) {
        Long pessoaId = contato.getPessoa().getId();
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        contato.setPessoa(pessoa);
        Contato novoContato = contatoRepository.save(contato);
        return ResponseEntity.ok(novoContato);
    }

    @Operation(summary = "Retorna os dados de um Contato por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Contato> obterContatoPorId(@PathVariable Long id) {
        return contatoRepository.findById(id)
                .map(contato -> ResponseEntity.ok().body(contato))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Lista todos os Contatos de uma Pessoa")
    @GetMapping("/pessoa/{pessoaId}")
    public List<Contato> listarContatosPorPessoa(@PathVariable Long pessoaId) {
        return contatoRepository.findByPessoaId(pessoaId);
    }

    @Operation(summary = "Atualiza um Contato existente")
    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizarContato(@PathVariable Long id, @RequestBody Contato contatoAtualizado) {
        return contatoRepository.findById(id)
                .map(contato -> {
                    contato.setTipoContato(contatoAtualizado.getTipoContato());
                    contato.setContato(contatoAtualizado.getContato());
                    Contato updated = contatoRepository.save(contato);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Remove um Contato por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarContato(@PathVariable Long id) {
        return contatoRepository.findById(id)
                .map(contato -> {
                    contatoRepository.delete(contato);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
