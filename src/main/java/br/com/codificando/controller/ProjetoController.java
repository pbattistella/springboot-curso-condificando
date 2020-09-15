package br.com.codificando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.codificando.model.Projeto;
import br.com.codificando.respository.FuncionarioRespository;
import br.com.codificando.respository.ProjetoRepository;

@Controller
public class ProjetoController {
	
	@Autowired
	ProjetoRepository projetoRepository;
	
	@Autowired
	FuncionarioRespository funcionarioRespository;
	
	@GetMapping("/projeto/list")
	public String listProjeto(Model model) {
		
		model.addAttribute("projetos", projetoRepository.findAll(Sort.by("dataInicio")));
		return "projeto/list";
	}
	
	@GetMapping("/projeto/add")
	public String addProjeto(Model model) {
		
		model.addAttribute("projeto", new Projeto());
		model.addAttribute("funcionarios", funcionarioRespository.findAllByCargo("Gerente"));
		return "projeto/add";
		
	}
	
	@PostMapping("/projeto/save")
	public String saveProjeto(Projeto projeto) {
		try {
			projetoRepository.save(projeto);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return "redirect:/projeto/edit/" + projeto.getId();
	}
	
	@GetMapping("/projeto/edit/{id}")
	public String editProjeto(@PathVariable long id) {
		//TODO: continuo no próximo episódio
		System.out.println("ID criado: " + id);
		return "redirect:projeto/list";//vou mudar aqui
	}

}
