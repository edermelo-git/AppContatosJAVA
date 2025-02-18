package br.com.etm.AppListContatos.repository;

import br.com.etm.AppListContatos.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}