package com.unbosque.service;

import java.util.List;

import com.unbosque.dao.TratamientoDAO;
import com.unbosque.entidad.Dieta;
import com.unbosque.entidad.Tratamiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("TratamientoService")
@Transactional(readOnly = true)
public class TratamientoService {

    // CustomerDAO is injected...
    @Autowired
    TratamientoDAO tratamientoDAO;

    @Transactional(readOnly = false)
    public void addTratamiento(Tratamiento tratamiento) {
        getTratamientoDAO().addTratamiento(tratamiento);
    }

    @Transactional(readOnly = false)
    public void deleteTratamiento(Tratamiento tratamiento) {
        getTratamientoDAO().deleteTratamiento(tratamiento);
    }

    @Transactional(readOnly = false)
    public void updateTratamiento(Tratamiento tratamiento) {
        getTratamientoDAO().updateTratamiento(tratamiento);
    }

    public Tratamiento getTratamientoById(int id) {
        return getTratamientoDAO().getTratamientoById(id);
    }

    public List<Tratamiento> getTratamientos() {
        return getTratamientoDAO().getTratamientos();
    }

    public TratamientoDAO getTratamientoDAO() {
        return tratamientoDAO;
    }

    public void setTratamientoDAO(TratamientoDAO tratamientoDAO) {
        this.tratamientoDAO = tratamientoDAO;
    }
    public Tratamiento getTratamientoByDescripcion(String descripcion) {
		return getTratamientoDAO().getTratamientoByDescripcion(descripcion);
	}
}