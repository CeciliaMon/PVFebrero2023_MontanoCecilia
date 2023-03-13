package ar.edu.unju.escmi.pv.service;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unju.escmi.pv.models.Usuario;

public interface IUsuarioService {
	
	//contrato de implementacion
	public List<Usuario> listar();
	public void guardar(Usuario usuario);
	public void remove(Long dni);
	public Usuario buscarDni(Long dni);
	public List<Usuario> listarHuesped();
	public List<Usuario> findByNacionalidad(String nacionalidad);
	public List<Usuario> findByDni(Long dni);
	public List<Usuario> findByfechaNacimiento(LocalDate fechaNacimiento);
}
