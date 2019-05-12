package Model;

public class Company {
	
	private int id; 
	private String code;
	private String name; 
	private String web; 
	private String email; 
	private String phone; 
	private String address;
	
	
	
	public Company(String code, String name, String web, String email, String phone, String address) {
		this.code = code;
		this.name = name;
		this.web = web;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	public Company(int id,String code, String name, String web, String email, String phone, String address) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.web = web;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	
	public void setId(int id){
		this.id = id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	} 
	
	
}
