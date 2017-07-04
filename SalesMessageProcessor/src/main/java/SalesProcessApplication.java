import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Akshata Hatkar 
 * This is the java entry class which processes the sales messages
 */
public class SalesProcessApplication {

	public static void main(String[] args) {
		String line = new String();
		MessageParser msgParser = new MessageParser();
		SalesReport salesReport = new SalesReportImpl();

		try (BufferedReader inputFile = new BufferedReader(
				new FileReader("src/main/resources/salesDetailsInput.txt"));) {

			int threshold = 0; //to keep track of no. of notifications read
			while ((line = inputFile.readLine()) != null) {
				msgParser.evaluate(line); //parsing one notification at a time
				threshold++;
				if (threshold % 10 == 0) {
					salesReport.fireGeneralReport(); 
				}
				if (threshold == 50) {
					System.out.println("            PAUSING           ");
					Thread.sleep(2000); //Pausing the application for 2 seconds
					salesReport.fireAdjustmentReport();
					System.out.println("-- Exiting application as 50 messages processed successfully --");
					System.exit(1);
				}
			}
		} catch (IOException | InterruptedException ie) {

			System.out.println(ie.getMessage());
		}
	}
}
