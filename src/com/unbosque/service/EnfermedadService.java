package com.unbosque.service;

import java.util.List;

import com.unbosque.dao.EnfermedadDAO;
import com.unbosque.entidad.Dieta;
import com.unbosque.entidad.Enfermedad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("EnfermedadService")
@Transactional(readOnly = true)
public class EnfermedadService {

    // CustomerDAO is injected...
    @Autowired
    EnfermedadDAO enfermedadDAO;

    @Transactional(readOnly = false)
    public void addEnfermedad(Enfermedad enfermedad) {
        getEnfermedadDAO().addEnfermedad(enfermedad);
    }

    @Transactional(readOnly = false)
    public void deleteEnfermedad(Enfermedad enfermedad) {
        getEnfermedadDAO().deleteEnfermedad(enfermedad);
    }

    @Transactional(readOnly = false)
    public void updateEnfermedad(Enfermedad enfermedad) {
        getEnfermedadDAO().updateEnfermedad(enfermedad);
    }

    public Enfermedad getEnfermedadById(int id) {
        return getEnfermedadDAO().getEnfermedadById(id);
    }

    public List<Enfermedad> getEnfermedades() {
        return getEnfermedadDAO().getEnfermedades();
    }

    public EnfermedadDAO getEnfermedadDAO() {
        return enfermedadDAO;
    }

    public void setEnfermedadDAO(EnfermedadDAO enfermedadDAO) {
        this.enfermedadDAO = enfermedadDAO;
    }
    public Enfermedad getEnfermedadByDescripcion(String descripcion) {
		return getEnfermedadDAO().getEnfermedadByDescripcion(descripcion);
	}
}
