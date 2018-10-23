package sprint2;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CheckPersonInfoTest {
	@Test
	public void testUS21() throws Exception {
		List<Person> people = new ArrayList<>();
		List<Family> families = new ArrayList<>();
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = new Person();
		Person person4 = new Person();
		Family family1 = new Family();
		Family family2 = new Family();


		person1.id_indi = "I1";
		person2.id_indi = "I2";
		person3.id_indi = "I3";
		person4.id_indi = "I4";
		family1.id_fam = "F1";
		family2.id_fam = "F2";

		person1.sex = "F";
		person2.sex = "M";
		person3.sex = "M";
		person4.sex = "F";

		family1.id_husband = "I2";
		family1.id_wife = "I1";
		family2.id_husband = "I4";
		family2.id_wife = "I3";
		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		families.add(family1);
		families.add(family2);

		//for capturing the system.out.println
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);


		CheckPersonInfo checkPersonInfo = new CheckPersonInfo();
		checkPersonInfo.US21(people,families);
		//for println
		String separator = System.getProperty("line.separator");
		assertEquals("ERROR: FAMILY: US21: F2: Wife I3 has wrong gender." + separator
				+ "ERROR: FAMILY: US21: F2: Husband I4 has wrong gender." + separator, os.toString());

		System.setOut(originalOut);
	}

	@Test
	public void testUS23() {
		List<Person> people = new ArrayList<>();    
		Person person1 = new Person();  
		Person person2 = new Person();	
		Person person3 = new Person();	
		Person person4 = new Person();	

		person1.id_indi = "I1";	//same name and birthday
		person2.id_indi = "I2";	//same name and birthday
		person3.id_indi = "I3"; //different name but same birthday
		person4.id_indi = "I4"; //same name but different birthday
				
		person1.name = "PANPAN";
		person2.name = "PANPAN";
		person3.name = "PAN YUE";
		person4.name = "PANPAN";
		
		person1.birt_year = "1990";
		person1.birt_month = "06";
		person1.birt_day = "15";
		person2.birt_year = "1990";
		person2.birt_month = "06";
		person2.birt_day = "15";
		person3.birt_year = "1990";
		person3.birt_month = "06";
		person3.birt_day = "15";
		person4.birt_year = "2011";
		person4.birt_month = "07";
		person4.birt_day = "13";
		
		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		
		//for capturing the system.out.println
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		CheckPersonInfo checkPersonInfo = new CheckPersonInfo();
		checkPersonInfo.US23(people);
		
		//for println
		String separator = System.getProperty("line.separator");
		assertEquals("ERROR: INDIVIDUAL: US23: I1 and I2 has the same name and birthday."+ separator, os.toString());
		System.setOut(originalOut);
		
	}

}
