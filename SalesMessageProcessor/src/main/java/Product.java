
/**
 * 
 * @author Akshata Hatkar
 * POJO class to represent a product
 *
 */
public class Product {

	private String type;
	
	private double price;
	
	private double totalSaleAmount;

	private int quantity;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getTotalSaleAmount() {
		return totalSaleAmount;
	}

	public void setTotalSaleAmount(double totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
