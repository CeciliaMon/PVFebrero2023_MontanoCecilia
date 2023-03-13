package ar.edu.unju.escmi.pv.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
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
	
	@GetMapping({"/home", "/"})
	public String home(Model model) {
		model.addAttribute("titulo", "Pagina Principal");
		return "home";
	}
	
	@GetMapping("/error_350")
		public String error(Model model) {
			model.addAttribute("titulo", "Accesso Denegado");
			return "error_350"; 
		}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
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
		usuario = usuarioRepository.buscarDni(dni);
		}
		else {
			return "redirect:/listar";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "FORMULARIO EDITAR");
	return "formularioModificar";
	}
	@PostMapping("/formularioModificar")
	public String modificar(@Valid Usuario usuario, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "FORMULARIO");
			return "formularioModificar";
		}
		usuarioRepository.guardar(usuario);
		return "redirect:/listar";
	}
	
	@GetMapping("/eliminar/{dni}")
	public String remove(@PathVariable(value = "dni") Long dni) {
		if(dni > 0) {
		usuarioRepository.remove(dni);
		}
		
	return "redirect:/listar";
	}
	
	@GetMapping("/listarHuespedes")
	public String listarHuesped(Model model) {
		
		model.addAttribute("titulo", "Listado de Huespedes en el Hotel");
		model.addAttribute("usuarios", usuarioRepository.listarHuesped());
		return "listarHuespedes";
	}
	
	@GetMapping("/listarBuscarNacionalidad")
	public String buscarPorNacionalidad(@Param("nacionalidad") String nacionalidad, Model model) {
		if(nacionalidad==null) {
			nacionalidad="";
		}
		List<Usuario> usuario =  new ArrayList<>();
		if(nacionalidad != "") {
		usuario = usuarioRepository.findByNacionalidad(nacionalidad);
		}
		if(nacionalidad == "") {
			usuario = usuarioRepository.listar();
		}
		model.addAttribute("usuarios", usuario);
		model.addAttribute("titulo", "Busqueda por nacionalidad");
		return "listarBuscarNacionalidad";
	}
	@GetMapping("/listarBuscarDni")
	public String buscarPorDni(@Param("dni") Long dni, Model model) {
		
		List<Usuario> usuario =  new ArrayList<>();
		if(dni != null) {
		usuario = usuarioRepository.findByDni(dni);
		}
		if(dni == null) {
			usuario = usuarioRepository.listar();
		}
		model.addAttribute("usuarios", usuario);
		model.addAttribute("titulo", "Busqueda por dni");
		return "listarBuscarDni";
	}
	@GetMapping("/listarBuscarFecha")
	public String buscarPorFecha(@Param("fecha") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fecha, Model model) {
		
		List<Usuario> usuario =  new ArrayList<>();
		if(fecha != null) {
		usuario = usuarioRepository.findByfechaNacimiento(fecha);
		}
		if(fecha == null) {
			usuario = usuarioRepository.listar();
		}
		model.addAttribute("usuarios", usuario);
		model.addAttribute("titulo", "Busqueda por fecha");
		return "listarBuscarFecha";
	}
	
	@ModelAttribute("listaTipoUsuario")
	public List<String> listaTipoUsuario(){
		List<String> tipoUsuarios = new ArrayList<>();
		tipoUsuarios.add("Administrador");
		tipoUsuarios.add("Huesped");
		return tipoUsuarios;
	}
}
