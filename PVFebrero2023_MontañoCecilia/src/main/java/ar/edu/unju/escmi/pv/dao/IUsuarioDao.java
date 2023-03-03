package ar.edu.unju.escmi.pv.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.escmi.pv.models.Usuario;

@Repository 
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
}
