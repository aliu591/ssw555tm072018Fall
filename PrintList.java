import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import wagu.Block;
import wagu.Board;
import wagu.Table;

public class PrintList {

	/**
	 * Print table 
	 */
	public void printTable (List<String> header, List<List<String>> rowList, int boardSize) {
		Board board = new Board(boardSize);
        Table table = new Table(board, boardSize, header, rowList);
        table.setGridMode(Table.GRID_COLUMN);
        //setting width and data-align of columns
        List<Integer> colWidthsList = new ArrayList<>();
        for (int i = 0; i < header.size(); i++) {
        	colWidthsList.add(22);
        }
        List<Integer> colAlignList = new ArrayList<>();
        for (int i = 0; i < header.size(); i++) {
        	colAlignList.add(Block.DATA_CENTER);
        }
        table.setColWidthsList(colWidthsList);
        table.setColAlignsList(colAlignList);
        
        board.setInitialBlock(table.tableToBlocks());
        board.build();
        System.out.println(board.getPreview());
	}
	
	/**
	 * Sprint 1: US 29, list deceased
	 */
	public List<List<String>> US29(List<Person> people, List<Family> families) {	
		List <String> headerList_death = Arrays.asList("ID", "Name", "Death Date");;		//table header for deceased
		List<List<String>> rowList_death = new ArrayList<>();;		//list of death for print table
		
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
		printTable(headerList_death, rowList_death, 530);
		return rowList_death;
	}
}
