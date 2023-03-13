package ar.edu.unju.escmi.pv.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.escmi.pv.models.Usuario;

@Repository 
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	@Query(value = "SELECT * FROM Usuario WHERE nacionalidad = ?1", nativeQuery = true)
	 List<Usuario> findByNacionalidad(String nacionalidad);
	
	@Query(value = "SELECT * FROM Usuario WHERE dni = ?1", nativeQuery = true)
	List<Usuario> findByDni(Long dni);
	
	List<Usuario> findByfechaNacimiento(LocalDate fechaNacimiento);
}
