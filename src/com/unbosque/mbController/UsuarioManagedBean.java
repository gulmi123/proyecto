package com.unbosque.mbController;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;









import com.unbosque.entidad.Usuario;
import com.unbosque.service.UsuarioService;
import com.unbosque.mbController.CifrarClave;
import com.unbosque.entidad.Parametro;
import com.unbosque.service.ParametroService;

@ManagedBean(name = "usuarioMBController")
@ViewScoped
public class UsuarioManagedBean implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -833450576444506528L;

	// Spring Customer Service is injected...
	@ManagedProperty(value = "#{UsuarioService}")
	UsuarioService usuarioService;


	@ManagedProperty(value = "#{ParametroService}")
	ParametroService parametroService;




	List<Usuario> usuarioList;
	List<Parametro> parametroList;

	private Integer id;
	private String apellidosNombres;
	private String correo;
	private String estado;
	private Timestamp fechaClave;
	private Timestamp fechaCreacion;

	private String login;
	private String password;
	private String tipoUsuario;
	private Date  fecha = new Date();
	private boolean logeado = false;

	private int intentos;
	CifrarClave clave = new CifrarClave();
	private static final Logger logger = Logger.getLogger(UsuarioManagedBean.class);



	public void addUsuario() {
		try {



			Usuario usuario= new Usuario();
			if(getUsuarioService().getUsuarioByLoginSolo(getLogin())==null){


				usuario.setApellidosNombres(getApellidosNombres());
				usuario.setCorreo(getCorreo());
				usuario.setEstado("A");  
				usuario.setFechaClave(new Timestamp(fecha.getTime()));
				usuario.setFechaCreacion(new Timestamp(fecha.getTime()));

				usuario.setLogin(getLogin());
				usuario.setPassword(clave.cifradoClave(getPassword()));
				usuario.setTipoUsuario("U");

				getUsuarioService().addUsuario(usuario);

				FacesMessage cuenta = new FacesMessage("Cuenta","Se creo correctamente el usuario");
				FacesContext.getCurrentInstance().addMessage(null, cuenta);



				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");

				Session session = Session.getDefaultInstance(props,
						new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("gulmineitor@gmail.com","ni753951852456");
					}
				});

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("gulmineitor@gmail.com"));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(getCorreo()));
					message.setSubject("Su cuenta se a registrado exitosamente al proyecto dietas");
					message.setText("Su registro se completo con exito , su usario es :"+" "+getLogin()+" "+"y su password es:"+" "+getPassword());

					Transport.send(message);

					System.out.println("Done");

					FacesMessage msg = new FacesMessage("Correo","Se envio un correo a su email");
					FacesContext.getCurrentInstance().addMessage(null, msg);

				} catch (MessagingException e) {
					logger.error("This is Error message", new Exception("Testing"));
				}
			}else{



				FacesMessage cuenta = new FacesMessage("Cuenta","El usario ya existe ");
				FacesContext.getCurrentInstance().addMessage(null, cuenta);
			}



		} catch (DataAccessException e) {
			logger.error("This is Error message", new Exception("Testing"));
		}

	}


	public void addUsuarioMad(){

		try{

			Usuario cla = getUsuarioService().getUsuarioByPassword(clave.cifradoClave(getPassword()));




			cla.setCorreo(getCorreo());


			cla.setPassword(clave.cifradoClave(getPassword()));
			cla.setFechaClave(new Timestamp(fecha.getTime()));

			getUsuarioService().updateUsuario(cla);
			FacesMessage msg = new FacesMessage("Usuario","Sus datos se actualizaron con exito");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}catch(DataAccessException e ){
			logger.error("This is Error message", new Exception("Testing"));
		}
	}
	public void addcontraseña(){
		try{
			Usuario cla = getUsuarioService().getUsuarioByPassword(clave.cifradoClave(getPassword()));







			cla.setPassword(clave.cifradoClave(getPassword()));
			cla.setFechaClave(new Timestamp(fecha.getTime()));

			getUsuarioService().updateUsuario(cla);
			FacesMessage msg = new FacesMessage("Usuario","La contraseña se actualizo con exito");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}catch(DataAccessException e ){
			logger.error("This is Error message", new Exception("Testing"));
		}
	}

	public void loginVali(ActionEvent actionEvent) {
		RequestContext context = RequestContext.getCurrentInstance();

		ExternalContext context1 = FacesContext.getCurrentInstance().getExternalContext();


		Timestamp fechaclave = getUsuarioService().getUsuarioByLoginSolo(getLogin()).getFechaClave();

		Parametro parametro = new Parametro();

		String valor = getParametroService().getParametroById(1).getValor();
		int valorParametro=Integer.parseInt(valor);



		long diferencia = ( fecha.getTime() - fechaclave.getTime() );


		Usuario usu = getUsuarioService().getUsuarioByLoginSolo(getLogin());



		try{


			if(getUsuarioService().getUsuarioByLogin(getLogin(),clave.cifradoClave(getPassword()),"A","A")!=null){
				FacesMessage msg = new FacesMessage("Administrador","Bienvenido Admin");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				FacesContext contex = FacesContext.getCurrentInstance();
				contex.getExternalContext().redirect("http://localhost:8080/ProyectoDietas/admiHome.xhtml");
			}



			else {



				if(diferencia>=valorParametro){
					FacesContext contex = FacesContext.getCurrentInstance();
					contex.getExternalContext().redirect("http://localhost:8080/ProyectoDietas/cambioClave.xhtml");
					FacesMessage msg = new FacesMessage(getLogin()+" "+" por favor cambie su contraseña");
					FacesContext.getCurrentInstance().addMessage(null, msg);

				}else{


					if(getUsuarioService().getUsuarioByLogin(getLogin(),clave.cifradoClave(getPassword()),"U","A")!=null ){

						FacesContext contex = FacesContext.getCurrentInstance();

						FacesMessage msg = new FacesMessage("Usuario "+getLogin(),"Bienvenido "+getLogin());
						FacesContext.getCurrentInstance().addMessage(null, msg);
						contex.getExternalContext().redirect("http://localhost:8080/ProyectoDietas/MAD.xhtml");


						usu.setIntentos(0);
						getUsuarioService().updateUsuario(usu);

					} else{


						int xd= getUsuarioService().getUsuarioByLoginSolo(getLogin()).getIntentos();

						if(xd==3){

							usu.setEstado("B");
							getUsuarioService().updateUsuario(usu);

							FacesMessage msg = new FacesMessage("Error","Usuario bloqueado, el numeros de intentos de ingreso fallidos es:"+""+xd);
							FacesContext.getCurrentInstance().addMessage(null, msg);

						}else{


							usu.setIntentos(xd+1);
							getUsuarioService().updateUsuario(usu);

							FacesMessage msg = new FacesMessage("Error","Usuario Incorrecto");
							FacesContext.getCurrentInstance().addMessage(null, msg);


						}


					}
				}
			}

		}
		catch (IOException e) {
			logger.error("This is Error message", new Exception("Testing"));
		}


	}







	public void updateUsuario(Usuario usuario) {
		try {

			RequestContext context = RequestContext.getCurrentInstance();
			FacesMessage message = null;



			usuario.setApellidosNombres(getApellidosNombres());
			usuario.setCorreo(getCorreo());
			usuario.setEstado(getEstado());  



			usuario.setPassword(getPassword()); 


			getUsuarioService().updateUsuario(usuario);


			FacesMessage msg = new FacesMessage("Modificar","Se modifico exitosamente el usario");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			logger.error("This is Error message", new Exception("Testing"));
		}

	}
	public void deleteUsuario(Usuario usuario) {
		try {


			FacesMessage message = null;

			usuario.setEstado("I");


			getUsuarioService().updateUsuario(usuario);


			FacesMessage msg = new FacesMessage("Borrar","Se borro exitosamente el usuario");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			logger.error("This is Error message", new Exception("Testing"));
		}

	}



	public void reset() {

		this.setApellidosNombres("");
		this.setCorreo("");

		this.setLogin("");
		this.setPassword("");


	}






	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Cancelar","No se modifico ninguna columna");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}



	public List<Usuario> getUsuariosList() {
		usuarioList = new ArrayList<Usuario>();
		usuarioList.addAll(getUsuarioService().getUsuarios());
		return usuarioList;
	}






	public UsuarioService getUsuarioService() {
		return usuarioService;
	}





	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}





	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}




	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public String getApellidosNombres() {
		return apellidosNombres;
	}





	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}



	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}


	public String getCorreo() {
		return correo;
	}





	public void setCorreo(String correo) {
		this.correo = correo;
	}





	public String getEstado() {
		return estado;
	}





	public void setEstado(String estado) {
		this.estado = estado;
	}





	public Timestamp getFechaClave() {
		return fechaClave;
	}





	public void setFechaClave(Timestamp fechaClave) {
		this.fechaClave = fechaClave;
	}





	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}





	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}










	public String getLogin() {
		return login;
	}





	public void setLogin(String login) {
		this.login = login;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public String getTipoUsuario() {
		return tipoUsuario;
	}





	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}





	public ParametroService getParametroService() {
		return parametroService;
	}





	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}





	public List<Parametro> getParametroList() {
		return parametroList;
	}





	public void setParametroList(List<Parametro> parametroList) {
		this.parametroList = parametroList;
	}





	public int getIntentos() {
		return intentos;
	}





	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}









}