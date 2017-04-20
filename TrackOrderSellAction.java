package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import service.*;
import model.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TrackOrderSellAction {
	
	private TrackOrderSellService trackservice = new TrackOrderSellService();
	private TrackOrderSellModel tracksell = new TrackOrderSellModel();
	private List<TrackOrderSellModel> records;
	private String result;
	private String message;
	private Order record;
	private int order_items_sk;
	private String order_status;

	
	public TrackOrderSellService getDao() {
		return trackservice;
	}

	public void setDao(TrackOrderSellService trackservice) {
		this.trackservice = trackservice;
	}

	
	
	public String list() {
		try {
			Map<String , Object> session = ActionContext.getContext().getSession();
			int userid = (int)session.get("user_key");
			// Fetch Data from Student Table
			records = trackservice.getAllOrders(userid);
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			e.printStackTrace();
			
			message = e.getMessage();
			System.err.println(e.getMessage());
			
		}
		System.out.println("m heresdsd");

		return Action.SUCCESS;
	}

	public String update() throws IOException {
		Order order= new Order();
		
		order.setOrder_items_sk(order_items_sk);
		order.setOrder_status(order_status);
		
		
		try {
			// Update existing record
			trackservice.updateOrder(order);
			result = "OK";
		} catch (Exception e) {
			result = "ERROR";
			message = e.getMessage();
			System.err.println(e.getMessage());
		}
		return Action.SUCCESS;
	}
	



	
	public TrackOrderSellService getTrackservice() {
		return trackservice;
	}

	public void setTrackservice(TrackOrderSellService trackservice) {
		this.trackservice = trackservice;
	}

	public TrackOrderSellModel getTracksell() {
		return tracksell;
	}

	public void setTracksell(TrackOrderSellModel tracksell) {
		this.tracksell = tracksell;
	}

	


	public Order getRecord() {
		return record;
	}

	public void setRecord(Order record) {
		this.record = record;
	}

	public List<TrackOrderSellModel> getRecords() {
		return records;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public void setRecords(List<TrackOrderSellModel> records) {
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

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}


}