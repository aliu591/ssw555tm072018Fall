package sprint3;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CheckPersonInfoTest {
	@Test
	public void testUS18() throws Exception {
		List<Person> people = new ArrayList<>();
		List<Family> families = new ArrayList<>();
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = new Person();
		Person person4 = new Person();
		Family family = new Family();

		person1.id_indi = "I1";
		person1.id_famc.add("F1");
		person1.id_fams.add("F1");
		person2.id_indi = "I2";
		person2.id_famc.add("F1");
		person2.id_fams.add("F1");
		person3.id_indi = "I3";
		person3.id_famc.add("F2");
		person3.id_fams.add("F3");
		person4.id_indi = "I4";
		person4.id_famc.add("F4");
		person4.id_fams.add("F3");


		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		families.add(family);

		//for capturing the system.out.println
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);


		CheckPersonInfo checkPersonInfo = new CheckPersonInfo();
		checkPersonInfo.US18(people,families);
		//for println
		String separator = System.getProperty("line.separator");
		assertEquals("ERROR: Individual: US18: Individual ID I1 married with other sibling in family" + separator
				+"ERROR: Individual: US18: Individual ID I2 married with other sibling in family" + separator, os.toString());

		System.setOut(originalOut);
	}

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
	
	@Test
	public void testUS15() {
		List<Person> people = new ArrayList<>();    
		List<Family> families =  new ArrayList<>(); 
		Person person1 = new Person();  
		Person person2 = new Person();	
		Family family1 = new Family();	
		person1.id_indi = "I1";
		person2.id_indi = "I2";
		family1.id_fam = "F1";
		family1.id_husband="I1";
		family1.id_wife="I2";
		
		person1.birt_year = "1990";
		person1.birt_month = "06";
		person1.birt_day = "15";
		person2.birt_year = "1996";
		person2.birt_month = "07";
		person2.birt_day = "13";

		
		family1.marr_year = "2010";
		family1.marr_month = "03";
		family1.marr_day = "20";
		String[] array = {"C1","C2","C3","C4","C5","C6","C7","C8","C9","C10","C11","C12","C13","C14","C15","C16"};
		
		for(int i=0;i<array.length;i++){
			family1.id_children.add(array[i]);
		}
		people.add(person1);
		people.add(person2);
		families.add(family1);
		
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		
		CheckPersonInfo checkInfo = new CheckPersonInfo();
		checkInfo.US15(families);
		//for println
		String separator = System.getProperty("line.separator");
		assertEquals("ERROR: FAMILY: US15: F1 has more than 15 siblings"+ separator , os.toString());
		
		System.setOut(originalOut);
	}
	
	
	@Test
	public void testUS22() {
		List<Person> people = new ArrayList<>();    
		List<Family> families =  new ArrayList<>(); 
		Person person1 = new Person();  
		Person person2 = new Person();
		Person person3 = new Person();  
		Person person4 = new Person();	
		Family family1 = new Family();	
		Family family2 = new Family();
		person1.id_indi = "I1";
		person2.id_indi = "I2";
		person3.id_indi = "I3";
		person4.id_indi = "I2";  // the same individual ID
		//person4.id_indi = "I4";
		family1.id_fam = "F1";
		family1.id_husband="I1";
		family1.id_wife="I2";
		
		family2.id_fam="F1"; // the same family ID
		family2.id_husband="I3";
		family2.id_wife="I4";
		
		person1.birt_year = "1990";
		person1.birt_month = "06";
		person1.birt_day = "15";
		person2.birt_year = "1996";
		person2.birt_month = "07";
		person2.birt_day = "13";
		
		person3.birt_year = "1992";
		person3.birt_month = "05";
		person3.birt_day = "15";
		person4.birt_year = "1993";
		person4.birt_month = "06";
		person4.birt_day = "13";

		
		family1.marr_year = "2010";
		family1.marr_month = "03";
		family1.marr_day = "20";
		family2.marr_year = "2011";
		family2.marr_month = "06";
		family2.marr_day = "20";
		
		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		families.add(family1);
		families.add(family2);
		
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		
		CheckPersonInfo checkInfo = new CheckPersonInfo();
		checkInfo.US22(people,families);
		//for println
		String separator = System.getProperty("line.separator");
		assertEquals("ERROR: INDIVIDUAL: US22: individual ID I2 should be unique."+ separator +
				"ERROR: Family: US22: family ID F1 should be unique."+ separator , os.toString());
		System.setOut(originalOut);
	}

	
	@Test
	public void testUS14() {
		List<Person> people = new ArrayList<>();    
		List<Family> families =  new ArrayList<>(); 
		Person person1 = new Person();  
		Person person2 = new Person();
		Person person3 = new Person();  
		Person person4 = new Person();	
		Person person5 = new Person();	
		Person person6 = new Person();	
		Family family1 = new Family();
		family1.id_fam = "F1";
		person1.birt_year = "1990";
		person1.birt_month = "06";
		person1.birt_day = "15";
		person2.birt_year = "1990";
		person2.birt_month = "06";
		person2.birt_day = "15";
		person3.birt_year = "1990";
		person3.birt_month = "06";
		person3.birt_day = "15";
		person4.birt_year = "1990";
		person4.birt_month = "06";
		person4.birt_day = "15";
		person5.birt_year = "1990";
		person5.birt_month = "06";
		person5.birt_day = "15";
		person6.birt_year = "1990";
		person6.birt_month = "06";
		person6.birt_day = "15";
		
		family1.children.add(person1);
		family1.children.add(person2);
		family1.children.add(person3);
		family1.children.add(person4);
		family1.children.add(person5);
		family1.children.add(person6);
		families.add(family1);
		
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		
		CheckPersonInfo checkInfo = new CheckPersonInfo();
		checkInfo.US14(people,families);
		//for println
		String separator = System.getProperty("line.separator");
		assertEquals("ERROR: Family: US14: family ID F1 has more than five sibling born at the same time."+ separator , os.toString());
		System.setOut(originalOut);
	}
	
	@Test
	public void testUS25() {
		List<Person> people = new ArrayList<>();    
		List<Family> families =  new ArrayList<>(); 
		Person person1 = new Person();  
		Person person2 = new Person();
		Person person3 = new Person();  
		Person person4 = new Person();	
		Person person5 = new Person();	
		Person person6 = new Person();	
		Family family1 = new Family();
		family1.id_fam = "F1";
		person1.name="Tom Jame";
		person1.birt_year = "1990";
		person1.birt_month = "06";
		person1.birt_day = "15";
		person2.name="Tony Jame";
		person2.birt_year = "1995";
		person2.birt_month = "06";
		person2.birt_day = "25";
		person3.name="Honey Jame";
		person3.birt_year = "1993";
		person3.birt_month = "06";
		person3.birt_day = "24";
		person4.name="Part Jame";
		person4.birt_year = "1992";
		person4.birt_month = "06";
		person4.birt_day = "21";
		person5.name="Happy Jame";
		person5.birt_year = "1991";
		person5.birt_month = "06";
		person5.birt_day = "20";
		person6.name="Tom Jame";
		person6.birt_year = "1990";
		person6.birt_month = "06";
		person6.birt_day = "15";
		
		family1.children.add(person1);
		family1.children.add(person2);
		family1.children.add(person3);
		family1.children.add(person4);
		family1.children.add(person5);
		family1.children.add(person6);
		families.add(family1);
		
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		
		CheckPersonInfo checkInfo = new CheckPersonInfo();
		checkInfo.US25(people,families);
		//for println
		String separator = System.getProperty("line.separator");
		assertEquals("ERROR: Family: US25: Children name Tom Jame in family should be unique."+ separator , os.toString());
		System.setOut(originalOut);
	}
}
