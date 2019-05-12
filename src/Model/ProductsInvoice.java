package Model;

public class ProductsInvoice{
	private int id;
	private int productId;
	private int subTotal;
	private int quantity;
	private int invoiceId;
	
	
	public ProductsInvoice(int id, int productId, int subTotal, int quantity, int invoiceId) {
		super();
		this.id = id;
		this.productId = productId;
		this.subTotal = subTotal;
		this.quantity = quantity;
		this.invoiceId = invoiceId;
	}

	public ProductsInvoice() {
		this.productId = 0;
		this.subTotal = 0;
		this.quantity = 0;
		this.invoiceId = 0;
	}
	
	public ProductsInvoice(int productId, int subTotal, int quantity, int invoiceId) {
		this.productId = productId;
		this.subTotal = subTotal;
		this.quantity = quantity;
		this.invoiceId = invoiceId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	
}
