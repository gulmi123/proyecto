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

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;






import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;













import com.unbosque.entidad.Dieta;
import com.unbosque.entidad.Enfermedad;
import com.unbosque.entidad.Paciente;
import com.unbosque.service.DietaService;

@ManagedBean(name = "dietaMBController")
@ViewScoped
public class DietaManagedBean implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -833450576444506528L;

	// Spring Customer Service is injected...
	@ManagedProperty(value = "#{DietaService}")
	DietaService dietaService;



	  private  List<String> selectedOptions;
      public List<String> getSelectedOptions() {
              return selectedOptions;
      }


      public void setSelectedOptions(List<String> selectedOptions) {
              this.selectedOptions = selectedOptions;
      }


	List<Dieta> dietaList;

	private Integer id;
	private String descripcion;

	private String estado;

	private Dieta seleccionado;
    private List<Dieta> lista;
    private static final Logger logger = Logger.getLogger(DietaManagedBean.class);
    
    public void init() {
        lista=new ArrayList<Dieta>();
      
        seleccionado=new Dieta();

        lista=getDietaService().getDietas();


}
    
	
	public void addDieta() {
		try {



			Dieta dieta= new Dieta();
			if(getDietaService().getDietaByDescripcion(getDescripcion())==null){


				dieta.setDescripcion(getDescripcion());
				dieta.setEstado("A");  


				System.out.println(dieta);
				getDietaService().addDieta(dieta);
				System.out.println("hola");
				FacesMessage cuenta = new FacesMessage("Dieta","Se creo correctamente la dieta");
				FacesContext.getCurrentInstance().addMessage(null, cuenta);







			}else{




				FacesMessage cuenta = new FacesMessage("Dieta","la dieta ya existe ");
				FacesContext.getCurrentInstance().addMessage(null, cuenta);
			}



		} catch (DataAccessException e) {
			logger.error("This is Error message", new Exception("Testing"));
		} 

	}

	public void updateDieta(Dieta dieta) {
		try {

			RequestContext context = RequestContext.getCurrentInstance();
			FacesMessage message = null;



			dieta.setDescripcion(getDescripcion());








			getDietaService().updateDieta(dieta);


			FacesMessage msg = new FacesMessage("Modificar","Se modifico exitosamente la dieta");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			logger.error("This is Error message", new Exception("Testing"));
		}

	}

	public void deleteDieta(Dieta dieta) {
		try {


			FacesMessage message = null;

			dieta.setEstado("I");


			getDietaService().updateDieta(dieta);


			FacesMessage msg = new FacesMessage("Borrar","Se borro exitosamente la dieta");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataAccessException e) {
			logger.error("This is Error message", new Exception("Testing"));
		}

	}


	public void reset() {




		this.setDescripcion("");


	}



	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Cancelar","No se modifico ninguna columna");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}


	public List<Dieta> getDietasList() {
		dietaList = new ArrayList<Dieta>();
		dietaList.addAll(getDietaService().getDietas());
		return dietaList;
	}
	

	public List<String> getDietaNombre() {
		dietaList = new ArrayList<Dieta>();
		dietaList.addAll(getDietaService().getDietas());
		List <String > dieta = new ArrayList <String>();
		for (int i = 0; i <dietaList.size(); i++) {
			dieta.add(dietaList.get(i).getDescripcion());
		}
		return dieta;
	}
	

	public DietaService getDietaService() {
		return dietaService;
	}

	public void setDietaService(DietaService dietaService) {
		this.dietaService = dietaService;
	}

	public List<Dieta> getDietaList() {
		return dietaList;
	}

	public void setDietaList(List<Dieta> dietaList) {
		this.dietaList = dietaList;
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


	public Dieta getSeleccionado() {
		return seleccionado;
	}


	public void setSeleccionado(Dieta seleccionado) {
		this.seleccionado = seleccionado;
	}


	public List<Dieta> getLista() {
		return lista;
	}


	public void setLista(List<Dieta> lista) {
		this.lista = lista;
	}



}