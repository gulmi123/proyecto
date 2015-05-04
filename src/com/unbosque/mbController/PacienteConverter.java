package com.unbosque.mbController;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
 





import com.unbosque.entidad.Paciente;

import com.unbosque.service.PacienteService;

@FacesConverter("pacienteConverter")
public class  PacienteConverter implements Converter {

	@Override
	  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
			 if(value != null && value.trim().length() > 0) {
		            try {
		                PacienteService service = (PacienteService) fc.getExternalContext().getApplicationMap().get("PacienteService");
		                return service.getPacientes().get(Integer.parseInt(value));
		            } catch(NumberFormatException e) {
		                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid paciente."));
		            }
		        }
		        else {
		            return null;
		        }
		    }

	
	 public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Paciente) object).getId());
        }
        else {
            return null;
        }
    }   

	
	

}
