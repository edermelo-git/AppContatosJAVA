package br.com.etm.AppListContatos.repository;

import br.com.etm.AppListContatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	List<Contato> findByPessoaId(Long pessoaId);
}