package ar.edu.unju.escmi.pv.controllers;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.escmi.pv.service.IUsuarioService;

@Controller
public class MainController {
	
	@Autowired
	private IUsuarioService usuarioRepository;
	
	@GetMapping("/login")
	public String ingresar(@RequestParam(value = "error", required=false) String error, @RequestParam(value = "logout", required=false) String logout, Principal principal, RedirectAttributes flash, Model model) {
	
		model.addAttribute("titulo", "Inicio de Sesion");
		
		if(principal != null) {
			flash.addFlashAttribute("info", "Ya se ha iniciado sesion anteriormente");
			return "redirect:/home";
		}
	
	if(error != null) {
		model.addAttribute("error", "Error en el nombre de usuario o contraseña incorrecta, por favor, intentelo de nuevo...");
	}
	
	if(logout != null) {
		model.addAttribute("success", "Cierre de sesion");
	}
		
		return "login";
	}
}
