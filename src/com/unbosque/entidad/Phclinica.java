package com.unbosque.entidad;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the phclinica database table.
 * 
 */
@Entity
@NamedQuery(name="Phclinica.findAll", query="SELECT p FROM Phclinica p")
public class Phclinica implements Serializable {
	private static final long serialVersionUID = 1L;

	private String estado;

	@Column(name="fecha_hclinica")
	private Timestamp fechaHclinica;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="dieta")
	private String dieta;

	@Column(name="enfermedad")
	private String enfermedad;

	@Column(name="paciente")
	private String paciente;

	@Column(name="tratamiento")
	private String tratamiento;

	public Phclinica() {
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaHclinica() {
		return this.fechaHclinica;
	}

	public void setFechaHclinica(Timestamp fechaHclinica) {
		this.fechaHclinica = fechaHclinica;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	

}