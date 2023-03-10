package ar.edu.unju.escmi.pv.service.IMP;

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
	public Usuario findByDni(Long dni) {
		// TODO Auto-generated method stub
		
		return usuarioRepository.findById(dni).orElse(null);//funciona
	}
	
}
