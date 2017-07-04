/**
 * 
 * @author Akshata Hatkar
 * Implementing SalesReport interface
 */
public class SalesReportImpl implements SalesReport {

	/* (non-Javadoc)
	 * @see SalesReport#fireGeneralReport()
	 * This method prints a sales report for every 10 messages consumed.
	 */
	@Override
	public void fireGeneralReport() {
		System.out.println("*********************SALES REPORT*****************************");
		System.out.println("****************10 MESSAGES PROCESSED*************************");
		for (Product product : MessageParser.productMap.values()) {
			double price = product.getPrice() / 100; //division by 100 to display the amount in pounds
			double total = product.getTotalSaleAmount() / 100;
			System.out.printf("%d quantities of %s were sold at a base price of %.2f. Total sale is %.2f",
					product.getQuantity(), product.getType(), price, total);
			System.out.println("\n----------------------------------------------------------");
		}
	}

	/* (non-Javadoc)
	 * @see SalesReport#fireAdjustmentReport()
	 * This method prints a report for all the prices adjusted during sales processing.
	 */
	@Override
	public void fireAdjustmentReport() {
		System.out.println("*********************ADJUSTMENT SALES REPORT*****************************");
		for (String message : MessageParser.adjustmentList) {
			System.out.println(message);
		}
	}

}
