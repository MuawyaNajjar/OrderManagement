package backing;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import model.Customer;
import model.CustomerOrder;
import services.BusniessServices;

@ViewScoped
@ManagedBean(name = "orderBacking")
public class OrderBackingBean extends OrderManagementBackingBean {

	private CustomerOrder customerOrder;
	private BusniessServices services;
	private List<CustomerOrder> customerOrderList;
	private List<Customer> customers;

	public OrderBackingBean() {
		customerOrder = new CustomerOrder();
		services = new BusniessServices();
		customerOrderList = new ArrayList<CustomerOrder>();
		customerOrderList = services.getAllOrder();
		customers = new ArrayList<Customer>();
		customers=services.getAllCustomer();

	}

	public void add(ActionEvent event) {
		services.addOrder(customerOrder);
		addMessage(ORDER_SUCCESSFUL, FacesMessage.SEVERITY_INFO, "");
		customerOrderList = services.getAllOrder();
		cancel();
	}

	public void deleteOrder(ActionEvent event) {
		if (customerOrder != null && customerOrder.getId() != null) {
			Boolean isDeleted = services.deleteOrder(customerOrder.getId());
			if (isDeleted.equals(true)) {
				customerOrderList.remove(customerOrder);
				addMessage(ORDER_DELETED, FacesMessage.SEVERITY_INFO, "");
			} else {
				addMessage(ORDER_IS_NOT_DELETED, FacesMessage.SEVERITY_ERROR,
						"");
			}
			cancel();
		}
	}

	public void update(ActionEvent event) {
		services.addOrder(customerOrder);
		addMessage(EDIT_ORDER, FacesMessage.SEVERITY_INFO, "");
		customerOrderList = services.getAllOrder();
		cancel();

	}

	public void cancel() {
		this.customerOrder = new CustomerOrder();
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public List<CustomerOrder> getCustomerOrderList() {
		return customerOrderList;
	}

	public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
		this.customerOrderList = customerOrderList;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
