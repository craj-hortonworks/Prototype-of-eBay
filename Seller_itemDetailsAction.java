package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import utility.DBConnection;


public class Seller_itemDetailsAction extends ActionSupport implements ModelDriven {
	private static String SUCCESS = "success";
	private static String ERROR = "failure";
	private static String EXISTING = "existing";

	private String subCategoryName,categoriesName,Category_number;
	private ArrayList<String> brandmap = new ArrayList<String>();
	

	public ArrayList<String> getBrandmap() {
		return brandmap;
	}

	public void setBrandmap(ArrayList<String> brandmap) {
		this.brandmap = brandmap;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getCategoriesName() {
		return categoriesName;
	}

	public void setCategoriesName(String categoriesName) {
		this.categoriesName = categoriesName;
	}
	
	public String execute(){
		categoriesName = getCategoriesName();
		subCategoryName = getSubCategoryName();
		try{
			DBConnection db=new DBConnection();
			Connection con=db.getConnection();

			PreparedStatement ps;
			ResultSet rs;
			ps = con.prepareStatement("select cat2_sk from cat2 where name= ?");
			ps.setString(1, subCategoryName);
			rs = ps.executeQuery();
			rs.next();	
			int subcatid = rs.getInt(1); 
			ps =  con.prepareStatement("select * from brand where cat2_sk = ?");
			ps.setInt(1, subcatid);

			rs = ps.executeQuery();

			while(rs.next()){
				brandmap.add(rs.getString("name"));
			}
			brandmap.add("NA");
			System.out.println("subcatlist fetched");
		//	dummyMessage = "Ajax Action Triggered";
		System.out.println("cat  " + categoriesName+"  subcat" +subCategoryName	);
		}

		catch(Exception e){

			e.printStackTrace();
			return ERROR;
		}


		return SUCCESS;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return brandmap;
	}

	public String getCategory_number() {
		return Category_number;
	}

	public void setCategory_number(String category_number) {
		Category_number = category_number;
	}
}
