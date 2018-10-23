package sprint2;
import java.util.ArrayList;

public class Person {
	public String id_indi;
	public ArrayList<String> id_famc;
	public ArrayList<String> id_fams;
	public String name;
	public String sex;
	public String birt_year;
	public String birt_month;
	public String birt_day;
	public String alive;
	public String deat_year;
	public String deat_month;
	public String deat_day;
	public String age;	
	
	public boolean birthValid;
	public boolean deathValid;
	public boolean birthBeforeToday;
	public boolean deathBeforeToday;
	
	public Person() {
		this.id_indi = "NA";
		this.id_famc = new ArrayList<String>();
		this.id_fams = new ArrayList<String>();
		this.name = "NA";
		this.sex = "NA";
		this.birt_year = "NA";
		this.birt_month = "NA";
		this.birt_day = "NA";
		this.deat_year = "NA";
		this.deat_month = "NA";
		this.deat_day = "NA";
		this.alive = "True";
		this.birthValid = true;
		this.deathValid = true;
		this.birthBeforeToday = true;
		this.deathBeforeToday = true;
		this.age = "NA";
	}
	
}
