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


















import com.unbosque.entidad.Dieta;
import com.unbosque.entidad.Paciente;
import com.unbosque.entidad.Tratamiento;
import com.unbosque.service.TratamientoService;






	@ManagedBean(name = "tratamientoMBController")
	@ViewScoped
	public class TratamientoManagedBean implements Serializable {



		/**
		 * 
		 */
		private static final long serialVersionUID = -833450576444506528L;

		// Spring Customer Service is injected...
		@ManagedProperty(value = "#{TratamientoService}")
		TratamientoService tratamientoService;

		  private  List<String> selectedOptions;
	        public List<String> getSelectedOptions() {
	                return selectedOptions;
	        }
	 
	 
	        public void setSelectedOptions(List<String> selectedOptions) {
	                this.selectedOptions = selectedOptions;
	        }




		List<Tratamiento> tratamientoList;
		

		private String descripcion;

		private String estado;

		private Integer id;
		private Tratamiento seleccionado;
	    private List<Tratamiento> lista;		
	    
		private static final Logger logger = Logger.getLogger(TratamientoManagedBean.class);
	    
	    
	    public void init() {
            lista=new ArrayList<Tratamiento>();
          
            seleccionado=new Tratamiento();
    
            lista=getTratamientoService().getTratamientos();


    }
	    
		
		public void addTratamiento() {
			try {



				Tratamiento tratamiento = new Tratamiento();
				if(getTratamientoService().getTratamientoByDescripcion(getDescripcion())==null){


					tratamiento.setDescripcion(getDescripcion());
					tratamiento.setEstado("A");  


			
					getTratamientoService().addTratamiento(tratamiento);
		
					FacesMessage cuenta = new FacesMessage("Tratamiento","Se creo correctamente el tratamiento");
					FacesContext.getCurrentInstance().addMessage(null, cuenta);







				}else{




					FacesMessage cuenta = new FacesMessage("Tratamiento","El tratamiento ya existe ");
					FacesContext.getCurrentInstance().addMessage(null, cuenta);
				}



			} catch (DataAccessException e) {
				logger.error("This is Error message", new Exception("Testing"));
			} 

		}

		public void updateTratamiento(Tratamiento tratamiento) {
			try {

				RequestContext context = RequestContext.getCurrentInstance();
				FacesMessage message = null;



				tratamiento.setDescripcion(getDescripcion());








				getTratamientoService().updateTratamiento(tratamiento);


				FacesMessage msg = new FacesMessage("Modificar","Se modifico exitosamente el tratamiento");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} catch (DataAccessException e) {
				logger.error("This is Error message", new Exception("Testing"));
			}

		}

		public void deleteTratamiento(Tratamiento tratamiento) {
			try {


				FacesMessage message = null;

				tratamiento.setEstado("I");


				getTratamientoService().updateTratamiento(tratamiento);


				FacesMessage msg = new FacesMessage("Borrar","Se borro exitosamente el tratamiento");
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


		public List<Tratamiento> getTratamientosList() {
			tratamientoList = new ArrayList<Tratamiento>();
			tratamientoList.addAll(getTratamientoService().getTratamientos());
			return tratamientoList;
		}
		
		
		
		
		
		
		
		
		
		
		

		public TratamientoService getTratamientoService() {
			return tratamientoService;
		}

		public void setTratamientoService(TratamientoService tratamientoService) {
			this.tratamientoService = tratamientoService;
		}

		public List<Tratamiento> getTratamientoList() {
			return tratamientoList;
		}

		public void setTratamientoList(List<Tratamiento> tratamientoList) {
			this.tratamientoList = tratamientoList;
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

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}


		public Tratamiento getSeleccionado() {
			return seleccionado;
		}


		public void setSeleccionado(Tratamiento seleccionado) {
			this.seleccionado = seleccionado;
		}


		public List<Tratamiento> getLista() {
			return lista;
		}


		public void setLista(List<Tratamiento> lista) {
			this.lista = lista;
		}

	
	
}
