package action;


import java.util.ArrayList;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import model.ReportModel;
import model.ReviewOrderModel;
import service.ReportService;



public class ReportAction extends ActionSupport implements ModelDriven{
	ArrayList<ReportModel> itemlist;
public ArrayList<ReportModel> getItemlist() {
		return itemlist;
	}

	public void setItemlist(ArrayList<ReportModel> itemlist) {
		this.itemlist = itemlist;
	}

@Override
public String execute() throws Exception {
	// TODO Auto-generated method stub

	ReportService rss=new ReportService();
	itemlist=rss.getItem();
	return "success";
}

@Override
public Object getModel() {
	// TODO Auto-generated method stub
	return itemlist;
}
	

	
	

}
