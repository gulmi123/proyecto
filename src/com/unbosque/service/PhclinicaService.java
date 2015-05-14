package com.unbosque.service;

import java.util.List;





import com.unbosque.dao.PhclinicaDAO;
import com.unbosque.entidad.Phclinica;
import com.unbosque.entidad.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("PhclinicaService")
@Transactional(readOnly = true)
public class PhclinicaService {

    // CustomerDAO is injected...
	 @Autowired
	 PhclinicaDAO phclinicaDAO;

    @Transactional(readOnly = false)
    public void addPhclinica(Phclinica phclinica) {
        getPhclinicaDAO().addPhclinica(phclinica);
    }

    @Transactional(readOnly = false)
    public void deletePhclinica(Phclinica phclinica) {
        getPhclinicaDAO().deletePhclinica(phclinica);
    }

    @Transactional(readOnly = false)
    public void updatePhclinica(Phclinica phclinica) {
        getPhclinicaDAO().updatePhclinica(phclinica);
    }

    public Phclinica getPhclinicaById(int id) {
        return getPhclinicaDAO().getPhclinicaById(id);
    }

    public List<Phclinica> getPhclinicas() {
        return getPhclinicaDAO().getPhclinicas();
    }

    public PhclinicaDAO getPhclinicaDAO() {
        return phclinicaDAO;
    }

    public void setPhclinicaDAO(PhclinicaDAO phclinicaDAO) {
        this.phclinicaDAO = phclinicaDAO;
    }
	public Phclinica getPhclinicaBypaciente(String paciente) {
		return getPhclinicaDAO().getPhclinicaBypaciente(paciente);
	}
}



