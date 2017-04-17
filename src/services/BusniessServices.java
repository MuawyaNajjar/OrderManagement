package services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Customer;
import model.CustomerOrder;
import model.OrderItem;
import model.Product;

public class BusniessServices {

	/**
	 * @return EntityManeger Create EntityManager
	 */
	public static EntityManager open() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("OrderManagement");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		return em;
	}

	/**
	 * @param em
	 *            Commit Transaction and close EntityManager
	 */

	public static void close(EntityManager em) {
		em.getTransaction().commit();
		em.close();

	}

	/**
	 * @param customer
	 *            Add New Customer
	 */

	public void addCustomer(Customer customer) {
		EntityManager entityManager = open();
		entityManager.merge(customer);
		close(entityManager);
	}

	/**
	 * @param value
	 * @return Boolean to check if Customer is Deleted or No Delete Customer
	 */

	public Boolean deleteCustomer(Integer value) {
		EntityManager em = open();
		Customer findCustomer = em.find(Customer.class, value);
		if (findCustomer != null) {
			em.remove(findCustomer);
			close(em);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @return List Get All Customer From DB Using NamedQuere
	 */

	public List<Customer> getAllCustomer() {
		EntityManager em = open();
		List<Customer> getAll = new ArrayList<Customer>();
		Query query = em.createNamedQuery("Customer.findAll");
		getAll = query.getResultList();
		return getAll;
	}

	/**
	 * @param customerName
	 * @return SpecificCustomer By Customer Name
	 */

	public Customer specificCustomer(String customerName) {
		Customer customer = new Customer();
		EntityManager em = open();
		Query query = em
				.createQuery("Select c from Customer c where c.customerName =?1");
		query.setParameter(1, customerName);
		customer = (Customer) query.getSingleResult();
		return customer;
	}

	/**
	 * @param product
	 *            Add New Product
	 */

	public void addProduct(Product product) {
		EntityManager entityManager = open();
		entityManager.merge(product);
		close(entityManager);
	}

	/**
	 * @param value
	 * @return Boolean to check if Product is Deleted or No Delete Product
	 */

	public Boolean deleteProduct(Integer value) {
		EntityManager em = open();
		Product findProduct = em.find(Product.class, value);
		if (findProduct != null) {
			em.remove(findProduct);
			close(em);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @return List Get All Product From DB Using NamedQuere
	 */

	public List<Product> getAllProduct() {
		EntityManager em = open();
		List<Product> getAll = new ArrayList<Product>();
		Query query = em.createNamedQuery("Product.findAll");
		getAll = query.getResultList();
		return getAll;
	}

	/**
	 * @param order
	 *            Add New Order
	 */

	public void addOrder(CustomerOrder customerOrder) {
		EntityManager entityManager = open();
		entityManager.merge(customerOrder);
		close(entityManager);
	}

	/**
	 * @return List Get All Order From DB Using NamedQuere
	 */

	public List<CustomerOrder> getAllOrder() {
		EntityManager em = open();
		List<CustomerOrder> getAll = new ArrayList<CustomerOrder>();
		Query query = em.createNamedQuery("CustomerOrder.findAll");
		getAll = query.getResultList();
		return getAll;
	}

	/**
	 * @param value
	 * @return Boolean to check if Order is Deleted or No Delete Order
	 */

	public Boolean deleteOrder(Integer value) {
		EntityManager em = open();
		CustomerOrder findOrder = em.find(CustomerOrder.class, value);
		if (findOrder != null) {
			em.remove(findOrder);
			close(em);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @param order
	 *            Add New Item
	 */
	public void addItem(OrderItem item) {
		EntityManager entityManager = open();
		entityManager.merge(item);
		close(entityManager);
	}

	/**
	 * @return List Get All Item From DB Using NamedQuere
	 */

	public List<OrderItem> getAllItem() {
		EntityManager em = open();
		List<OrderItem> getAll = new ArrayList<OrderItem>();
		Query query = em.createNamedQuery("OrderItem.findAll");
		getAll = query.getResultList();
		return getAll;
	}

	/**
	 * @param value
	 * @return Boolean to check if Item is Deleted or No Delete Product
	 */

	public Boolean deleteItem(Integer value) {
		EntityManager em = open();
		OrderItem findItem = em.find(OrderItem.class, value);
		if (findItem != null) {
			em.remove(findItem);
			close(em);
			return true;
		} else {
			return false;
		}

	}

	public Customer findCustomer(Integer value) {
		EntityManager em = open();
		Customer customer = new Customer();
		customer = em.find(Customer.class, value);
		return customer;
	}

	public Product findProduct(Integer value) {
		EntityManager em = open();
		Product product = new Product();
		product = em.find(Product.class, value);
		return product;
	}

	public CustomerOrder findOrder(Integer value) {
		EntityManager em = open();
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder = em.find(CustomerOrder.class, value);
		return customerOrder;
	}
}
