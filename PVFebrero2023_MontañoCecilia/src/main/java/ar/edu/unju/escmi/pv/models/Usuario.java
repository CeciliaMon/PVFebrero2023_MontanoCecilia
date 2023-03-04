package ar.edu.unju.escmi.pv.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	private Long dni;

	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Past
	private Date fechaNacimiento;

	@NotEmpty
	private String contrasena;

	@NotEmpty
	private String nacionalidad;

	/*@NotEmpty
	private List<String> tipoUsuario;*/


	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/*public List<String> getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(List<String> tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}*/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
}
