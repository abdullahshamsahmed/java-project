package Model;

public class Product {
	private int id;
	private String name;
	private int unitPrice;
	private String category;
	private String description;
	
	
	public Product(String name, int unitPrice, String category, String description) {
		this.name = name;
		this.unitPrice = unitPrice;
		this.category = category;
		this.description = description;
	}
	public Product(int id, String name, int unitPrice, String category, String description) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.category = category;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String toString(){
		return this.name;
	}
}
