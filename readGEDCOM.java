import java.io.IOException;
import java.util.*;
import wagu.*;

public class readGEDCOM {

	public static void main(String[] args) {
		ParseGEDCOM parseFile = new ParseGEDCOM();
		try {
			parseFile.parse();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
