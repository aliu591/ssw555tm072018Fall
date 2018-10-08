package sprint1;
import wagu.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.*;
import java.util.*;


public class ParseGEDCOM {
	private List<String> headerList_indi;    	//table header for indi
	private List<String> headerList_fam;		//table header for fam
	private List <String> headerList_death;		//table header for deceased
	private List<String> personInfo;
	private List<String> familyInfo;
	private List<List<String>> rowList_indi;		//list of indi
	private List<List<String>> rowList_fam;			//list of fam
	private List<List<String>> rowList_death;		//list of death
	private Person person;
	private Family family;
	public List<Person> people = new ArrayList<>();    //List of persons with id 
	public List<Family> families =  new ArrayList<>(); //List of families with id 
	
	
	/**
	 * Project 3
	 * @throws IOException
	 */
	public void parse() throws IOException {
		List<String> list = new ArrayList<>(); 
		list.add("INDI");
		list.add("1 NAME");
		list.add("1 SEX");
		list.add("1 BIRT");
		list.add("1 DEAT");
		list.add("1 FAMC");
		list.add("1 FAMS");
		list.add("FAM");
		list.add("1 MARR");
		list.add("1 HUSB");
		list.add("1 WIFE");
		list.add("1 CHIL");
		list.add("1 DIV");
		list.add("2 DATE");
		list.add("0 HEAD");
		list.add("0 TRLR");
		list.add("0 NOTE");
		
		//Tables
		headerList_indi = Arrays.asList("ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
		headerList_fam = Arrays.asList("ID", "Married", "Divorced", "Husband ID", "Husband Name", "Wife ID", "Wife Name", "Children");
		headerList_death = Arrays.asList("ID", "Name", "Death Date");
		rowList_indi = new ArrayList<>();
		rowList_fam = new ArrayList<>();
		rowList_death = new ArrayList<>();
		personInfo = new ArrayList<>(Arrays.asList("0","0","0","0","0","0","0","0","0"));
		familyInfo = new ArrayList<>(Arrays.asList("0","0","0","0","0","0","0","0"));
		StringBuffer sb_fams = null;
		StringBuffer sb_famc = null;
		StringBuffer sb_chil = null;
		boolean addIndi = true;
		boolean firstAddIndi = false;
		boolean firstAddFam = false;
		
		
		while (true) {
			BufferedReader readerOfFile = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("The name of the parsing file: " );
			try {	
				//get the file from current path
				String fileName = readerOfFile.readLine();
				URL path = ParseGEDCOM.class.getResource(fileName);
				File f = new File(path.getFile());
				//check the validation of the file
				if(f.exists() && f.canRead() && f.isFile()) {
					BufferedReader fileReader = new BufferedReader(new FileReader(f));
					String str;
					while((str = fileReader.readLine()) != null){
						boolean check = false;
						//check if contains the tag
						for(String k : list) {
							if (str.contains(k)) {
								check = true;
								String tag = k;
								
								//corner case
								if (tag.equals("INDI")) {
									if(str.charAt(0) == '0') {
										
										if (firstAddIndi) {
											if(personInfo.get(0) != "0") {
												for (int i = 0; i < 9; i++) {
													if (personInfo.get(i) == "0") {
														personInfo.set(i, "NA");
													}
												}
												people.add(person);
												rowList_indi.add(personInfo);
											}
										}
										firstAddIndi = true;
										//reset variables
										sb_fams = new StringBuffer();
										sb_famc = new StringBuffer();
										personInfo = new ArrayList<>(Arrays.asList("0","0","0","0","0","0","0","0","0"));
										person = new Person(); //for future use
										
										
										//add id
										int index = str.indexOf(tag);
										person.id_indi = str.substring(2, index  - 1);
										personInfo.set(0, person.id_indi);
										
									
									} else {
										//invalid individual level
										int index = str.indexOf(tag);
										StringBuilder builder = new StringBuilder(str);
										builder.delete(index - 1, index + tag.length());
										builder.replace(1, 2, "|");
										builder.insert(2, tag + "|N|");
										System.out.println("<-- " + builder.toString());
										System.out.println("Invalid INDI level. Please check GEDCOM file");
										System.exit(0);
									}
								} else if(tag.equals("FAM")){

									//read family id
									if(str.charAt(0) == '0') {
										if (firstAddFam) {
											if(familyInfo.get(0) != "0") {
												for (int i = 0; i< 8; i++) {
													if (familyInfo.get(i) == "0") {
														familyInfo.set(i, "NA");
													}
												}
												rowList_fam.add(familyInfo);
												families.add(family);
											}
										}
										
										//reset variable for new family id
										familyInfo = new ArrayList<>(Arrays.asList("0","0","0","0","0","0","0","0"));
										sb_chil = new StringBuffer();
										family = new Family();
									
																				
										//set id
										int index = str.indexOf(tag);
										familyInfo.set(0, str.substring(2, index  - 1));
										family.id_fam = str.substring(2, index  - 1);
										firstAddFam = true;

									} else {
										//invalid fam level
										int index = str.indexOf(tag);
										StringBuilder builder = new StringBuilder(str);
										builder.delete(index - 1, index + tag.length());
										builder.replace(1, 2, "|");
										builder.insert(2, tag + "|N|");
										System.out.println("<-- " + builder.toString());
										System.out.println("Invalid FAM level. Please check GEDCOM file");
										System.exit(0);
									} 								
								} else {
									//family tag
									if (tag.equals("1 HUSB")) {
										String husb = str.substring(7);
										familyInfo.set(3, husb);
										family.id_husband = husb;
										
										//find the name with id
										for (Person p : people) {
											if (p.id_indi.equals(husb)) {
												familyInfo.set(4, p.name);
												family.name_hasband = p.name;
												break;
											}
										}
										
										
									}
									
									if (tag.equals("1 WIFE")) {
										String wife = str.substring(7);
										familyInfo.set(5, wife);
										family.id_wife = wife;
										//find the name with id
										for (Person p : people) {
											if (p.id_indi.equals(wife)) {
												familyInfo.set(6, p.name);
												family.name_wife = p.name;
												break;
											}
										}
										
									}
									
									if (tag.equals("1 MARR")) {
										String date = fileReader.readLine();
										if (date.contains("2 DATE")) {
											String longDate = date.substring(7);
											String year =  date.substring(date.length() - 4, date.length());
											String month = date.substring(date.length() - 8, date.length() - 5);
											
											char[] arr = longDate.toCharArray();
											
											int index = 0;
											for (int i = 0; i < arr.length; i++) {
												if (arr[i] == ' ') {
													index = i;
													break;
												}
											}
											
											String day = longDate.substring(0, index);
											//single digit day
											if (day.length() == 1) {
												StringBuffer sb = new StringBuffer();
												sb.append("0");
												sb.append(day);
												day = sb.toString();
											}
											String numMonth = convertMonth(month);
											
											//check if the date is valid
											try {
												LocalDate marryday = LocalDate.of(Integer.parseInt(year), Integer.parseInt(numMonth), 
														  Integer.parseInt(day));
											}catch (Exception e){
												System.out.println("ERROR: FAMILY: US42:"+ familyInfo.get(0)+ ":Marry day is invalid");
												throw e;
											}
											
											familyInfo.set(1, year+"-" + numMonth + "-"+day);
											family.marr_year = year;
											family.marr_month = numMonth;
											family.marr_day = day;
										}
									}
									
									if (tag.equals("1 DIV")) {
										String date = fileReader.readLine();
										if (date.contains("2 DATE")) {
											String longDate = date.substring(7);
											String year =  date.substring(date.length() - 4, date.length());
											String month = date.substring(date.length() - 8, date.length() - 5);
											
											char[] arr = longDate.toCharArray();
											
											int index = 0;
											for (int i = 0; i < arr.length; i++) {
												if (arr[i] == ' ') {
													index = i;
													break;
												}
											}
											String day = longDate.substring(0, index);
											//single digit day
											if (day.length() == 1) {
												StringBuffer sb = new StringBuffer();
												sb.append("0");
												sb.append(day);
												day = sb.toString();
											}
											String numMonth = convertMonth(month);
													
											//check if the date is valid
											try {
												LocalDate divorceday = LocalDate.of(Integer.parseInt(year), Integer.parseInt(numMonth), 
														  Integer.parseInt(day));
											}catch (Exception e){
												System.out.println("ERROR: FAMILY: US42:" + familyInfo.get(0)+ ": Divorced day is invalid");
												throw e;
											}
											
											familyInfo.set(2, year+"-" + numMonth + "-"+day);
											family.div_year = year;
											family.div_month = numMonth;
											family.div_day = day;

										}
										
									}
									
									if (tag.equals("1 CHIL")) {
										family.id_children.add(str.substring(7));
										if(familyInfo.get(7) == "0") {
											sb_chil.append("'"+ str.substring(7)+"'");
											familyInfo.set(7, "{"+ sb_chil.toString() + "}");
										} else {
											sb_chil.append(", '" +str.substring(7) + "'");
											familyInfo.set(7, "{"+ sb_chil.toString() + "}");
										}
									}
									
//									if (tag.equals("0 TRLR")) {
//										
//									}
									
									//individual tag 
									if (tag.equals("1 NAME")) {
										person.name = str.substring(7);
										personInfo.set(1, person.name);
										
									}
									
									if (tag.equals("1 SEX")) {
										person.sex = str.substring(6);
										personInfo.set(2, person.sex);
									}
									
									if (tag.equals("1 BIRT")) {
										String date = fileReader.readLine();
										if (date.contains("2 DATE")) {
											String longDate = date.substring(7);
											String year =  date.substring(date.length() - 4, date.length());
											String month = date.substring(date.length() - 8, date.length() - 5);
											
											char[] arr = longDate.toCharArray();
											
											int index = 0;
											for (int i = 0; i < arr.length; i++) {
												if (arr[i] == ' ') {
													index = i;
													break;
												}
											}
											String day = longDate.substring(0, index);
											//single digit day
											if (day.length() == 1) {
												StringBuffer sb = new StringBuffer();
												sb.append("0");
												sb.append(day);
												day = sb.toString();
											}
											String numMonth = convertMonth(month);
											person.birt_year = year;
											person.birt_month = numMonth;
											person.birt_day = day;
											
											personInfo.set(3, person.birt_year+"-"+person.birt_month+"-"+person.birt_day);
											
											//calculate the age
											try {
												LocalDate today = LocalDate.now();                        
												LocalDate birthday = LocalDate.of(Integer.parseInt(year), Integer.parseInt(numMonth), 
																				  Integer.parseInt(day));  
												Period diff = Period.between(birthday, today);
												person.age = Integer.toString(diff.getYears());
											} catch (Exception e){
												System.out.println("ERROR: INDIVIDUAL: US42:"+ personInfo.get(0)+ ": Birthday is invalid");
												throw e;
											}
											
											personInfo.set(4, person.age);
											personInfo.set(5, person.alive);
											personInfo.set(6, "NA"); //not death
											
										} 
									}
									
									if (tag.equals( "1 FAMS")) {
										person.id_fams.add(str.substring(7));
									
										if(personInfo.get(8) == "0") {
											sb_fams.append("'"+ str.substring(7)+"'");
											personInfo.set(8, "{"+ sb_fams.toString() + "}");
										} else {
											sb_fams.append(", '" +str.substring(7) + "'");
											personInfo.set(8, "{"+ sb_fams.toString() + "}");
										}
										
									}
										
									if (tag.equals("1 FAMC")) {
										person.id_famc.add(str.substring(7));
										
										if(personInfo.get(7) == "0") {
											sb_famc.append("'"+ str.substring(7)+"'");
											personInfo.set(7, "{"+ sb_famc.toString() + "}");
										} else {
											sb_famc.append(", '" +str.substring(7) + "'");
											personInfo.set(7, "{"+ sb_famc.toString() + "}");
										}
									}
									
									if (tag.equals("1 DEAT")) {
										String date = fileReader.readLine();
										if (date.contains("2 DATE")) {
											String longDate = date.substring(7);
											String year =  date.substring(date.length() - 4, date.length());
											String month = date.substring(date.length() - 8, date.length() - 5);
											
											char[] arr = longDate.toCharArray();
											
											int index = 0;
											for (int i = 0; i < arr.length; i++) {
												if (arr[i] == ' ') {
													index = i;
													break;
												}
											}
											String day = longDate.substring(0, index);
											
											//single digit day
											if (day.length() == 1) {
												StringBuffer sb = new StringBuffer();
												sb.append("0");
												sb.append(day);
												day = sb.toString();
											}
											String numMonth = convertMonth(month);
											
											person.deat_year = year;
											person.deat_month = numMonth;
											person.deat_day = day;	
											person.alive = "False";	
											
											try {
												LocalDate birthday = LocalDate.of(Integer.parseInt(person.birt_year), 
																				  Integer.parseInt(person.birt_month), 
																				  Integer.parseInt(person.birt_day));  	
												LocalDate deathday = LocalDate.of(Integer.parseInt(year), Integer.parseInt(numMonth), 
														  Integer.parseInt(day));  	
												Period diff = Period.between(birthday, deathday);
												person.age = Integer.toString(diff.getYears());
											} catch(Exception e) {
												System.out.println("ERROR: INDIVIDUAL: US42:"+ personInfo.get(0)+ ": Death date is invalid");
											}
											
											personInfo.set(4, person.age);
											personInfo.set(5, person.alive);
											personInfo.set(6, person.deat_year+"-"+person.deat_month+"-"+person.deat_day);
										} 
									}

								}
								break;
							}
						}

						//print the output for invalid tag
						if (!check) {
							StringBuilder builder = new StringBuilder(str);
							builder.replace(1, 2, "|");							
							String result = builder.toString();
							int count = 0;
							for (int i = 0; i < result.length(); i++) {
								if (result.charAt(i) != ' ') {
									if (Character.isUpperCase(result.charAt(i))) {
										count++;
									}
								} else {
									break;
								}
							}
							builder.replace(2+ count, 3+count, "|N|");
							System.out.println("<-- " + builder.toString());
							System.out.println("Invalid tag. Please check GEDCOM file");
							System.exit(0);
						} 	
					}
					
					
					//add last parsed person
					if(personInfo.get(0) != "0") {
						for (int i = 0; i < 9; i++) {
							if (personInfo.get(i) == "0") {
								personInfo.set(i, "NA");
							}
						}
						people.add(person);
						rowList_indi.add(personInfo);							
					}
						
					//sort id
					sortIndiID();
		
					//print individual table
					printIndividualTable();
					
					//add last parsed family
					if(familyInfo.get(0) != "0") {
						for (int i = 0; i< 8; i++) {
							if (familyInfo.get(i) == "0") {
								familyInfo.set(i, "NA");
							}
						}
						families.add(family);
						rowList_fam.add(familyInfo);
					}
					
					//sort family id
					sortFamID();
					
					//print families table
					printFamilyTable();
					fileReader.close();
					
					
					break;
				} else {
					System.out.println("File Error");
				}
			} catch(Exception e) {
				System.out.println("File does not exist or GEDCOM format error");
			}
		}
	}
	
	/**
	 * sprint1 US02
	 */
	public void US02() throws IOException{
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
			if(Integer.parseInt(hus.birt_year)<Integer.parseInt(fam.marr_year)){
				throw new IOException("Error US02: Birth of "+ hus.name +" should occur before marriage");
			}else if(Integer.parseInt(hus.birt_year)==Integer.parseInt(fam.marr_year)){
				if(Integer.parseInt(hus.birt_month)<Integer.parseInt(fam.marr_month)){
					throw new IOException("Error US02: Birth of "+ hus.name +" should occur before marriage");
				}else if(Integer.parseInt(hus.birt_month)==Integer.parseInt(fam.marr_month)){
					if(Integer.parseInt(hus.birt_day)<Integer.parseInt(fam.marr_day)){
						throw new IOException("Error US02: Birth of "+ hus.name +" should occur before marriage");
					}
				}
			}
			if(Integer.parseInt(wife.birt_year)<Integer.parseInt(fam.marr_year)){
				throw new IOException("Error US02: Birth of "+ wife.name +" should occur before marriage");
			}else if(Integer.parseInt(wife.birt_year)==Integer.parseInt(fam.marr_year)){
				if(Integer.parseInt(wife.birt_month)<Integer.parseInt(fam.marr_month)){
					throw new IOException("Error US02: Birth of "+ wife.name +" should occur before marriage");
				}else if(Integer.parseInt(wife.birt_month)==Integer.parseInt(fam.marr_month)){
					if(Integer.parseInt(wife.birt_day)<Integer.parseInt(fam.marr_day)){
						throw new IOException("Error US02: Birth of "+ wife.name +" should occur before marriage");
					}
				}
			}
			
		}
		
	}
	
