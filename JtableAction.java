package action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import service.CrudDao;
import model.Order;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class JtableAction {
	
	private CrudDao dao = new CrudDao();

	private List<Order> records;
	private String result;
	private String message;
	private Order record;

	private String total_cost;
	private String transac_date;
	private String ship_before;
	private String deliver_before;
	private String order_status;	
	private String item_title;
	private int order_items_sk;
	
	public CrudDao getDao() {
		return dao;
	}

	public void setDao(CrudDao dao) {
		this.dao = dao;
	}

	public String getItem_title() {
		return item_title;
	}

	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}

	public String getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(String total_cost) {
		this.total_cost = total_cost;
	}

	public String getTransac_date() {
		return transac_date;
	}

	public void setTransac_date(String transac_date) {
		this.transac_date = transac_date;
	}

	public String getShip_before() {
		return ship_before;
	}

	public void setShip_before(String ship_before) {
		this.ship_before = ship_before;
	}

	public String getDeliver_before() {
		return deliver_before;
	}

	public void setDeliver_before(String deliver_before) {
		this.deliver_before = deliver_before;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	
	public String list() {
		try {
			Map<String , Object> session = ActionContext.getContext().getSession();
			int userid = (int)session.get("user_key");
			// Fetch Data from Student Table
			records = dao.getAllOrders(userid);
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			System.err.println(e.getMessage());
		}
		return Action.SUCCESS;
	}


	public String update() throws IOException {
		Order order= new Order();
		
		order.setOrder_items_sk(order_items_sk);
		order.setOrder_status(order_status);
		
		
		try {
			// Update existing record
			dao.updateOrder(order);
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			System.err.println(e.getMessage());
		}
		return Action.SUCCESS;
	}



	public Order getRecord() {
		return record;
	}

	public void setRecord(Order record) {
		this.record = record;
	}

	public List<Order> getRecords() {
		return records;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public void setRecords(List<Order> records) {
		this.records = records;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getOrder_items_sk() {
		return order_items_sk;
	}

	public void setOrder_items_sk(int order_items_sk) {
		this.order_items_sk = order_items_sk;
	}
}