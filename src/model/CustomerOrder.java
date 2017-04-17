package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the customer_order database table.
 * 
 */
@Entity
@Table(name = "customer_order")
@NamedQuery(name = "CustomerOrder.findAll", query = "SELECT c FROM CustomerOrder c")
public class CustomerOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2935361717287850252L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "grand_total")
	private Integer grandTotal;

	private String name;

	// bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	// bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;

	@Transient
	private boolean selectedRow;

	public CustomerOrder() {
		customer = new Customer();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGrandTotal() {
		return this.grandTotal;
	}

	public void setGrandTotal(Integer grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setCustomerOrder(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setCustomerOrder(null);

		return orderItem;
	}

	public boolean isSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(boolean selectedRow) {
		this.selectedRow = selectedRow;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerOrder other = (CustomerOrder) obj;
		if (grandTotal == null) {
			if (other.grandTotal != null)
				return false;
		} else if (!grandTotal.equals(other.grandTotal))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (selectedRow != other.selectedRow)
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}

}