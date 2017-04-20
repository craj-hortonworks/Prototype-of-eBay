package action;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import model.*;
import service.*;

public class InvoiceAction extends ActionSupport implements ModelDriven,Action {
		public InvoiceModel getInvoicemodel() {
		return invoicemodel;
	}
	public void setInvoicemodel(InvoiceModel invoicemodel) {
		this.invoicemodel = invoicemodel;
	}
		InvoiceModel invoicemodel = new InvoiceModel();
		Map<String , Object> session = ActionContext.getContext().getSession();
		int userid = (int)session.get("user_key");
		public String execute(){
			InvoiceService invoiceservice = new InvoiceService();
			
			invoicemodel = invoiceservice.getInvoice(userid);
			System.out.println();
			if(invoicemodel==null)
				return ERROR;
			else
				return SUCCESS;
		}
		public Object getModel() {
			
			return invoicemodel;
		}
}
