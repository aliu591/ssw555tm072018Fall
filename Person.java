package sprint1;
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
	}
	
	public Person(String id_indi, ArrayList<String> id_famc, ArrayList<String> id_fams, 
				  String name, String sex, String birt_year, String birt_month, String birt_day,
				  String deat_year, String deat_month, String deat_day) {
			
		this.id_indi = id_indi;
		this.id_famc = id_famc;
		this.id_fams = id_fams;
		this.name = name;
		this.sex = sex;
		this.birt_year = birt_year;
		this.birt_month = birt_month;
		this.birt_day = birt_day;
		this.deat_year = deat_year;
		this.deat_month = deat_month;
		this.deat_day = deat_day;
		this.alive = "True";
	}
}
