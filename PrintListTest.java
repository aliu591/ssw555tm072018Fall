import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PrintListTest {

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

}
