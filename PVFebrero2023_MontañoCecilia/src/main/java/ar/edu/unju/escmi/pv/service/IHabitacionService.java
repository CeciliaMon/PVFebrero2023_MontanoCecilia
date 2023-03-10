package ar.edu.unju.escmi.pv.service;

import java.util.List;

import ar.edu.unju.escmi.pv.models.Habitacion;

public interface IHabitacionService {
	public List<Habitacion> listar();
	public void guardar(Habitacion habitacion);
	public Habitacion findByCodigo(Long codigo);
	public void remove(Long codigo);
}
