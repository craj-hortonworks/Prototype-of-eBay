package action;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.HomeCarouselModel;
import service.HomeCarouselService;

public class HomeCarouselAction extends ActionSupport {

	ArrayList<HomeCarouselModel> itemList = new ArrayList<HomeCarouselModel>() ;

	public ArrayList<HomeCarouselModel> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<HomeCarouselModel> itemList) {
		this.itemList = itemList;
	}
	
	public String execute(){
		
		System.out.println("home_carousel_action_execute");
		HomeCarouselService  service = new HomeCarouselService();
		itemList = service.getItem();
		
		for(int i = 0; i<itemList.size(); i++){
			System.out.println(itemList.get(i).getItemId());
			System.out.println(itemList.get(i).getItemName());
			System.out.println(itemList.get(i).getPhotoFileName());
		}
		ServletActionContext.getRequest().setAttribute("itemList", itemList);
		if(itemList != null)
			return SUCCESS;
		else
			return ERROR;
	}
}
