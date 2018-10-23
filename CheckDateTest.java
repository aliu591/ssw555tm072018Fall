package sprint1;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.Rule;
import org.junit.Test;

public class CheckDateTest {
	
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
		
		
		CheckDate checkDate = new CheckDate();
		checkDate.US15(families);
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
		//person4.id_indi = "I2";
		person4.id_indi = "I4";
		family1.id_fam = "F1";
		family1.id_husband="I1";
		family1.id_wife="I2";
		
		//family2.id_fam="F2";
		family2.id_fam="F1";
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
		
		
		CheckDate checkDate = new CheckDate();
		checkDate.US22(people,families);
		//for println
		String separator = System.getProperty("line.separator");
		//assertEquals("ERROR: INDIVIDUAL: US22: individual ID I2 should be unique."+ separator , os.toString());
		
		assertEquals("ERROR: Family: US22: family ID F1 should be unique."+ separator , os.toString());
		System.setOut(originalOut);
	}
	

	@Test
	public void testUS02() {
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
		person2.birt_year = "2011";
		person2.birt_month = "07";
		person2.birt_day = "13";

		
		family1.marr_year = "2010";
		family1.marr_month = "03";
		family1.marr_day = "20";
		
		people.add(person1);
		people.add(person2);
		families.add(family1);
		
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		
		CheckDate checkDate = new CheckDate();
		checkDate.US02(people, families);
		//for println
		String separator = System.getProperty("line.separator");
		assertEquals("ERROR: FAMILY: US02: F1: Wife's birth date 2011-07-13 after marriage date 2010-03-20"+ separator , os.toString());
		
		System.setOut(originalOut);
	}

	@Test
	public void testUS12() {
		List<Person> people = new ArrayList<>();    
		List<Family> families =  new ArrayList<>(); 
		Person person1 = new Person();  
		Person person2 = new Person();	
		Person person3 = new Person();  
		Person person4 = new Person();	
		Family family1 = new Family();	
		person1.id_indi = "I1";
		person2.id_indi = "I2";
		person3.id_indi = "I3";
		person4.id_indi = "I4";
		family1.id_fam = "F1";
		family1.id_husband="I1";
		family1.id_wife="I2";
		
		
		person1.age="90";
		person2.age="70";
		person3.age="20";
		person4.age="8";
		
		family1.id_children.add("I3");
		family1.id_children.add("I4");
		
		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		families.add(family1);
		
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);
		
		
		CheckDate checkDate = new CheckDate();
		checkDate.US12(people, families);
		//for println
		String separator = System.getProperty("line.separator");
		assertEquals("ERROR: FAMILY: US12: F1: wife I2 should be less than 60 years older than her children I4"+ separator
				+ "ERROR: FAMILY: US12: F1: husband I1 should be less than 80 years older than her children I4"+ separator, os.toString());
		
		System.setOut(originalOut);
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
