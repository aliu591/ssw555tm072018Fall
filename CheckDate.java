import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class CheckDate {

	

	
	
	/**
	 * sprint1 US02 Birth before marriage
	 */
	public void US02(List<Person> people, List<Family> families) {
		Family fam;
		Person hus = null;
		Person wife = null;
		for(int i=0; i<families.size();i++){
			fam = families.get(i);
			for(int j=0; j< people.size();j++){
				if(people.get(j).id_indi.equals(fam.id_husband)){
					hus = people.get(j);
				}
				if(people.get(j).id_indi.equals(fam.id_wife)){
					wife = people.get(j);
				}
			}
			if(Integer.parseInt(hus.birt_year) < Integer.parseInt(fam.marr_year)){
				System.out.println("Error: US02: Birth of "+ hus.name +" should occur before marriage");
			}else if(Integer.parseInt(hus.birt_year)==Integer.parseInt(fam.marr_year)){
				if(Integer.parseInt(hus.birt_month)<Integer.parseInt(fam.marr_month)){
					System.out.println("Error: US02: Birth of "+ hus.name +" should occur before marriage");
				}else if(Integer.parseInt(hus.birt_month)==Integer.parseInt(fam.marr_month)){
					if(Integer.parseInt(hus.birt_day)<Integer.parseInt(fam.marr_day)){
						System.out.println("Error: US02: Birth of "+ hus.name +" should occur before marriage");
					}
				}
			}
			if(Integer.parseInt(wife.birt_year)<Integer.parseInt(fam.marr_year)){
				System.out.println("Error: US02: Birth of "+ wife.name +" should occur before marriage");
			}else if(Integer.parseInt(wife.birt_year)==Integer.parseInt(fam.marr_year)){
				if(Integer.parseInt(wife.birt_month)<Integer.parseInt(fam.marr_month)){
					System.out.println("Error: US02: Birth of "+ wife.name +" should occur before marriage");
				}else if(Integer.parseInt(wife.birt_month)==Integer.parseInt(fam.marr_month)){
					if(Integer.parseInt(wife.birt_day)<Integer.parseInt(fam.marr_day)){
						System.out.println("Error: US02: Birth of "+ wife.name +" should occur before marriage");
					}
				}
			}
			
		}
		
	}
	
	
	/**
	 * sprint1 US12 Parents not too old
	 */
	public void US12(List<Person> people, List<Family> families) {
		Family fam;
		Person hus = null;
		Person wife = null;
		Person child = null;
		for(int i=0; i<families.size();i++){
			fam = families.get(i);
			for(int j=0; j< people.size();j++){
				if(people.get(j).id_indi.equals(fam.id_husband)){
					hus = people.get(j);
				}
				if(people.get(j).id_indi.equals(fam.id_wife)){
					wife = people.get(j);
				}
			}
			if(fam.id_children.size()>0){
				for(int k = 0; k<fam.id_children.size();k++){
					for(int n = 0; n<people.size();n++){
						if(people.get(n).id_indi.equals(fam.id_children.get(k))){
							child = people.get(n);
						}
					}
					if(Integer.parseInt(wife.age)-Integer.parseInt(child.age)>=60){
						System.out.println("Error US12: Mother "+ wife.name+" should be less than 60 years older than her children "+child.name);
					}
					if(Integer.parseInt(hus.age)-Integer.parseInt(child.age)>=80){
						System.out.println("Error US12: Father "+ hus.name+" should be less than 80 years older than her children "+child.name);
					}
				}
			
			}	
		}
	}
	
	
	/**
	 * sprint1 US42 Reject illegal dates
	 */
	public void US42 (List<Person> people, List<Family> families) {
		for (int i = 0; i < people.size(); i++) {
			if (!people.get(i).birthValid)
				System.out.println("ERROR: INDIVIDUAL: US42: "+ people.get(i).id_indi + ": Birthday date (" + people.get(i).birt_year +"-"+ people.get(i).birt_month+"-"+ people.get(i).birt_day +") is invalid");
			
			if (!people.get(i).deathValid)
				System.out.println("ERROR: INDIVIDUAL: US42: "+ people.get(i).id_indi + ": Death date ("+ people.get(i).deat_year +"-"+ people.get(i).deat_month+"-"+ people.get(i).deat_day +") is invalid");
		}
		
		for (int i = 0; i < families.size(); i++) {
			if (!families.get(i).marryDateValid)
				System.out.println("ERROR: FAMILY: US42: "+ families.get(i).id_fam + ": Marry date ("+ families.get(i).marr_year +"-"+ families.get(i).marr_month+"-"+ families.get(i).marr_day+ ") is invalid");
			
			if (!families.get(i).divorceDateValid)
				System.out.println("ERROR: FAMILY: US42: "+ families.get(i).id_fam + ": Divorce date ("+families.get(i).div_year +"-"+ families.get(i).div_month+"-"+ families.get(i).div_day+ ") is invalid");
		}
		
	}
	
}
