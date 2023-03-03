package ar.edu.unju.escmi.pv.service;

import java.util.List;

import ar.edu.unju.escmi.pv.models.Usuario;

public interface IUsuarioService {
	
	//contrato de implementacion
	public List<Usuario> listar();
	public void guardar(Usuario usuario);
	public void remove(Long dni);
	public Usuario findByDni(Long dni);
	
}
