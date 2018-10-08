package sprint1;
import java.util.ArrayList;

public class Family {
	public String id_fam;
	public String id_husband;
	public String id_wife;
	public String name_hasband;
	public String name_wife;
	public ArrayList<String> id_children;
	public String marr_year;
	public String marr_month;
	public String marr_day;
	public String div_year;
	public String div_month;
	public String div_day;
	
	public Family() {
		this.id_fam = "NA";
		this.id_husband = "NA";
		this.id_wife = "NA";
		this.name_hasband = "NA";
		this.name_wife = "NA";
		this.id_children = new ArrayList<String>();
		this.marr_year = "NA";
		this.marr_month = "NA";
		this.marr_day = "NA";
		this.div_year = "NA";
		this.div_month = "NA";
		this.div_day = "NA";
	}
}
