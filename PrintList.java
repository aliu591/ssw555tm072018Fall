import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wagu.Block;
import wagu.Board;
import wagu.Table;

public class PrintList {
	public List <String> headerList_death = Arrays.asList("ID", "Name", "Death Date");;		//table header for deceased
	public List<List<String>> rowList_death = new ArrayList<>();;		//list of death
	
	/**
	 * Sprint 1: US 29, list deceased
	 */
	public void US29(List<Person> people, List<Family> families) {	
		
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
}
