import java.util.*;
public class CheckPersonInfo {
	
	/**
     * sprint2 US23 Unique name and birthday
     */
    public void US23(List<Person> people) {
    	for (int i = 0; i < people.size(); i++) {
    		for (int j = i + 1; j < people.size() - 1; j++) {
    			Person person1 = people.get(i);
    			Person person2 = people.get(j);
    			if (person1.name.equals(person2.name) && person1.birt_year.equals(person2.birt_year) &&
    				person1.birt_month.equals(person2.birt_month) && person1.birt_day.equals(person2.birt_day)) {
    		    	System.out.println("ERROR: INDIVIDUAL: US23: "+ person1.id_indi +" and " +person2.id_indi+ " has the same name and birthday.");
    			}
    		}
    	}         
    }
	/**
	 * sprint2 US21 Correct gender for role
	 * Husband in family should be male and wife in family should be female
	 */
	public void US21(List<Person> people, List<Family> families) {
		for (int i = 0; i < families.size(); i++) {
			if(!families.get(i).id_wife.equals("NA") && !families.get(i).id_husband.equals("NA")){
				for(int j = 0; j < people.size(); j++){
					if(people.get(j).id_indi.equals(families.get(i).id_wife)){
						if(!people.get(j).sex.equals("F")){
							System.out.println("ERROR: FAMILY: US21: "+ families.get(i).id_fam+": Wife "+families.get(i).id_wife+" has wrong gender." );
						}
					}
					if(people.get(j).id_indi.equals(families.get(i).id_husband)){
						if(!people.get(j).sex.equals("M")){
							System.out.println("ERROR: FAMILY: US21: "+ families.get(i).id_fam+": Husband "+families.get(i).id_husband+" has wrong gender." );
						}
					}
				}
			}
		}
	}
	
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

