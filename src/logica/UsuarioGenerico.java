package logica;

public class UsuarioGenerico {
 private String usuario;
 private String contraseña;
 private String tipoUsuario;

	 public UsuarioGenerico(String usuario, String contraseña, String tipoUsuario) {
		 this.tipoUsuario = tipoUsuario;
		 this.usuario = usuario;
		 this.contraseña = contraseña;
	 }
	 
	 
	 public String getUsuario() {
		return usuario;
	}
	 
	 
	public String getContraseña() {
		return contraseña;
	}
	
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public void SetUsuario(String usuario) {
		this.usuario = usuario;
	}
 
}
