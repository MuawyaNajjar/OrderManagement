package backing;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import services.BusniessServices;
import model.Customer;

@ManagedBean(name = "customerBacking")
@ViewScoped
public class CustomerBackingBean extends OrderManagementBackingBean {

	private Customer customer;
	private BusniessServices services;
	private List<Customer> customersList;

	public CustomerBackingBean() {
		customer = new Customer();
		services = new BusniessServices();
		customersList = new ArrayList<Customer>();
		customersList = services.getAllCustomer();

	}

	public void add(ActionEvent event) {
		if (isExistCustomer(customer.getName())) {
			services.addCustomer(customer);
			addMessage(CUSTOMER_SUCCESSFUL, FacesMessage.SEVERITY_INFO, "");
		} else {
			addMessage(DUBLICATE_CUSTOMER, FacesMessage.SEVERITY_ERROR, "");
		}
		customersList = services.getAllCustomer();
		cancel();
	}

	public void deleteCustomer(ActionEvent event) {
		if (customer != null && customer.getId() != null) {
			Boolean isDeleted = services.deleteCustomer(customer.getId());
			if (isDeleted.equals(true)) {
				customersList.remove(customer);
				addMessage(CUSTOMER_DELETED, FacesMessage.SEVERITY_INFO, "");
			} else {
				addMessage(CUSTOMER_IS_NOT_DELETED,
						FacesMessage.SEVERITY_ERROR, "");
			}
			cancel();
		}

	}

	public void update(ActionEvent actionEvent) {
		services.addCustomer(customer);
		addMessage(EDIT_CUSTOMER, FacesMessage.SEVERITY_INFO, "");
		customersList = services.getAllCustomer();
		cancel();

	}

	private boolean isExistCustomer(String customerName) {
		boolean value = true;
		for (Customer customer : customersList) {
			if (customer.getName().equalsIgnoreCase(customerName)) {
				value = false;
				break;
			} else {
				continue;
			}
		}
		return value;
	}

	public void cancel() {
		this.customer = new Customer();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BusniessServices getServices() {
		return services;
	}

	public void setServices(BusniessServices services) {
		this.services = services;
	}

	public List<Customer> getCustomersList() {
		return customersList;
	}

	public void setCustomersList(List<Customer> customersList) {
		this.customersList = customersList;
	}

}
