package sprint2;
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
}

