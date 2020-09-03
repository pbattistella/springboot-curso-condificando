package br.com.codificando.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codificando.model.Funcionario;

public interface FuncionarioRespository extends JpaRepository<Funcionario, Long>{

}
