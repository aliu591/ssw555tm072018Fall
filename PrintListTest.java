import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PrintListTest {
	@Test
	public void testUS30() throws Exception {
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

		family1.marryDateValid = true;
		family1.divorceDateValid = false;
		family1.id_wife = "I1";
		family1.id_fam = "F1";

		family2.marryDateValid = true;
		family2.divorceDateValid = true;
		family2.id_wife = "I2";
		family2.id_husband = "I3";
		family2.id_fam = "F2";

		family3.marryDateValid = true;
		family3.divorceDateValid = true;
		family3.id_wife = "I2";
		family3.id_husband = "I3";
		family3.id_fam = "F3";


		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		families.add(family1);
		families.add(family2);
		families.add(family3);

		PrintList printList = new PrintList();
		List<List<String>> list = printList.US30(people, families);

		assertEquals(list.get(0).get(0), "I1" );
		assertEquals(list.get(0).get(1), "XiaoLi" );
		assertEquals(list.get(0).get(2), "F1" );
	}

	@Test
	public void testUS29() { 
		List<Person> people = new ArrayList<>();    
		List<Family> families = new ArrayList<>();    
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
		List<List<String>> list = printList.US29(people, families);
		
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
		List<List<String>> list = printList.US33(people, families);
		
		assertEquals(list.get(0).get(0), "I3" );
		assertEquals(list.get(0).get(1), "PPPYYY" );
		assertEquals(list.get(0).get(2), "F1" );
	}


}
