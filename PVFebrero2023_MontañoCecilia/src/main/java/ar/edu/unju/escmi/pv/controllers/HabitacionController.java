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

import ar.edu.unju.escmi.pv.models.Habitacion;
import ar.edu.unju.escmi.pv.service.IHabitacionService;

@Controller
public class HabitacionController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IHabitacionService habitacionRepository;
	
	@GetMapping("/listarHabitaciones")
	public String listarHabitaciones(Model model) {
		model.addAttribute("titulo", "Listado de Habitaciones en el Hotel");
		model.addAttribute("habitaciones", habitacionRepository.listar());
	return "listarHabitaciones";
	}
	
	@GetMapping("/formularioHabitaciones")
	public String crear(Model model) {
		Habitacion habitacion = new Habitacion();
		model.addAttribute("habitacion", habitacion);
		model.addAttribute("titulo", "FORMULARIO DE HABITACIONES");
	return "formularioHabitaciones";
	}
	
	@PostMapping("/formularioHabitaciones")
	public String save(@Valid Habitacion habitacion, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "FORMULARIO DE HABITACIONES");
			return "formularioHabitaciones";
		}
		habitacionRepository.guardar(habitacion);
		return "redirect:/listarHabitaciones";
	}
	
	@GetMapping("/formularioHabitaciones/{dni}")
	public String editar(@PathVariable(value = "codigo") Long codigo, Model model) {
		Habitacion habitacion = new Habitacion();
		if(codigo > 0) {
		habitacion = habitacionRepository.findByCodigo(codigo);
		}
		else {
			return "redirect:/listarHabitaciones";
		}
		model.addAttribute("habitacion", habitacion);
		model.addAttribute("titulo", "FORMULARIO EDITAR HABITACIONES");
	return "formularioHabitaciones";
	}
	
	@GetMapping("/eliminarHabitacion/{codigo}")
	public String remove(@PathVariable(value = "codigo") Long codigo) {
		if(codigo > 0) {
		habitacionRepository.remove(codigo);
		}
		
	return "redirect:/listarHabitaciones";
	}
	
	@GetMapping("/listarHabitacionesLibres")
	public String listarLibres(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(hasRole("Huesped")) {
			logger.info("Hola ".concat(auth.getName()).concat(" tienes acceso!"));
		}
		else {
			logger.info("Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
		}
		model.addAttribute("titulo", "Listado de Habitaciones disponibles:");
		model.addAttribute("habitaciones", habitacionRepository.buscarHabitacionLibre());
		return "listarHabitacionesLibres";
	}
	
	@ModelAttribute("listaTipo")
	public List<String> listaTipo(){
		List<String> tipos = new ArrayList<>();
		tipos.add("Regular");
		tipos.add("Premium");
		return tipos;
	}
	@ModelAttribute("listaCamas")
	public List<String> listaCama(){
		List<String> camas = new ArrayList<>();
		camas.add("Simple");
		camas.add("Doble");
		camas.add("Triple");
		return camas;
	}
	@ModelAttribute("listaEstado")
	public List<String> listaEstado(){
		List<String> estados = new ArrayList<>();
		estados.add("Ocupado");
		estados.add("Libre");
		return estados;
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

