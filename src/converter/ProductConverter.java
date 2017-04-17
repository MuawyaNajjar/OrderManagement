package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import services.BusniessServices;
import model.Product;

@FacesConverter("productConverter")
public class ProductConverter implements Converter {

	private BusniessServices services;

	public ProductConverter() {
		services = new BusniessServices();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.equals("")) {
			int findProduct = Integer.parseInt(value);
			Product product = services.findProduct(findProduct);
			return product;
		} else {
			return "";
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && !value.equals("")) {
			Product product = (Product) value;
			return String.valueOf(product.getId());
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
