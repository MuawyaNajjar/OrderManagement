package model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the order_item database table.
 * 
 */
@Entity
@Table(name = "order_item")
@NamedQuery(name = "OrderItem.findAll", query = "SELECT o FROM OrderItem o")
public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -443890230858831355L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer quantity;

	private String status;

	// bi-directional many-to-one association to CustomerOrder
	@ManyToOne
	@JoinColumn(name = "order_id")
	private CustomerOrder customerOrder;

	// bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	@Transient
	private boolean selectedRow;

	public OrderItem() {
		product = new Product();
		customerOrder = new CustomerOrder();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomerOrder getCustomerOrder() {
		return this.customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
		OrderItem other = (OrderItem) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (selectedRow != other.selectedRow)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}