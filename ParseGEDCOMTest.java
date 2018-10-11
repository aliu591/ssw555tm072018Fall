import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ParseGEDCOMTest {

	@Test
	public void testParse() {
		ParseGEDCOM parseFile = new ParseGEDCOM();
		try {
			parseFile.parse();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
