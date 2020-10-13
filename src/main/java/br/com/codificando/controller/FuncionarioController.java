package br.com.codificando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.codificando.model.Funcionario;
import br.com.codificando.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRespository;
	
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
	
	@PostMapping("/funcionario/save")
	public String saveFuncionario(Funcionario funcionario) {
		try {
			if (funcionario != null) {
				funcionarioRespository.save(funcionario);
			}	
		} catch (Exception e) {
			System.out.println("Erro ao salvar: " + e.getMessage());
		}
		
		return "redirect:/funcionario/view/" + funcionario.getId() + "/" + true;
		
	}
	
	@GetMapping("/funcionario/view/{id}/{salvo}")
	public String viewFuncionario(@PathVariable long id, @PathVariable boolean salvo, Model model) {
		model.addAttribute("funcionario", funcionarioRespository.findById(id));
		model.addAttribute("salvo", salvo);
		return "funcionario/view";
	}
	
	@GetMapping("/funcionario/edit/{id}")
	public String editFuncionario(@PathVariable long id, Model model) {
		
		model.addAttribute("funcionario", funcionarioRespository.findById(id));
		return "funcionario/edit";
		
	}
	
	@GetMapping("/funcionario/delete/{id}")
	public String deleteFuncionario(@PathVariable long id) {
		try {
			funcionarioRespository.deleteById(id);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return "redirect:/funcionario/list";
	}
	

}
