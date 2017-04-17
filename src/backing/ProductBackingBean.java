package backing;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import model.Product;
import services.BusniessServices;

@ViewScoped
@ManagedBean(name = "productBacking")
public class ProductBackingBean extends OrderManagementBackingBean {

	private Product product;
	private BusniessServices services;

	private List<Product> productList;

	public ProductBackingBean() {
		product = new Product();
		services = new BusniessServices();
		productList = new ArrayList<Product>();
		productList = services.getAllProduct();

	}

	public void add(ActionEvent event) {
		if (isExistProudct(product.getName())) {
			services.addProduct(product);
			addMessage(PRODUCT_SUCCESSFUL, FacesMessage.SEVERITY_INFO, "");
		} else {
			addMessage(DUBLICATE_Product, FacesMessage.SEVERITY_ERROR, "");
		}

		productList = services.getAllProduct();
		cancel();
	}

	public void deleteProduct(ActionEvent event) {
		if (product != null && product.getId() != null) {
			Boolean isDeleted = services.deleteProduct(product.getId());
			if (isDeleted.equals(true)) {
				productList.remove(product);
				addMessage(PRODUCT_DELETED, FacesMessage.SEVERITY_INFO, "");
			} else {
				addMessage(PRODUCT_IS_NOT_DELETED, FacesMessage.SEVERITY_ERROR,
						"");
			}
			cancel();
		}

	}

	public void update(ActionEvent event) {
		services.addProduct(product);
		addMessage(EDIT_Product, FacesMessage.SEVERITY_INFO, "");
		productList = services.getAllProduct();
		cancel();

	}

	private boolean isExistProudct(String productName) {
		boolean x = true;
		for (Product product : productList) {
			if (product.getName().trim().equalsIgnoreCase(productName.trim())) {
				x = false;
				break;
			} else {
				continue;
			}
		}
		return x;
	}

	public void cancel() {
		this.product = new Product();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public BusniessServices getServices() {
		return services;
	}

	public void setServices(BusniessServices services) {
		this.services = services;
	}

}
