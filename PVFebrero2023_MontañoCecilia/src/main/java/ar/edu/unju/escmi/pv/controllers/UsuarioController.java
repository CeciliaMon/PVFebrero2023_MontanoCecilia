package ar.edu.unju.escmi.pv.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.escmi.pv.models.Usuario;
import ar.edu.unju.escmi.pv.service.IUsuarioService;

@Controller
public class UsuarioController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IUsuarioService usuarioRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("titulo", "Pagina Principal");
		return "home";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(hasRole("Administrador")) {
			logger.info("Hola usuario : ".concat(auth.getName()).concat(" tienes acceso!"));
		}
		else {
			logger.info("Hola usuario : ".concat(auth.getName()).concat(" no tienes acceso!"));

		}
		model.addAttribute("titulo", "Listado de Usuarios en el Hotel");
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
	public String save(@Valid Usuario usuario, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "FORMULARIO");
			return "formulario";
		}
		usuarioRepository.guardar(usuario);
		return "redirect:/listar";
	}
	
	@GetMapping("/formulario/{dni}")
	public String editar(@PathVariable(value = "dni") Long dni, Model model) {
		Usuario usuario = new Usuario();
		if(dni > 0) {
		usuario = usuarioRepository.findByDni(dni);
		}
		else {
			return "redirect:/listar";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "FORMULARIO EDITAR");
	return "formulario";
	}
	
	@GetMapping("/eliminar/{dni}")
	public String remove(@PathVariable(value = "dni") Long dni) {
		if(dni > 0) {
		usuarioRepository.remove(dni);
		}
		
	return "redirect:/listar";
	}
	
	@ModelAttribute("listaTipoUsuario")
	public List<String> listaTipoUsuario(){
		List<String> tipoUsuarios = new ArrayList<>();
		tipoUsuarios.add("Administrador");
		tipoUsuarios.add("Huesped");
		return tipoUsuarios;
	}
	
	private boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context == null) {
			return false;
		}
		
		Authentication auth = context.getAuthentication();
		
		if(auth == null) {
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		return authorities.contains(new SimpleGrantedAuthority(role)); //contiene el nombre del rol
		
	}
}
