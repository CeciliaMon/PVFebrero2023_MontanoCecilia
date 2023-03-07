package ar.edu.unju.escmi.pv.service.IMP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ar.edu.unju.escmi.pv.dao.IUsuarioDao;
import ar.edu.unju.escmi.pv.models.Usuario;

public class LoginUsuarioServiceImpl implements UserDetailsService{
	@Autowired
	IUsuarioDao UsuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
		
		Usuario usuarioEncontrado = UsuarioRepository.findById(Long.parseLong(dni)).orElseThrow(()->new UsernameNotFoundException("Login Invalido"));
		
		
		List<GrantedAuthority> tipos = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioEncontrado.getTipoUsuario());
		tipos.add(grantedAuthority);
		
		UserDetails user = (UserDetails) new User(dni,usuarioEncontrado.getContrasena(),tipos);
		
		return user;
	}
}
