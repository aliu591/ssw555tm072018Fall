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
				System.out.println("Error US02: Birth of "+ hus.name +" should occur before marriage");
			}else if(Integer.parseInt(hus.birt_year)==Integer.parseInt(fam.marr_year)){
				if(Integer.parseInt(hus.birt_month)<Integer.parseInt(fam.marr_month)){
					System.out.println("Error US02: Birth of "+ hus.name +" should occur before marriage");
				}else if(Integer.parseInt(hus.birt_month)==Integer.parseInt(fam.marr_month)){
					if(Integer.parseInt(hus.birt_day)<Integer.parseInt(fam.marr_day)){
						System.out.println("Error US02: Birth of "+ hus.name +" should occur before marriage");
					}
				}
			}
			if(Integer.parseInt(wife.birt_year)<Integer.parseInt(fam.marr_year)){
				System.out.println("Error US02: Birth of "+ wife.name +" should occur before marriage");
			}else if(Integer.parseInt(wife.birt_year)==Integer.parseInt(fam.marr_year)){
				if(Integer.parseInt(wife.birt_month)<Integer.parseInt(fam.marr_month)){
					System.out.println("Error US02: Birth of "+ wife.name +" should occur before marriage");
				}else if(Integer.parseInt(wife.birt_month)==Integer.parseInt(fam.marr_month)){
					if(Integer.parseInt(wife.birt_day)<Integer.parseInt(fam.marr_day)){
						System.out.println("Error US02: Birth of "+ wife.name +" should occur before marriage");
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
	 * sprint1 US42 Reject illegitimate dates
	 */
	public void US42 (List<Person> people, List<Family> families) throws Exception{
//		for (int i = 0; i < people.size(); i++) {
//			try {
//				LocalDate marryday = LocalDate.of(Integer.parseInt(people.get(i).birt_year), Integer.parseInt(people.get(i).birt_month), 
//						  Integer.parseInt(people.get(i).birt_day));
//			}catch (Exception e){
//				System.out.println("ERROR: FAMILY: US42:"+ + ":Birthday is invalid");
//			}
//		
//		}
	}
	
}
