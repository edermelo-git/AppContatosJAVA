package br.com.etm.AppListContatos.service;

import br.com.etm.AppListContatos.model.Contato;
import br.com.etm.AppListContatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

  //------------CRUD---CREAT-------------//
    
    public Contato criarContato(Contato contato) {
        return contatoRepository.save(contato);
    }

    //-------OBTER CONTATO POR ID-------//
    
    public Optional<Contato> obterContatoPorId(Long id) {
        return contatoRepository.findById(id);
    }

    //------ LISTA DE CONTAOS POR PESSOA -----///
    
    public List<Contato> listarContatosPorPessoa(Long pessoaId) {
        return contatoRepository.findByPessoaId(pessoaId);
    }

    //---------- ATUALIZAR CONATO-------------//
    
    public Contato atualizarContato(Long id, Contato contatoAtualizado) {
        contatoAtualizado.setId(id);
        return contatoRepository.save(contatoAtualizado);
    }
    
  //--------------------CRUD-DELETE---------------//
    public void deletarContato(Long id) {
        contatoRepository.deleteById(id);
    }
}