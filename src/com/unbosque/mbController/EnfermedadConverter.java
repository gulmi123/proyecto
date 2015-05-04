package com.unbosque.mbController;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
 





import com.unbosque.entidad.Enfermedad;

import com.unbosque.service.EnfermedadService;

@FacesConverter("enfermedadConverter")
public class  EnfermedadConverter implements Converter {

	@Override
	  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
			 if(value != null && value.trim().length() > 0) {
		            try {
		            	EnfermedadService service = (EnfermedadService) fc.getExternalContext().getApplicationMap().get("EnfermedadService");
		                return service.getEnfermedades().get(Integer.parseInt(value));
		            } catch(NumberFormatException e) {
		                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid enfermedad."));
		            }
		        }
		        else {
		            return null;
		        }
		    }

	
	 public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Enfermedad) object).getId());
        }
        else {
            return null;
        }
    }   

	
	

}
