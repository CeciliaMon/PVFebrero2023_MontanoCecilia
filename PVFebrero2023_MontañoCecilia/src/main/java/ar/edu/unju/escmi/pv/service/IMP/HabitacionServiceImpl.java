package ar.edu.unju.escmi.pv.service.IMP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.escmi.pv.dao.IHabitacionDao;
import ar.edu.unju.escmi.pv.models.Habitacion;
import ar.edu.unju.escmi.pv.service.IHabitacionService;
@Service
public class HabitacionServiceImpl implements IHabitacionService{

	@Autowired
	private IHabitacionDao habitacionRepository;
	
	@Override
	public List<Habitacion> listar() {
		// TODO Auto-generated method stub
		List<Habitacion> listaH = new ArrayList<>();
		listaH = (List<Habitacion>) habitacionRepository.findAll();
		return listaH;
	}

	@Override
	public void guardar(Habitacion habitacion) {
		// TODO Auto-generated method stub
		habitacionRepository.save(habitacion);
	}

	@Override
	public Habitacion findByCodigo(Long codigo) {
		// TODO Auto-generated method stub
		return habitacionRepository.findById(codigo).orElse(null);
	}

	@Override
	public void remove(Long codigo) {
		// TODO Auto-generated method stub
		habitacionRepository.deleteById(codigo);
	}

}
