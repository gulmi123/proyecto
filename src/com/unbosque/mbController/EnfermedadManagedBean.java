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
















import com.unbosque.entidad.Dieta;
import com.unbosque.entidad.Enfermedad;
import com.unbosque.service.EnfermedadService;

@ManagedBean(name = "enfermedadMBController")
@ViewScoped
public class EnfermedadManagedBean implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -833450576444506528L;

	// Spring Customer Service is injected...
	@ManagedProperty(value = "#{EnfermedadService}")
	EnfermedadService enfermedadService;






	List<Enfermedad> enfermedadList;
	private Integer id;
	private String descripcion;

	private String estado;
	
	
	
	public void addEnfermedad() {
		try {



			Enfermedad enfermedad= new Enfermedad();
			if(getEnfermedadService().getEnfermedadByDescripcion(getDescripcion())==null){


				enfermedad.setDescripcion(getDescripcion());
				enfermedad.setEstado("A");  

			

			getEnfermedadService().addEnfermedad(enfermedad);

			FacesMessage cuenta = new FacesMessage("Enfermedad","Se creo correctamente la enfermedad");
			FacesContext.getCurrentInstance().addMessage(null, cuenta);






			
			}else{




				FacesMessage cuenta = new FacesMessage("Enfermedad","la enfermedad ya existe ");
				FacesContext.getCurrentInstance().addMessage(null, cuenta);
			}

			

		} catch (DataAccessException e) {
			e.printStackTrace();
		} 

	}

	
	public void updateEnfermedad(Enfermedad enfermedad) {
		try {

			RequestContext context = RequestContext.getCurrentInstance();
			FacesMessage message = null;



			enfermedad.setDescripcion(getDescripcion());








			getEnfermedadService().updateEnfermedad(enfermedad);


			FacesMessage msg = new FacesMessage("Modificar","Se modifico exitosamente la enfermedad");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	
	public void deleteEnfermedad(Enfermedad enfermedad) {
		try {


			FacesMessage message = null;

			enfermedad.setEstado("I");


			getEnfermedadService().updateEnfermedad(enfermedad);


			FacesMessage msg = new FacesMessage("Borrar","Se borro exitosamente la enfermedad");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	public void reset() {

		

		
		this.setDescripcion("");


	}
	
	
	
	
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Cancelar","No se modifico ninguna columna");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}


	public List<Enfermedad> getEnfermedadesList() {
		enfermedadList = new ArrayList<Enfermedad>();
		enfermedadList.addAll(getEnfermedadService().getEnfermedades());
		return enfermedadList;
	}

	public EnfermedadService getEnfermedadService() {
		return enfermedadService;
	}

	public void setEnfermedadService(EnfermedadService enfermedadService) {
		this.enfermedadService = enfermedadService;
	}

	public List<Enfermedad> getEnfermedadList() {
		return enfermedadList;
	}

	public void setEnfermedadList(List<Enfermedad> enfermedadList) {
		enfermedadList = enfermedadList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
	
	
}
