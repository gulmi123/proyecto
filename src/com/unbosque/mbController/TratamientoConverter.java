package com.unbosque.mbController;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
 





import com.unbosque.entidad.Tratamiento;

import com.unbosque.service.TratamientoService;

@FacesConverter("tratamientoConverter")
public class  TratamientoConverter implements Converter {

	@Override
	  public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
			 if(value != null && value.trim().length() > 0) {
		            try {
		            	TratamientoService service = (TratamientoService) fc.getExternalContext().getApplicationMap().get("TratamientoService");
		                return service.getTratamientos().get(Integer.parseInt(value));
		            } catch(NumberFormatException e) {
		                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid tratamientos."));
		            }
		        }
		        else {
		            return null;
		        }
		    }

	
	 public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Tratamiento) object).getId());
        }
        else {
            return null;
        }
    }   

	
	

}
