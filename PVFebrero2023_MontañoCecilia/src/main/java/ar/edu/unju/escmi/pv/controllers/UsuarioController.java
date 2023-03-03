package ar.edu.unju.escmi.pv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.escmi.pv.models.Usuario;
import ar.edu.unju.escmi.pv.service.IUsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioRepository;
	
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de Uusuarios en el Hotel");
		model.addAttribute("usuarios", usuarioRepository.listar());
	return "listar";
	}
	
	@GetMapping("/formulario")
	public String crear(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "FORMULARIO");
	return "formulario";
	}
	
	@PostMapping("/formulario")
	public String save(Usuario usuario, Model model) {
		usuarioRepository.guardar(usuario);
		return "redirect:/listar";
	}
	
	@GetMapping("/form/{dni}")
	public String editar(@PathVariable(value = "dni") Long dni, Model model) {
		Usuario usuario = new Usuario();
		usuario = usuarioRepository.findByDni(dni);
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "FORMULARIO EDITAR");
	return "formulario";
	}
	
	@GetMapping("/eliminar/{dni}")
	public String remove(@PathVariable(value = "dni") Long dni) {
		
		usuarioRepository.remove(dni);
	return "redirect:/listar";
	}
}