	/**
	 * sprint1 US12
	 */
	public void US12() throws IOException{
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
						throw new IOException("Error US12: Mother "+ wife.name+" should be less than 60 years older than her children "+child.name);
					}
					if(Integer.parseInt(hus.age)-Integer.parseInt(child.age)>=80){
						throw new IOException("Error US12: Father "+ hus.name+" should be less than 80 years older than her children "+child.name);
					}
				}
			
			}	
		}
	}
	
	/**
	 * Sort Individual ID
	 */
	private void sortIndiID() {
		for(int i = 0; i < rowList_indi.size() - 1; i++) {
			int min = Integer.parseInt(rowList_indi.get(i).get(0).substring(1, rowList_indi.get(i).get(0).length()));
			int minIndex = i;
			for (int j = i + 1; j < rowList_indi.size(); j++) {
				if (Integer.parseInt(rowList_indi.get(j).get(0).substring(1, rowList_indi.get(j).get(0).length())) < min) {									
					min = Integer.parseInt(rowList_indi.get(j).get(0).substring(1, rowList_indi.get(j).get(0).length()));
						minIndex = j;
				}
		
			}
			List<String> temp = rowList_indi.get(i);
			rowList_indi.set(i, rowList_indi.get(minIndex));
			rowList_indi.set(minIndex, temp);
		}
	}
	
	/**
	 * Sort Family ID 
	 */
	private void sortFamID() {
		for(int i = 0; i < rowList_fam.size() - 1; i++) {
			int min = Integer.parseInt(rowList_fam.get(i).get(0).substring(1, rowList_fam.get(i).get(0).length()));
			int minIndex = i;
			for (int j = i + 1; j < rowList_fam.size(); j++) {
				if (Integer.parseInt(rowList_fam.get(j).get(0).substring(1, rowList_fam.get(j).get(0).length())) < min) {
					min = Integer.parseInt(rowList_fam.get(j).get(0).substring(1, rowList_fam.get(j).get(0).length()));
					minIndex = j;
				}
				
			}
			List<String> temp = rowList_fam.get(i);
			rowList_fam.set(i, rowList_fam.get(minIndex));
			rowList_fam.set(minIndex, temp);
		}
	}
	
	/**
	 * Project 3
	 */
	public void printIndividualTable() {
		System.out.println("Individuals");
		Board board = new Board(350);
        Table table = new Table(board, 350, headerList_indi, rowList_indi);
        table.setGridMode(Table.GRID_COLUMN);
        //setting width and data-align of columns
        List<Integer> colWidthsList = Arrays.asList(10, 30, 10, 14, 10, 10, 14, 30, 30);
        List<Integer> colAlignList = Arrays.asList(Block.DATA_CENTER, Block.DATA_CENTER, Block.DATA_CENTER, Block.DATA_CENTER, 
        										   Block.DATA_CENTER, Block.DATA_CENTER, Block.DATA_CENTER, Block.DATA_CENTER, 
        										   Block.DATA_CENTER);
        table.setColWidthsList(colWidthsList);
        table.setColAlignsList(colAlignList);
        
        Block tableBlock = table.tableToBlocks();
        board.setInitialBlock(tableBlock);
        board.build();
        String tableString = board.getPreview();
        System.out.println(tableString);
	}
	
	/**
	 * Project 3
	 */
	public void printFamilyTable() {
		System.out.println("Families");
		Board board1 = new Board(350);
        Table table1 = new Table(board1, 350, headerList_fam, rowList_fam);
        table1.setGridMode(Table.GRID_COLUMN);
        //setting width and data-align of columns
        List<Integer> colWidthsList1 = Arrays.asList(10, 14, 10, 10, 30, 10, 30, 30);
        List<Integer> colAlignList1 = Arrays.asList(Block.DATA_CENTER, Block.DATA_CENTER, Block.DATA_CENTER, Block.DATA_CENTER, 
        										   Block.DATA_CENTER, Block.DATA_CENTER, Block.DATA_CENTER, Block.DATA_CENTER);
        table1.setColWidthsList(colWidthsList1);
        table1.setColAlignsList(colAlignList1);
        
        Block tableBlock1 = table1.tableToBlocks();
        board1.setInitialBlock(tableBlock1);
        board1.build();
        String tableString1 = board1.getPreview();
        System.out.println(tableString1);
	}
	
	/**
	 * Sprint 1: US 29, list deceased
	 */
	public void printDeathPeople() {	
		for (int i = 0; i < people.size(); i++) {
			List<String> deathPerson = new ArrayList<>(Arrays.asList("0","0","0"));
			if (people.get(i).alive.equals("False")) {
				deathPerson.set(0, people.get(i).id_indi);
				deathPerson.set(1, people.get(i).name);
				deathPerson.set(2, people.get(i).deat_year+"-"+ people.get(i).deat_month+"-"+ people.get(i).deat_day);
				rowList_death.add(deathPerson);
			}
		}
		
		System.out.println("Deceased");
		Board board2 = new Board(350);
        Table table2 = new Table(board2, 350, headerList_death, rowList_death);
        table2.setGridMode(Table.GRID_COLUMN);
        //setting width and data-align of columns
        List<Integer> colWidthsList2 = Arrays.asList(10, 30, 14);
        List<Integer> colAlignList2 = Arrays.asList(Block.DATA_CENTER, Block.DATA_CENTER, Block.DATA_CENTER);
        table2.setColWidthsList(colWidthsList2);
        table2.setColAlignsList(colAlignList2);
        
        Block tableBlock2 = table2.tableToBlocks();
        board2.setInitialBlock(tableBlock2);
        board2.build();
        String tableString1 = board2.getPreview();
        System.out.println(tableString1);
	}
	
	

	/**
	 * For convert letter month to number month
	 * @param month
	 * @return String
	 */
	private String convertMonth(String month) {
		String numMonth = null;
		switch(month){
		case "JAN":
			numMonth = "01";
			break;
		case "FEB":
			numMonth = "02";
			break;
		case "MAR":
			numMonth = "03";
			break;
		case "APR":
			numMonth = "04";
			break;
		case "MAY":
			numMonth = "05";
			break;
		case "JUN":
			numMonth = "06";
			break;
		case "JUL":
			numMonth = "07";
			break;
		case "AUG":
			numMonth = "08";
			break;
		case "SEP":
			numMonth = "09";
			break;
		case "OCT":
			numMonth = "10";
			break;
		case "NOV":
			numMonth = "11";
			break;
		case "DEC":		
			numMonth = "12";
			break;
		}
		return numMonth;
	}
	
	/**
	 * Project 2
	 * @throws IOException
	 */
	public void printParse() throws IOException {
		//create a list to store known tag
		List<String> list = new ArrayList<>(); 
		list.add("INDI");
		list.add("1 NAME");
		list.add("1 SEX");
		list.add("1 BIRT");
		list.add("1 DEAT");
		list.add("1 FAMC");
		list.add("1 FAMS");
		list.add("FAM");
		list.add("1 MARR");
		list.add("1 HUSB");
		list.add("1 WIFE");
		list.add("1 CHIL");
		list.add("1 DIV");
		list.add("2 DATE");
		list.add("0 HEAD");
		list.add("0 TRLR");
		list.add("0 NOTE");
		
		while (true) {
			BufferedReader readerOfFile = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("The name of the parsing file: " );
			try {	
				//get the file from current path
				String fileName = readerOfFile.readLine();
				URL p = ParseGEDCOM.class.getResource(fileName);
				File f = new File(p.getFile());
				
				if(f.exists() && f.canRead() && f.isFile()) {
					BufferedReader fileReader = new BufferedReader(new FileReader(f));
					String str;
					while((str = fileReader.readLine()) != null){
						//print the input
						System.out.println("--> " + str);
						boolean check = false;
						//check if contains the tag
						for(String k : list) {
							if (str.contains(k)) {
								check = true;
								String tag = k;
								//corner case
								if (tag == "INDI" || tag == "FAM") {
									if(str.charAt(0) == '0') {
										int index = str.indexOf(tag);
										StringBuilder builder = new StringBuilder(str);
										builder.delete(index - 1, index + tag.length());
										builder.replace(1, 2, "|");
										builder.insert(2, tag + "|Y|");
										System.out.println("<-- " + builder.toString());
									} else {
										int index = str.indexOf(tag);
										StringBuilder builder = new StringBuilder(str);
										builder.delete(index - 1, index + tag.length());
										builder.replace(1, 2, "|");
										builder.insert(2, tag + "|N|");
										System.out.println("<-- " + builder.toString());
									}
								} else {
									tag = tag.substring(2, tag.length());
									int index = str.indexOf(tag);
									StringBuilder builder = new StringBuilder(str);
									builder.replace(1, 2, "|");
									builder.insert((index + tag.length()), "|Y");
									builder.replace((index + tag.length() + 2), (index + tag.length() + 3), "|");
									System.out.println("<-- " + builder.toString());
								}
								break;
							}
						}

						//print the output for invalid tag
						if (!check) {
							StringBuilder builder = new StringBuilder(str);
							builder.replace(1, 2, "|");							
							String result = builder.toString();
							int count = 0;
							for (int i = 0; i < result.length(); i++) {
								if (result.charAt(i) != ' ') {
									if (Character.isUpperCase(result.charAt(i))) {
										count++;
									}
								} else {
									break;
								}
							}
							builder.replace(2+ count, 3+count, "|N|");
							System.out.println("<-- " + builder.toString());
						} 	
					}
					fileReader.close();
					break;
				} else {
					System.out.println("File Error");
				}
			} catch(Exception e) {
				System.out.println("Invalid Input or File does not exist or file format error or empty line in gedcom file");
			}
		}
	}
}
