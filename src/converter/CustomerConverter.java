package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Customer;
import services.BusniessServices;

@FacesConverter("customerConverter")
public class CustomerConverter implements Converter {

	private BusniessServices services;

	public CustomerConverter() {
		services = new BusniessServices();
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null && !value.equals("")) {
			int findCustomer = Integer.parseInt(value);
			Customer customer = services.findCustomer(findCustomer);
			return customer;
		} else {
			return "";
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null && !value.equals("")) {
			Customer customer = (Customer) value;
			return String.valueOf(customer.getId());
		} else {
			return "";
		}

	}

	public BusniessServices getServices() {
		return services;
	}

	public void setServices(BusniessServices services) {
		this.services = services;
	}

}
