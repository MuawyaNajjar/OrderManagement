package backing;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import services.BusniessServices;
import model.CustomerOrder;
import model.OrderItem;
import model.Product;

@ManagedBean(name = "orderItemBacking")
@ViewScoped
public class OrderItemBackingBean extends OrderManagementBackingBean {

	private OrderItem orderItem;
	private List<OrderItem> orderItemList;
	private BusniessServices services;
	private List<Product> productsList;
	private List<CustomerOrder> orders;

	public OrderItemBackingBean() {
		orderItem = new OrderItem();
		orderItemList = new ArrayList<OrderItem>();
		services = new BusniessServices();
		orderItemList = services.getAllItem();
		productsList = new ArrayList<Product>();
		productsList = services.getAllProduct();
		orders = new ArrayList<CustomerOrder>();
		orders = services.getAllOrder();

	}

	public void addItems(ActionEvent event) {
		if (isExistItem(orderItem.getProduct().getId(), orderItem
				.getCustomerOrder().getId(), orderItem.getStatus())) {
			services.addItem(orderItem);
			addMessage(ITEM_SUCCESSFUL, FacesMessage.SEVERITY_INFO, "");
		} else {
			addMessage(DUBLICATE_ITEM, FacesMessage.SEVERITY_ERROR, "");
		}
		orderItemList = services.getAllItem();
		cancel();
	}

	public void deleteItem(ActionEvent event) {
		if (orderItem != null && !orderItem.getId().equals("")) {
			Boolean isDeleted = services.deleteItem(orderItem.getId());
			if (isDeleted.equals(true)) {
				orderItemList.remove(orderItem);
				addMessage(ITEM_DELETED, FacesMessage.SEVERITY_INFO, "");
			} else {
				addMessage(ITEM_IS_NOT_DELETED, FacesMessage.SEVERITY_ERROR, "");
			}
			cancel();

		}

	}

	public void update(ActionEvent event) {
		services.addItem(orderItem);
		addMessage(EDIT_ITEM, FacesMessage.SEVERITY_INFO, "");
		orderItemList = services.getAllItem();
		cancel();

	}

	private boolean isExistItem(Integer productId, Integer orderId,
			String status) {
		boolean value = true;
		for (OrderItem orderItem : orderItemList) {
			if ((orderItem.getProduct().getId().equals(productId) && orderItem
					.getCustomerOrder().getId().equals(orderId))
					&& orderItem.getStatus().equalsIgnoreCase(status)) {
				value = false;
				break;
			} else {
				continue;
			}
		}
		return value;
	}

	public void cancel() {
		this.orderItem = new OrderItem();
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public BusniessServices getServices() {
		return services;
	}

	public void setServices(BusniessServices services) {
		this.services = services;
	}

	public List<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}

	public List<CustomerOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}

}
