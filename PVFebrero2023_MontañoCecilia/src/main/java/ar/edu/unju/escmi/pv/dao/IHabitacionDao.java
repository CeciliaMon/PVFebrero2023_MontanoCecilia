package ar.edu.unju.escmi.pv.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.escmi.pv.models.Habitacion;

@Repository
public interface IHabitacionDao extends CrudRepository<Habitacion, Long>{

}
