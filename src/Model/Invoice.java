package Model;

public class Invoice {
	private int id;
	private String code;
	private String payment;
	private int total;
	private int customerId;
	
	
	
	public Invoice(int id, String code, String payment, int total, int customerId) {
		super();
		this.id = id;
		this.code = code;
		this.payment = payment;
		this.total = total;
		this.customerId = customerId;
	}
	public Invoice(String code, String payment, int total, int customerId) {
		super();
		this.code = code;
		this.payment = payment;
		this.total = total;
		this.customerId = customerId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
