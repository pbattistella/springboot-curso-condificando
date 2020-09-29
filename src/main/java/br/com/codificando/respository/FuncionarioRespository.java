package br.com.codificando.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codificando.model.Funcionario;

public interface FuncionarioRespository extends JpaRepository<Funcionario, Long>{
	
	public List<Funcionario> findAllByCargo(String cargo);
	public List<Funcionario> findByCargoNot(String cargo);

}
