package com.unbosque.mbController;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
 





import com.unbosque.entidad.Dieta;

import com.unbosque.service.DietaService;

@FacesConverter("dietaConverter")
public class  DietaConverter implements Converter {

	@Override
	  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
			 if(value != null && value.trim().length() > 0) {
		            try {
		                DietaService service = (DietaService) fc.getExternalContext().getApplicationMap().get("DietaService");
		                return service.getDietas().get(Integer.parseInt(value));
		            } catch(NumberFormatException e) {
		                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Dieta."));
		            }
		        }
		        else {
		            return null;
		        }
		    }

	
	 public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Dieta) object).getId());
        }
        else {
            return null;
        }
    }   

	
	

}
