package br.com.etm.AppListContatos.service;

import br.com.etm.AppListContatos.model.Pessoa;
import br.com.etm.AppListContatos.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

  //------------CRUD---CREAT-------------//
    
    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    //-----------OBTER PESSOA POR ID---------//
    
    public Optional<Pessoa> obterPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    //-----------LISTA DE TODAS AS PESSOAS -------//
    
    public List<Pessoa> listarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    //---------- ATUALIZAR PESSOA-------------------//
    
    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        pessoaAtualizada.setId(id);
        return pessoaRepository.save(pessoaAtualizada);
    }
  
    //--------------------CRUD-DELETE---------------//
    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}