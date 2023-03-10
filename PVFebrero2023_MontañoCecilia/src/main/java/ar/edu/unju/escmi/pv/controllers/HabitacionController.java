package ar.edu.unju.escmi.pv.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/eliminar/{codigo}")
	public String remove(@PathVariable(value = "codigo") Long codigo) {
		if(codigo > 0) {
		habitacionRepository.remove(codigo);
		}
		
	return "redirect:/listarHabitaciones";
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
	
	
}

