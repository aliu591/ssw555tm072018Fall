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

}
