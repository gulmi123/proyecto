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
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.annotation.PostConstruct;
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




















import com.unbosque.entidad.Paciente;
import com.unbosque.service.PacienteService;

@ManagedBean(name = "pacienteMBController")
@ViewScoped
public class PacienteManagedBean implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -833450576444506528L;

	// Spring Customer Service is injected...
	@ManagedProperty(value = "#{PacienteService}")
	PacienteService pacienteService;



	  private  List<String> selectedOptions;
      public List<String> getSelectedOptions() {
              return selectedOptions;
      }


      public void setSelectedOptions(List<String> selectedOptions) {
              this.selectedOptions = selectedOptions;
      }


	List<Paciente> pacienteList;

	
	private String correo;

	private String estado;
	
private Integer id;

	private String identificacion;


	private String nombresApellidos;

	private String progNutricion;

	private String telefono;
	
	  private Paciente seleccionado;
      private List<Paciente> lista;
      private static final Logger logger = Logger.getLogger(PacienteManagedBean.class);
      
      @PostConstruct
      public void init() {
                  lista=new ArrayList<Paciente>();
                
                  seleccionado=new Paciente();
          
                  lista=getPacienteService().getPacientes();
   
   
          }

	
	public void addPaciente() {
		try {



			Paciente paciente= new Paciente();
			
			if(getPacienteService().getPacienteByindetificacion(getIdentificacion())==null){


				paciente.setCorreo(getCorreo());
				
				paciente.setIdentificacion(getIdentificacion());
				paciente.setNombresApellidos(getNombresApellidos());
				paciente.setProgNutricion(getProgNutricion());
				paciente.setTelefono(getTelefono());
				
				paciente.setEstado("A");  


				System.out.println(paciente);
				getPacienteService().addPaciente(paciente);
				System.out.println("hola");
				FacesMessage cuenta = new FacesMessage("Paciente","Se creo correctamente el paciente");
				FacesContext.getCurrentInstance().addMessage(null, cuenta);







			}else{




				FacesMessage cuenta = new FacesMessage("Paciente","El paciente ya existe ");
				FacesContext.getCurrentInstance().addMessage(null, cuenta);
			}



		} catch (DataAccessException e) {
			logger.error("This is Error message", new Exception("Testing"));
		} 

	}

	public void updatePaciente(Paciente paciente) {
		try {

			RequestContext context = RequestContext.getCurrentInstance();
			FacesMessage message = null;



		

			paciente.setCorreo(getCorreo());

			paciente.setNombresApellidos(getNombresApellidos());
			paciente.setProgNutricion(getProgNutricion());
			paciente.setTelefono(getTelefono());
			








			getPacienteService().updatePaciente(paciente);


			FacesMessage msg = new FacesMessage("Modificar","Se modifico exitosamente el paciente");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			logger.error("This is Error message", new Exception("Testing"));
		}

	}

	public void deletePaciente(Paciente paciente) {
		try {


			FacesMessage message = null;

			paciente.setEstado("I");


			getPacienteService().updatePaciente(paciente);


			FacesMessage msg = new FacesMessage("Borrar","Se borro exitosamente el paciente");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			logger.error("This is Error message", new Exception("Testing"));
		}

	}


	
public void reset(){
	
	this.setCorreo("");
	this.setIdentificacion("");
	this.setNombresApellidos("");
	this.setProgNutricion("");
	this.setTelefono("");
	
}


	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Cancelar","No se modifico ninguna columna");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}


	public List<Paciente> getPacientesList() {
		pacienteList = new ArrayList<Paciente>();
		pacienteList.addAll(getPacienteService().getPacientes());
		return pacienteList;
	}
	
	
	
	
	
	
	
	
	

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	public List<Paciente> getPacienteList() {
		return pacienteList;
	}

	public void setPacienteList(List<Paciente> pacienteList) {
		this.pacienteList = pacienteList;
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

	

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombresApellidos() {
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public String getProgNutricion() {
		return progNutricion;
	}

	public void setProgNutricion(String progNutricion) {
		this.progNutricion = progNutricion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Paciente getSeleccionado() {
		return seleccionado;
	}


	public void setSeleccionado(Paciente seleccionado) {
		this.seleccionado = seleccionado;
	}


	public List<Paciente> getLista() {
		return lista;
	}


	public void setLista(List<Paciente> lista) {
		this.lista = lista;
	}
	
	

	
	
	

}
