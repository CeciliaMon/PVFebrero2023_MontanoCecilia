package ar.edu.unju.escmi.pv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ar.edu.unju.escmi.pv.service.IMP.LoginUsuarioServiceImpl;
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/","/images/**", "/css/**", "/js/**", "/formulario/**", "/formularioHabitaciones/**", "/listarHabitaciones/**").permitAll()
		.antMatchers("/formulario/**").hasAnyAuthority("Administrador")
		.antMatchers("/listar/**").hasAnyAuthority("Administrador")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.usernameParameter("dni")
			.passwordParameter("password")
			.permitAll()
		.and()
		.logout()
		.permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_350");
		//.logoutSuccessUrl("/login?logout");
	}
	
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		
		return bCryptPasswordEncoder;
	}
	
	@Autowired
	LoginUsuarioServiceImpl userDetailsService;
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userDetailsService);
		/*PasswordEncoder encoder = passwordEncoder();
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
		auth.inMemoryAuthentication().withUser(users.username("34").password("234").roles("Administrador"));*/
	}
		
	
}
