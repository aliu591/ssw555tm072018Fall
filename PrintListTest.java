import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PrintListTest {
	
	@Test
    public void testUS38(){
		List<Person> people = new ArrayList<>();
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = new Person();

		people.add(person1);
		people.add(person2);
		people.add(person3);

		person1.id_indi = "I1";
		person2.id_indi = "I2";
		person3.id_indi = "I3";

		person1.name = "Person1";
		person2.name = "Person2";
		person3.name = "Person3";

		person1.birt_year = "2017";
		person1.birt_month = "11";
		person1.birt_day = "30";
		person2.birt_year = "1999";
		person2.birt_month = "07";
		person2.birt_day = "01";
		person3.birt_year = "2017";
		person3.birt_month = "08";
		person3.birt_day = "11";

		person1.birthValid = true;
		person2.birthValid = true;
		person3.birthValid = true;
		person1.birthBeforeToday = true;
		person2.birthBeforeToday = true;
		person3.birthBeforeToday = true;
		person1.alive = "True";
		person2.alive = "False";
		person3.alive = "True";
		
		PrintList printList = new PrintList();
		List<List<String>> list = printList.US38(people);

		assertEquals( "I1" ,list.get(0).get(0));
		assertEquals( "Person1",list.get(0).get(1) );
		assertEquals("2017-11-30" ,list.get(0).get(2));
	}
	
	@Test
    public void testUS39(){
		List<Family> families = new ArrayList<>();
		Family family1 = new Family(); //anni
		Family family2 = new Family();	//one dead
		Person hus1 = new Person();
		Person hus2 = new Person();
		Person wife1 = new Person();
		Person wife2 = new Person();
		family1.husband = hus1;
		family1.wife = wife1;
		family2.husband = hus2;
		family1.wife = wife2;
		family1.marryBeforeToday = true;
		family1.marryDateValid = true;
		family1.marryBeforeToday = true;
		family1.marryDateValid = true;
		family1.id_fam = "F1";
		family2.id_fam = "F2";
		family1.marr_year = "2017";
		family1.marr_month = "11";
		family1.marr_day = "30";
		family1.div_year = "NA";
		family1.marr_year = "2017";
		family1.marr_month = "11";
		family1.marr_day = "30";
		family2.div_year = "NA";

		hus1.id_indi = "I1";
		hus2.id_indi = "I2";
		wife1.id_indi = "I3";
		wife2.id_indi = "I4";

		family1.name_hasband = "hus1";
		family2.name_hasband = "hus2";
		family1.name_wife = "wife1";
		family2.name_wife = "wife2";
		
		hus1.alive = "True";
		hus2.alive = "False";
		wife1.alive = "True";
		wife2.alive = "True";

		families.add(family1);
		families.add(family2);
		PrintList printList = new PrintList();
		List<List<String>> list = printList.US39(families);

		assertEquals( "F1" ,list.get(0).get(0));
		assertEquals( "hus1",list.get(0).get(1) );
		assertEquals("wife1" ,list.get(0).get(2));
		assertEquals("2017-11-30" ,list.get(0).get(3));
		
	}
	
    @Test
    public void testUS35(){
		List<Person> people = new ArrayList<>();
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = new Person();
		Person person4 = new Person();

		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);

		person1.id_indi = "I1";
		person2.id_indi = "I2";
		person3.id_indi = "I3";
		person4.id_indi = "I4";

		person1.name = "Person1";
		person2.name = "Person2";
		person3.name = "Person3";
		person4.name = "Person4";

		person1.birt_year = "2018";
		person1.birt_month = "11";
		person1.birt_day = "11";
		person2.birt_year = "1999";
		person2.birt_month = "07";
		person2.birt_day = "01";
		person3.birt_year = "2018";
		person3.birt_month = "10";
		person3.birt_day = "11";
		person4.birt_year = "2018";
		person4.birt_month = "11";
		person4.birt_day = "09";

		person1.birthValid = true;
		person2.birthValid = true;
		person3.birthValid = true;
		person4.birthValid = true;
		person1.birthBeforeToday = true;
		person2.birthBeforeToday = true;
		person3.birthBeforeToday = true;
		person4.birthBeforeToday = true;

		PrintList printList = new PrintList();
		List<List<String>> list = printList.US35(people);

		assertEquals( "I1" ,list.get(0).get(0));
		assertEquals( "Person1",list.get(0).get(1) );
		assertEquals("2018-11-11" ,list.get(0).get(2));
		assertEquals("I4" ,list.get(1).get(0));
		assertEquals("Person4",list.get(1).get(1) );
		assertEquals("2018-11-09" ,list.get(1).get(2));
    }

    @Test
    public void testUS36(){
		List<Person> people = new ArrayList<>();

		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = new Person();
		Person person4 = new Person();

		person1.id_indi = "I1";
		person2.id_indi = "I2";
		person3.id_indi = "I3";
		person4.id_indi = "I4";

		person1.name = "Person1";
		person2.name = "Person2";
		person3.name = "Person3";
		person4.name = "Person4";

		person1.deat_year = "1999";
		person1.deat_month = "11";
		person1.deat_day = "07";
		person2.deat_year = "2018";
		person2.deat_month = "11";
		person2.deat_day = "01";
		person3.deat_year = "2018";
		person3.deat_month = "10";
		person3.deat_day = "12";
		person4.deat_year = "2018";
		person4.deat_month = "10";
		person4.deat_day = "11";

		person1.birthValid = true;
		person2.birthValid = true;
		person3.birthValid = true;
		person4.birthValid = true;
		person1.birthBeforeToday = true;
		person2.birthBeforeToday = true;
		person3.birthBeforeToday = true;
		person4.birthBeforeToday = true;

		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		
		PrintList printList = new PrintList();
		List<List<String>> list = printList.US36(people);

		assertEquals( "I2" ,list.get(0).get(0));
		assertEquals( "Person2",list.get(0).get(1) );
		assertEquals("2018-11-01" ,list.get(0).get(2));

    }

    @Test
	public void testUS30() {
		List<Person> people = new ArrayList<>();
		List<Family> families = new ArrayList<>();
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = new Person();
		Person person4 = new Person();
		Family family1 = new Family();
		Family family2 = new Family();
		Family family3 = new Family();

		person1.id_indi = "I1";
		person1.alive = "True";
		person1.name ="XiaoLi";
		
		person2.id_indi = "I2";
		person2.alive = "True";
		person2.name ="XiaoZhou";
		
		person3.id_indi = "I3";
		person3.alive = "False";
		person3.name ="XiaoLei";

		//divorce
		family1.marryDateValid = true;
		family1.divorceDateValid = true;
		family1.marr_year = "1980";
		family1.div_year = "1990";
		family1.id_wife = "I1";
		family1.id_husband = "I3";
		family1.id_fam = "F1";

		//I3 husband dead
		family2.marryDateValid = true;
		family2.divorceDateValid = true;
		family2.marr_year = "1980";
		family2.div_year = "NA";
		family2.id_wife = "I2";
		family2.id_husband = "I3";
		family2.id_fam = "F2";

		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		families.add(family1);
		families.add(family2);
		families.add(family3);

		PrintList printList = new PrintList();
		List<List<String>> list = printList.US30(people, families);

		assertEquals(list.get(0).get(0), "I2" );
		assertEquals(list.get(0).get(1), "XiaoZhou" );
		assertEquals(list.get(0).get(2), "F2" );
	}

	@Test
	public void testUS29() { 
		List<Person> people = new ArrayList<>();     
		Person person1 = new Person();  //wrong date
		Person person2 = new Person();	//correct date
		
		person1.id_indi = "I1";
		person2.id_indi = "I2";

		person1.deat_year = "2000";
		person1.deat_month = "07";
		person1.deat_day = "45";
		person1.alive = "False";
		
		people.add(person1);
		people.add(person2);
				
		PrintList printList = new PrintList();
		List<List<String>> list = printList.US29(people);
		
		assertEquals(list.get(0).get(0), "I1" );
		assertEquals(list.get(0).get(1), "NA" );
		assertEquals(list.get(0).get(2), "2000-07-45" );

	}
	
	@Test
	public void testUS33() { 
		List<Person> people = new ArrayList<>();    
		List<Family> families = new ArrayList<>();    
		Person person1 = new Person();  //wrong date
		Person person2 = new Person();	//correct date
		Person person3 = new Person();
		Family family = new Family();
		
		person1.id_indi = "I1";
		person1.alive = "False";
		person2.id_indi = "I2";
		person2.alive = "False";
		person3.id_indi = "I3";
		person3.name = "PPPYYY";
		person3.alive = "True";
		person3.age = "14";
		family.id_fam  = "F1";
		family.husband = person1;
		family.wife = person2;
		family.children.add(person3);
		
		people.add(person1);
		people.add(person2);
		people.add(person3);
		families.add(family);
				
		PrintList printList = new PrintList();
		List<List<String>> list = printList.US33(families);
		
		assertEquals(list.get(0).get(0), "I3" );
		assertEquals(list.get(0).get(1), "PPPYYY" );
		assertEquals(list.get(0).get(2), "F1" );
	}

	@Test
	public void testUS27() { 
		List<Person> people = new ArrayList<>();    
		List<Family> families = new ArrayList<>();    
		Person person1 = new Person();  
		Person person2 = new Person();	
		Person person3 = new Person();
		Family family = new Family();
		
		person1.id_indi = "I1";
		person1.name="Tom Jame";
		person1.alive = "True";
		person1.sex="M";
		person1.birt_year = "1990";
		person1.birt_month = "06";
		person1.birt_day = "15";
		
		person2.id_indi = "I2";
		person2.alive = "False";
		person2.deat_day="6";
		person2.deat_month="3";
		person2.deat_year="2010";
		person2.name="Honey Jame";
		person2.sex="F";
		person2.birt_year = "1991";
		person2.birt_month = "06";
		person2.birt_day = "20";
		
		person3.id_indi = "I3";
		person3.name = "PPPYYY";
		person3.alive = "True";
		person3.sex="M";
		person3.birt_year = "1993";
		person3.birt_month = "06";
		person3.birt_day = "21";
		person3.alive="True";
		person3.id_famc.add("F1");
		
		family.id_fam  = "F1";
		family.husband = person1;
		family.wife = person2;
		family.children.add(person3);
		
		people.add(person1);
		people.add(person2);
		people.add(person3);
		families.add(family);
				
		PrintList printList = new PrintList();
		List<List<String>> list = printList.US27(people);
		
		assertEquals(list.get(2).get(0), "I3" );
		assertEquals(list.get(2).get(1), "PPPYYY" );
		assertEquals(list.get(2).get(2), "M" );
		assertEquals(list.get(2).get(3), "1993-06-21");
		assertEquals(list.get(2).get(4), "25");
		assertEquals(list.get(2).get(5), "True");
		assertEquals(list.get(2).get(6), "NA");
		assertEquals(list.get(2).get(7), "[F1]");
		assertEquals(list.get(2).get(8), "NA");
	}
	
	@Test
	public void testUS28() { 
		List<Person> people = new ArrayList<>();    
		List<Family> families = new ArrayList<>();    
		Person person1 = new Person();  
		Person person2 = new Person();	
		Person person3 = new Person();
		Person person4 = new Person();
		Family family = new Family();
		Family family1 = new Family();
		
		person1.id_indi = "I1";
		person1.name="Tom Jame";
		person1.alive = "True";
		person1.sex="M";
		person1.birt_year = "1986";
		person1.birt_month = "06";
		person1.birt_day = "15";
		person1.age="32";
		person1.id_famc.add("F1");
		
		person2.id_indi = "I2";
		person2.alive = "False";
		person2.deat_day="6";
		person2.deat_month="3";
		person2.deat_year="2010";
		person2.name="Honey Jame";
		person2.sex="F";
		person2.birt_year = "1979";
		person2.birt_month = "06";
		person2.birt_day = "20";
		person2.age="31";
		person2.id_famc.add("F1");
		
		person3.id_indi = "I3";
		person3.name = "PPPAAA";
		person3.alive = "True";
		person3.sex="M";
		person3.birt_year = "2000";
		person3.birt_month = "06";
		person3.birt_day = "21";
		person3.alive="True";
		person3.age="18";
		person3.id_famc.add("F2");
		
		person4.id_indi = "I4";
		person4.name = "PPPYYY";
		person4.alive = "True";
		person4.sex="F";
		person4.birt_year = "1980";
		person4.birt_month = "06";
		person4.birt_day = "21";
		person4.alive="True";
		person4.age="38";
		person4.id_famc.add("F2");
		
		family.id_fam  = "F1";
		family.children.add(person1);
		family.children.add(person2);
		
		family1.id_fam  = "F2";
		family1.children.add(person3);
		family1.children.add(person4);
		
		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		families.add(family);
		families.add(family1);
				
		PrintList printList = new PrintList();
		List<List<String>> list = printList.US28(people,families);
		
		assertEquals(list.get(0).get(0), "I1" );
		assertEquals(list.get(0).get(1), "Tom Jame" );
		assertEquals(list.get(0).get(2), "32" );
		assertEquals(list.get(0).get(3), "[F1]" );
		
		assertEquals(list.get(1).get(0), "I2" );
		assertEquals(list.get(1).get(1), "Honey Jame" );
		assertEquals(list.get(1).get(2), "31" );
		assertEquals(list.get(1).get(3), "[F1]" );
		
		assertEquals(list.get(2).get(0), "I4" );
		assertEquals(list.get(2).get(1), "PPPYYY" );
		assertEquals(list.get(2).get(2), "38" );
		assertEquals(list.get(2).get(3), "[F2]" );
		
		assertEquals(list.get(3).get(0), "I3" );
		assertEquals(list.get(3).get(1), "PPPAAA" );
		assertEquals(list.get(3).get(2), "18" );
		assertEquals(list.get(3).get(3), "[F2]" );
	}

	
	@Test
	public void testUS31() { 
		List<Person> people = new ArrayList<>();    
		List<Family> families = new ArrayList<>();    
		Person person1 = new Person();  
		Person person2 = new Person();	
		Person person3 = new Person();
		Person person4 = new Person();
		Family family = new Family();
		
		person1.id_indi = "I1";
		person1.name="Tom Jame";
		person1.alive = "True";
		person1.sex="M";
		person1.birt_year = "1986";
		person1.birt_month = "06";
		person1.birt_day = "15";
		person1.age="32";
		
		person2.id_indi = "I2";
		person2.alive = "False";
		person2.deat_day="6";
		person2.deat_month="3";
		person2.deat_year="2010";
		person2.name="Honey Jame";
		person2.sex="F";
		person2.birt_year = "1979";
		person2.birt_month = "06";
		person2.birt_day = "20";
		person2.age="31";
		
		person3.id_indi = "I3";
		person3.name = "PPPYYY";
		person3.alive = "True";
		person3.sex="M";
		person3.birt_year = "2000";
		person3.birt_month = "06";
		person3.birt_day = "21";
		person3.alive="True";
		person3.age="18";
		
		person4.id_indi = "I4";
		person4.name = "PPPAAA";
		person4.alive = "True";
		person4.sex="F";
		person4.birt_year = "1980";
		person4.birt_month = "06";
		person4.birt_day = "21";
		person4.alive="True";
		person4.age="38";
		person4.id_fams.add("F1");
		
		family.id_fam  = "F1";
		family.wife = person4;
		family.children.add(person3);
		
		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		families.add(family);
				
		PrintList printList = new PrintList();
		List<List<String>> list = printList.US31(people,families);
		
		assertEquals(list.get(0).get(0), "I1" );
		assertEquals(list.get(0).get(1), "Tom Jame" );
		assertEquals(list.get(0).get(2), "32" );
	}

}
