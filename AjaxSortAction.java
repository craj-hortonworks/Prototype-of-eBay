package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import model.SubcategoryClickModel;
import utility.DBConnection;

public class AjaxSortAction extends ActionSupport{

	//parameter received from subcategoryclickjsp
	private String sortid;
	private String subcatid;
	//private String pricefilter;
	private String toprice;
	private String fromprice;
	
	private String category_list;
	//list to read value of checkboxes i.e. list of brands
	public String getCategory_list() {
		return category_list;
	}

	public void setCategory_list(String category_list) {
		this.category_list = category_list;
	}
	
	public String getToprice() {
		return toprice;
	}

	public void setToprice(String toprice) {
		this.toprice = toprice;
	}

	public String getFromprice() {
		return fromprice;
	}

	public void setFromprice(String fromprice) {
		this.fromprice = fromprice;
	}

	private Map<Integer, SubcategoryClickModel> items = new LinkedHashMap<Integer, SubcategoryClickModel>();
	SubcategoryClickModel item;
	//key,value pair like (1, item1)...
	
	public Map<Integer, SubcategoryClickModel> getItems() {
		return items;
	}

	public void setItems(Map<Integer, SubcategoryClickModel> items) {
		this.items = items;
	}

	public SubcategoryClickModel getItem() {
		return item;
	}

	public void setItem(SubcategoryClickModel item) {
		this.item = item;
	}

	public Map<Integer, SubcategoryClickModel> getSubcatmap() {
		return items;
	}

	public void setSubcatmap(Map<Integer, SubcategoryClickModel> subcatmap) {
		this.items = subcatmap;
	}
	
	public String getSubcatid() {
		return subcatid;
	}

	public void setSubcatid(String subcatid) {
		this.subcatid = subcatid;
	}

	public String getSortid() {
		return sortid;
	}

	public void setSortid(String sortid) {
		this.sortid = sortid;
	}
		

	public String execute(){
		int cat2_id = Integer.parseInt(subcatid);
		int srtid = Integer.parseInt(sortid);
		Float fromcost = Float.parseFloat(fromprice);
		Float tocost = Float.parseFloat(toprice);
		
		System.out.println("In ajax sort action");
//		for(int i = 0; i < category_list.length ; i++){
//			System.out.println(category_list[i]);
//		}
		
		System.out.println(category_list);
		try{
			DBConnection db=new DBConnection();
			Connection con=db.getConnection();
			
			PreparedStatement ps;
			ResultSet rs;
						
			System.out.println("Sort id is" + sortid);
			System.out.println("Sub category id is" + subcatid);
			System.out.println("From Cost:" + fromcost);
			System.out.println("To Cost" + tocost);
			//System.out.println("brands "+ brands );
			
			String sortquery = null;
			
			//Filter by price
			String filterquery1 = null;
			
			//Filter by brand name
			String filterquery2 = null;
			
			//final query based on all filters(brand, price)
			String filterquery;
			
			//filters
			
			//Price filter
			if(tocost==0 && fromcost==0){
				filterquery1 = "SELECT item_sk FROM item WHERE item_sk IN ";
				//if none of the price filter is selected 
				//in is taking care of further queries (if checkboxex are checked)
				System.out.println("price filter is null");
				
			}else{
				filterquery1 = "SELECT item_sk FROM item WHERE price BETWEEN '"+ fromcost +"' AND '" + tocost +"' AND item_sk IN";
				System.out.println("price filter is specified");
				
			}
							
			//brand filter
						String brandList [] = category_list.split(" ");  
	
			//no checkbox is selected
			if(brandList.length <= 1){
				filterquery2 = "SELECT item_sk FROM item WHERE cat2_id=?";
				System.out.println("brand filter is null");
			}
			else{
				filterquery2 = "SELECT item_sk FROM item WHERE cat2_id= ? AND ( brand = '" + brandList[1] + "'";
				
				for(int i = 2; i < brandList.length; i++){
					filterquery2 += " OR brand ='" + brandList[i] + "'"; 
					//SELECT item_sk FROM item WHERE cat2_id= ? AND (brand=samsung  OR brand=lenovo)
				}		
				
				filterquery2 += ")";
				
				System.out.println(filterquery2);
			}
			
			//generating nested query combining price filter and brand filter
			filterquery = filterquery1 + "(" + filterquery2 + ")" ;	
			System.out.println(filterquery);
			
			//selecting the query based on the 
			//sort by best match
			//sort id=srtid
			if(srtid == 1){
				sortquery="SELECT * FROM item WHERE item_sk IN " + "(" + filterquery + ")";
			}
	
			//sort by price:ascending
			else if(srtid== 2){
				sortquery="SELECT * FROM item WHERE item_sk IN" + "(" + filterquery + ")" + "ORDER BY price ASC";
			}
			
			//sort by price:descending
			else if(srtid == 3){
				sortquery="SELECT * FROM item WHERE item_sk IN" + "(" + filterquery + ")" + "ORDER BY price DESC";
			}	
			
			ps =  con.prepareStatement(sortquery);
			ps.setInt(1, cat2_id);
			//ps.setInt(2, cat2_id);
			
			/*
			ps.setInt(1, cat2_id);
			
			
		*/	
			
			rs = ps.executeQuery();
			
			int i =1;
			while(rs.next()){
//				System.out.println(rs.getString("item_name"));
//				System.out.println(rs.getFloat("price"));
			item = new SubcategoryClickModel(rs.getInt("item_sk"), rs.getString("item_name"),  rs.getFloat("price"),"images/" + rs.getString("pic_name"));
				items.put(i, item);
				i++;	
			}
			
			System.out.println("Items fetched");
			
			String dummyMessage = "Ajax Action Triggered";
			return SUCCESS;

		}
		
		catch(Exception e){
			
			e.printStackTrace();
			return ERROR;
		}

	}

	

	
}
