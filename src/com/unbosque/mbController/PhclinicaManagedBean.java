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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;





















import com.unbosque.entidad.Phclinica;
import com.unbosque.entidad.Dieta;
import com.unbosque.entidad.Paciente;
import com.unbosque.entidad.Tratamiento;
import com.unbosque.entidad.Enfermedad;
import com.unbosque.service.PacienteService;
import com.unbosque.service.DietaService;
import com.unbosque.service.PhclinicaService;
import com.unbosque.service.EnfermedadService;
import com.unbosque.service.TratamientoService;
@ManagedBean(name = "phclinicaMBController")
@ViewScoped
public class PhclinicaManagedBean implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -833450576444506528L;

	// Spring Customer Service is injected...
	@ManagedProperty(value = "#{PhclinicaService}")
	PhclinicaService phclinicaService;

	@ManagedProperty(value = "#{DietaService}")
	DietaService dietaService;

	@ManagedProperty(value = "#{PacienteService}")
	PacienteService pacienteService;
	@ManagedProperty(value = "#{TratamientoService}")
	TratamientoService tratamientoService;
	@ManagedProperty(value = "#{EnfermedadService}")
	EnfermedadService enfermedadService;


	List<Dieta> dietaList;
	List<Paciente> pacienteList;
	List<Phclinica> phclinicaList;
	List<Tratamiento> tratamientoList;
	List<Enfermedad> enfermedadList;

	private String estado;
	private Timestamp fechaHclinica;
	private Integer id;
	private Integer idDieta;
	private Integer idEnfermedad;
	private Integer idPaciente;
	private Integer idTratamiento;
	private Date  fecha = new Date();

	private String dieta;
	private String enfermedad;
	private String paciente;
	private String tratamiento;
	
	private Dieta dietaClase;
	private Tratamiento tratamientoClase;
	private Paciente pacienteClase;
	private Enfermedad enfermedadClase;




	Phclinica phclinica = new Phclinica();
	
	public void addPhClinica(){

        int idDieta=dietaClase.getId();
        
        
        int idTratamiento=tratamientoClase.getId();
        int idPaciente=pacienteClase.getId();
        int idEnfermedad= enfermedadClase.getId();
        
		phclinica.setIdDieta(idDieta);
		phclinica.setFechaHclinica(new Timestamp(fecha.getTime()));
		phclinica.setIdEnfermedad(idEnfermedad);
		phclinica.setIdPaciente(idPaciente);
		phclinica.setIdTratamiento(idTratamiento);
		phclinica.setEstado("A");
		
		
		getPhclinicaService().addPhclinica(phclinica);


	}














	public List<Phclinica> getPhclinicasList() {
		phclinicaList = new ArrayList<Phclinica>();
		phclinicaList.addAll(getPhclinicaService().getPhclinicas());
		return phclinicaList;
	}












	public PhclinicaService getPhclinicaService() {
		return phclinicaService;
	}
	public void setPhclinicaService(PhclinicaService phclinicaService) {
		this.phclinicaService = phclinicaService;
	}
	public List<Phclinica> getPhclinicaList() {
		return phclinicaList;
	}
	public void setPhclinicaList(List<Phclinica> phclinicaList) {
		this.phclinicaList = phclinicaList;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Timestamp getFechaHclinica() {
		return fechaHclinica;
	}
	public void setFechaHclinica(Timestamp fechaHclinica) {
		this.fechaHclinica = fechaHclinica;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdDieta() {
		return idDieta;
	}
	public void setIdDieta(Integer idDieta) {
		this.idDieta = idDieta;
	}
	public Integer getIdEnfermedad() {
		return idEnfermedad;
	}
	public void setIdEnfermedad(Integer idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}
	public Integer getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}
	public Integer getIdTratamiento() {
		return idTratamiento;
	}
	public void setIdTratamiento(Integer idTratamiento) {
		this.idTratamiento = idTratamiento;
	}













	public DietaService getDietaService() {
		return dietaService;
	}













	public void setDietaService(DietaService dietaService) {
		this.dietaService = dietaService;
	}


























	public void setDietaList(List<Dieta> dietaList) {
		this.dietaList = dietaList;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public TratamientoService getTratamientoService() {
		return tratamientoService;
	}



	public void setTratamientoService(TratamientoService tratamientoService) {
		this.tratamientoService = tratamientoService;
	}



	public EnfermedadService getEnfermedadService() {
		return enfermedadService;
	}



	public void setEnfermedadService(EnfermedadService enfermedadService) {
		this.enfermedadService = enfermedadService;
	}



	public List<Tratamiento> getTratamientoList() {
		return tratamientoList;
	}



	public void setTratamientoList(List<Tratamiento> tratamientoList) {
		this.tratamientoList = tratamientoList;
	}



	public List<Enfermedad> getEnfermedadList() {
		return enfermedadList;
	}



	public void setEnfermedadList(List<Enfermedad> enfermedadList) {
		this.enfermedadList = enfermedadList;
	}














	public String getDieta() {
		return dieta;
	}














	public void setDieta(String dieta) {
		this.dieta = dieta;
	}














	public String getEnfermedad() {
		return enfermedad;
	}














	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}














	public String getPaciente() {
		return paciente;
	}














	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}














	public String getTratamiento() {
		return tratamiento;
	}














	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}














	public Phclinica getPhclinica() {
		return phclinica;
	}














	public void setPhclinica(Phclinica phclinica) {
		this.phclinica = phclinica;
	}














	public Dieta getDietaClase() {
		return dietaClase;
	}














	public void setDietaClase(Dieta dietaClase) {
		this.dietaClase = dietaClase;
	}














	public Tratamiento getTratamientoClase() {
		return tratamientoClase;
	}














	public void setTratamientoClase(Tratamiento tratamientoClase) {
		this.tratamientoClase = tratamientoClase;
	}














	public Paciente getPacienteClase() {
		return pacienteClase;
	}














	public void setPacienteClase(Paciente pacienteClase) {
		this.pacienteClase = pacienteClase;
	}














	public Enfermedad getEnfermedadClase() {
		return enfermedadClase;
	}














	public void setEnfermedadClase(Enfermedad enfermedadClase) {
		this.enfermedadClase = enfermedadClase;
	}














	public List<Dieta> getDietaList() {
		return dietaList;
	}












}