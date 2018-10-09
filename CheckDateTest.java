import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.Rule;
import org.junit.Test;

public class CheckDateTest {

	@Test
	public void testUS02() {
	
	}

	@Test
	public void testUS12() {
	
	}

	@Test
	public void testUS42() {
		List<Person> people = new ArrayList<>();    
		List<Family> families =  new ArrayList<>(); 
		Person person1 = new Person();  //wrong date
		Person person2 = new Person();	//correct date
		Family family1 = new Family();	//wrong date
		Family family2 = new Family();	//correct date
		person1.id_indi = "I1";
		person2.id_indi = "I2";
		family1.id_fam = "F1";
		family2.id_fam = "F2";
		
		person1.birt_year = "1999";
		person1.birt_month = "07";
		person1.birt_day = "45";
		person2.birt_year = "1999";
		person2.birt_month = "07";
		person2.birt_day = "01";
		
		person1.deat_year = "1999";
		person1.deat_month = "07";
		person1.deat_day = "45";
		person2.deat_year = "1999";
		person2.deat_month = "07";
		person2.deat_day = "01";
		
		family1.marr_year = "1999";
		family1.marr_month = "07";
		family1.marr_day = "45";
		family2.marr_year = "1999";
		family2.marr_month = "07";
		family2.marr_day = "01";
		
		family1.div_year = "1999";
		family1.div_month = "07";
		family1.div_day = "45";
		family2.div_year = "1999";
		family2.div_month = "07";
		family2.div_day = "01";
		
		person1.birthValid = false;
		person1.deathValid = false;
		family1.marryDateValid = false;
		family1.divorceDateValid = false;
		
		people.add(person1);
		people.add(person2);
		families.add(family1);
		families.add(family2);
		
		//for capturing the system.out.println
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		
		CheckDate checkDate = new CheckDate();
		checkDate.US42(people, families);
		//for println
		String separator = System.getProperty("line.separator");
		assertEquals("ERROR: INDIVIDUAL: US42: I1: Birthday date (1999-07-45) is invalid"+ separator 
				+ "ERROR: INDIVIDUAL: US42: I1: Death date (1999-07-45) is invalid"+ separator 
				+ "ERROR: FAMILY: US42: F1: Marry date (1999-07-45) is invalid"+ separator 
				+ "ERROR: FAMILY: US42: F1: Divorce date (1999-07-45) is invalid" + separator, os.toString());
		
		System.setOut(originalOut);
	}

}
