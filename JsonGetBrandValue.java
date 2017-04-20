package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import utility.DBConnection;

public class JsonGetBrandValue extends ActionSupport implements ModelDriven{


	private String dummyMessage;
	private static String SUCCESS = "success";
	private static String ERROR = "error";
	
	public String getDummyMessage() {
		return dummyMessage;
	}

	public void setDummyMessage(String dummyMessage) {
		this.dummyMessage = dummyMessage;
	}

	public String getSubcatname() {
		return subcatname;
	}

	public void setSubcatname(String subcatname) {
		this.subcatname = subcatname;
	}

	private static String EXISTING = "existing";

	private Map<String, String> brandmap = new LinkedHashMap<String, String>();

	//parameter from jquery
	private String subcatname;


	public Map<String, String> getBrandmap() {
		return brandmap;
	}

	public void setBrandmap(Map<String, String> brandmap) {
		this.brandmap = brandmap;
	}


	public String execute(){

		try{
			DBConnection db=new DBConnection();
			Connection con=db.getConnection();

			PreparedStatement ps;
			ResultSet rs;
			System.out.println("subcat name:"+getSubcatname());
			System.out.println("subcat name:"+ subcatname);
			ps = con.prepareStatement("select cat2_sk from cat2 where name= ?");
			ps.setString(1, subcatname);
			rs = ps.executeQuery();
			rs.next();	
			int subcatid = rs.getInt(1); 

			System.out.println("subcategory id "+ "is"+ subcatid);

			ps =  con.prepareStatement("select * from brand where cat2_sk = ?");
			ps.setInt(1, subcatid);

			rs = ps.executeQuery();

			while(rs.next()){
				brandmap.put(Integer.toString(rs.getInt("brand_sk")), rs.getString("name"));
			}

			System.out.println("subcatlist fetched");
			dummyMessage = "Ajax Action Triggered";
			return SUCCESS;
		}

		catch(Exception e){

			e.printStackTrace();
			return ERROR;
		}

	}


	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}


}



