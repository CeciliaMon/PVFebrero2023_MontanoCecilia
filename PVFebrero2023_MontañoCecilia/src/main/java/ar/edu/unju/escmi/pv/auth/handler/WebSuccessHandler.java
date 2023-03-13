package ar.edu.unju.escmi.pv.auth.handler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@Component
public class WebSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		
	
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		boolean admin=false;
		boolean huesped=false;
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	
		for(GrantedAuthority grantedAuthorities :authorities) {
			if(grantedAuthorities.getAuthority().equals("Administrador")) {
				admin=true;
				break;
			}
			else {
				if(grantedAuthorities.getAuthority().equals("Huesped")) {
					huesped=true;
				}
			}
		}
		if(admin==true) {
			redirectStrategy.sendRedirect(request, response, "/listarHuespedes"); 
		}
		if(huesped==true) {
			redirectStrategy.sendRedirect(request, response, "/listarHabitacionesLibres"); 

		}
				

	}

	
}
