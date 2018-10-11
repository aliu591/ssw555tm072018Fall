
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.Rule;
import org.junit.Test;

public class CheckDateTest {
	@Test
    public void testUS01() throws Exception {
        List<Person> people = new ArrayList<>();
        List<Family> families = new ArrayList<>();
        Person person1 = new Person();    //wrong date
        Person person2 = new Person();    //correct date
        Family family1 = new Family();    //wrong date
        Family family2 = new Family();    //correct date
        person1.id_indi = "I1";
        person2.id_indi = "I2";
        family1.id_fam = "F1";
        family2.id_fam = "F2";

        person1.birt_year = "2020";//future
        person1.birt_month = "07";
        person1.birt_day = "05";
        person2.birt_year = "1999";
        person2.birt_month = "07";
        person2.birt_day = "01";

        person1.deat_year = "2020";//future
        person1.deat_month = "07";
        person1.deat_day = "05";
        person2.deat_year = "1999";
        person2.deat_month = "07";
        person2.deat_day = "01";

        family1.marr_year = "2020";//future
        family1.marr_month = "07";
        family1.marr_day = "05";
        family2.marr_year = "1999";
        family2.marr_month = "07";
        family2.marr_day = "01";

        family1.div_year = "2020";//future
        family1.div_month = "07";
        family1.div_day = "05";
        family2.div_year = "1999";
        family2.div_month = "07";
        family2.div_day = "01";

        person1.birthValid = true;
        person1.deathValid = true;
        family1.marryDateValid = true;
        family1.divorceDateValid = true;

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
        checkDate.US01(people, families);
        //for println
        String separator = System.getProperty("line.separator");
        assertEquals("ERROR: FAMILY: US1: F1: Marriage date (2020-07-05) occurs in the future." + separator
                + "ERROR: FAMILY: US1: F1: Divorce date (2020-07-05) occurs in the future." + separator
                + "ERROR: INDIVIDUAL: US1: I1: Birthday date (2020-07-05) occurs in the future." + separator
                + "ERROR: INDIVIDUAL: US1: I1: Death date (2020-07-05) occurs in the future." + separator, os.toString());

        System.setOut(originalOut);
    }

    @Test
    public void testUS10() throws Exception {
        List<Person> people = new ArrayList<>();
        List<Family> families = new ArrayList<>();
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        Person person4 = new Person();
        Family family1 = new Family();
        Family family2 = new Family();

        family1.id_fam = "F1";//wrong
        family1.id_husband = "I11";
        family1.id_wife = "I12";
        person1.id_indi = "I11";//husband
        person2.id_indi = "I12";//wife
        person1.birt_year = "1990";
        person1.birt_month = "07";
        person1.birt_day = "05";
        person2.birt_year = "1999";
        person2.birt_month = "07";
        person2.birt_day = "01";
        family1.marr_year = "2010";
        family1.marr_month = "07";
        family1.marr_day = "05";


        family2.id_fam = "F2";//correct
        family2.id_husband = "I21";
        family2.id_wife = "I22";
        person3.id_indi = "I21";//husband
        person4.id_indi = "I22";//wife
        person3.birt_year = "1969";
        person3.birt_month = "07";
        person3.birt_day = "05";
        person4.birt_year = "1970";
        person4.birt_month = "07";
        person4.birt_day = "05";
        family2.marr_year = "1993";
        family2.marr_month = "07";
        family2.marr_day = "01";


        person1.birthValid = true;
        family1.marryDateValid = true;

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
        checkDate.US10(people, families);
        //for println
        String separator = System.getProperty("line.separator");
        assertEquals("ERROR: FAMILY: US10: I12: Marriage before 14." + separator, os.toString());
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
