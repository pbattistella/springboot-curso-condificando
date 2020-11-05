package br.com.codificando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.codificando.repository.UsuarioRepository;
import br.com.codificando.model.Usuario;
import br.com.codificando.repository.PermissaoRepository;

@Controller
public class UsuarioController {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PermissaoRepository permissaoRepository;
	
	@GetMapping("/usuario/add")
	public String addUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		
		return "usuario/add";
	}
	
	@PostMapping("/usuario/save")
	public String saveUsuario(Usuario usuario) {
		
		try {
			if(usuario != null) {
				String senha = usuario.getSenha();
				usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
				
				//Está salvando sem permissão 
				//Caso crie-se regras específicas para
				//uma página ou pasta, não vai funcionar
				//Sendo necessário criar um controle de usuário e permissões
				usuarioRepository.save(usuario);
			}
			
		} catch (Exception e) {
			System.out.println("Mensagem: " + e.getMessage());
			//TODO criar uma página HTML de erro
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/usuario/edit")
	public String editUsuario(Model model) {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("usuario", usuarioRepository.findByLogin(login));
		return "usuario/edit";
	}

}
