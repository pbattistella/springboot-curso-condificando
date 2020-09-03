package br.com.codificando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.codificando.model.Funcionario;
import br.com.codificando.respository.FuncionarioRespository;

@Controller
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRespository funcionarioRespository;
	
	@GetMapping("/funcionario/list")
	public String listFuncionarios(Model model) {
		
		model.addAttribute("funcionarios", funcionarioRespository.findAll(Sort.by("cargo")));
		
		return "funcionario/list";
	}
	
	@GetMapping("/funcionario/add")
	public String addFuncionario(Model model) {
		
		model.addAttribute("funcionario", new Funcionario());
		return "funcionario/add";
		
	}

}
