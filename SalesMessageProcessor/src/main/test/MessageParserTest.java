import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;


public class MessageParserTest {

	MessageParser parser = new MessageParser();
	
	@Test
	public void testParseMessageTypeOne(){
		String line = "apple at 10p";
		parser.evaluate(line);
		assertTrue(MessageParser.productMap.containsKey("apples"));
	}
	
	@Test
	public void testParseMessageTypeTwo(){
		String line = "20 sales of apples at 20p";
		parser.evaluate(line);
		Product product = MessageParser.productMap.get("apples");
		assertNotNull(product);
		assertThat(product.getPrice(), is(20.0));
	}
	
	@Test
	public void testParseMessageTypeThree(){
		String line = "20 sales of apples at 20p";
		parser.evaluate(line);
		line = "Add 20p apples";
		parser.evaluate(line);
		Product product = MessageParser.productMap.get("apples");
		assertNotNull(product);
		assertThat(product.getPrice(), is(40.0));
	}
}
