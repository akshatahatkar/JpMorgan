import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Akshata Hatkar
 * Responsible to parse the message and process the product information
 */
public class MessageParser {

	public static Map<String, Product> productMap = new HashMap<>(); // tracks the products updates
	public static List<String> adjustmentList = new ArrayList<>(); // tracks the adjustments done on product

	/**
	 * This method filters the messages to determine its type and passes for processing
	 * @param String is a message to be parsed
	 */
	public void evaluate(String line) {

		String[] productInfo = line.split(" ");
		for (String word : productInfo) {
			if (word.matches("Add|Subtract|Multiply")) { //Message type 3
				parseMessageTypeThree(productInfo);
				break;
			} else if (word.equalsIgnoreCase("sales")) { //Message type 3
				parseMessageTypeTwo(productInfo);
				break;
			} else if (word.equalsIgnoreCase("at")) { //Message type 3
				parseMessageTypeOne(productInfo);
				break;
			}
		}
	}

	/**
	 * This method adds the processed product info to the static hashmap
	 * @param productInfo parsed String array
	 */
	private void parseMessageTypeOne(String[] productInfo) {
		if (productInfo.length != 3) {
			System.out.println("Invalid input for Message Type 1");
		} else {
			String productType = getProductType(productInfo[0]);
			Product product = productMap.get(productType);
			if (product == null) { //Check for null for 1st execution of any product
				product = new Product();
			}
			product.setType(productType);
			product.setPrice(getPrice(productInfo[2]));
			product.setQuantity(product.getQuantity());
			product.setTotalSaleAmount(product.getTotalSaleAmount());
			productMap.put(productType, product);
		}
	}

	/**
	 * This method updates the quantity and total sales to the static hashmap
	 * @param productInfo parsed String array
	 */
	private void parseMessageTypeTwo(String[] productInfo) {
		if (productInfo.length != 6) {
			System.out.println("Invalid input for Message Type 2");
		} else {
			String productType = getProductType(productInfo[3]);
			int quantity = Integer.valueOf(productInfo[0]);
			Product product = productMap.get(productType);
			if (product == null) {
				product = new Product();
			}
			product.setPrice(getPrice(productInfo[5]));
			product.setQuantity(product.getQuantity() + quantity);
			product.setTotalSaleAmount(product.getTotalSaleAmount() + (product.getPrice() * quantity));
			productMap.put(productType, product);
		}
	}

	/**
	 * This method identifies the operation and updates the product to the static hashmap
	 * @param productInfo parsed String array
	 */
	private void parseMessageTypeThree(String[] productInfo) {
		if (productInfo.length != 3) {
			System.out.println("Invalid input for Message Type 3");
		} else {
			String productType = getProductType(productInfo[2]);
			Product product = productMap.get(productType);
			if (product == null) {
				product = new Product();
			}
			double price = product.getPrice();

			try {
				if (productInfo[0].equalsIgnoreCase("Add")) {
					product.setPrice(product.getPrice() + getPrice(productInfo[1]));
				}
				if (productInfo[0].equalsIgnoreCase("Subtract")) {

					if (price > 0) { //to avoid negative values
						product.setPrice(price - getPrice(productInfo[1]));
					} 
				}
				if (productInfo[0].equalsIgnoreCase("Multiply")) {
					if (price > 0) { //to avoid multiplication by 0
						product.setPrice(price * getPrice(productInfo[1]));
					}
				}
				product.setQuantity(product.getQuantity());
				product.setTotalSaleAmount(product.getTotalSaleAmount());
				productMap.put(product.getType(), product);
				adjustmentList.add(product.getType() + " with base value " + price + " has been adjusted to "
						+ product.getPrice());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Method to remove p(currency) to get pure number
	 * @param value
	 * @return price without currency info
	 */
	private double getPrice(String value) {
		return Double.valueOf(value.substring(0, value.indexOf('p')));
	}

	/**
	 * Method to generalize the key for map
	 * @param product type
	 * @return product type
	 */
	private String getProductType(String type) {
		String productType = new String();
		if (type.contains("apple")) { //could be apple or apples
			productType = "apples";
		}
		if (type.contains("banana")) {
			productType = "bananas";
		}
		if (type.contains("pineapple")) {
			productType = "pineapples";
		}
		if (type.contains("avocado")) {
			productType = "avocados";
		}
		return productType;
	}

}
