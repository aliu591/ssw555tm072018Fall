package sprint2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class checkinfo {
    
	/**
     * sprint2 US15 There should be fewer than 15 siblings in a family
     */
	public void US15(List<Family> families) {
		 Family fam;
		 for (int i = 0; i < families.size(); i++) {
	            fam = families.get(i);
	            if(fam.id_children.size()>15){
	            	 System.out.println("ERROR: FAMILY: US15: "+fam.id_fam+ " has more than 15 siblings");
	            }
		 }
	 }
	 
	
	 /**
	     * sprint2 US22 All individual IDs should be unique and all family IDs should be unique
	  */
	public void US22(List<Person> people, List<Family> families) {
		Set<String> person = new HashSet<>();
		 for (Person name : people) {
			 if (person.add(name.id_indi) == false) {
				 System.out.println("ERROR: INDIVIDUAL: US22: individual ID " + name.id_indi + " should be unique.");
			 }
		 }
		 Set<String> family = new HashSet<>();
		 for(Family fam: families){
			 if(family.add(fam.id_fam) == false){
				 System.out.println("ERROR: Family: US22: family ID " + fam.id_fam + " should be unique.");
			 }
		 }

	}
}
