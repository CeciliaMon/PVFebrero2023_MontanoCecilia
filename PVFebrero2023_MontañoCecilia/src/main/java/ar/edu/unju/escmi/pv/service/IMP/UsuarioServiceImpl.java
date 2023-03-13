package ar.edu.unju.escmi.pv.service.IMP;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.escmi.pv.dao.IUsuarioDao;
import ar.edu.unju.escmi.pv.models.Usuario;
import ar.edu.unju.escmi.pv.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioRepository;
	
	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		List<Usuario> lista1 = new ArrayList<>();
		lista1 = (List<Usuario>) usuarioRepository.findAll();
		
		return lista1;
	}

	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		String contr = usuario.getContrasena();
		BCryptPasswordEncoder coder = new BCryptPasswordEncoder(4);
		usuario.setContrasena(coder.encode(contr));
		usuarioRepository.save(usuario);
	}

	@Override
	public void remove(Long dni) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(dni);
	}

	@Override
	public Usuario buscarDni(Long dni) {
		// TODO Auto-generated method stub
		
		return usuarioRepository.findById(dni).orElse(null);//funciona
	}

	@Override
	public List<Usuario> listarHuesped() {
		// TODO Auto-generated method stub
		List<Usuario> usuario = (List<Usuario>) usuarioRepository.findAll();
		List<Usuario> usuario2 = new ArrayList<>();
		
		for(int i=0; i< usuario.size(); i++) {
			if(usuario.get(i).getTipoUsuario().equals("Huesped")) {
				usuario2.add(usuario.get(i));
			}
		}
		
		return usuario2;
	}

	@Override
	public List<Usuario> findByNacionalidad(String nacionalidad) {
		// TODO Auto-generated method stub
		
		return (List<Usuario>) usuarioRepository.findByNacionalidad(nacionalidad);
	}

	@Override
	public List<Usuario> findByDni(Long dni) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioRepository.findByDni(dni);
	}

	@Override
	public List<Usuario> findByfechaNacimiento(LocalDate fechaNacimiento) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioRepository.findByfechaNacimiento(fechaNacimiento);
	}
	
}
