package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.CustomerOrder;
import services.BusniessServices;

@FacesConverter("orderConverter")
public class OrderConverter implements Converter {
	private BusniessServices services;

	public OrderConverter() {
		services = new BusniessServices();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.equals("")) {
			int findOrder = Integer.parseInt(value);
			CustomerOrder customerOrder = services.findOrder(findOrder);
			return customerOrder;
		} else {
			return "";
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && !value.equals("")) {
			CustomerOrder order = (CustomerOrder) value;
			return String.valueOf(order.getId());
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
