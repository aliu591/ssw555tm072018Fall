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
    public void printTable(List<String> header, List<List<String>> rowList, int boardSize) {
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
        List<String> headerList_death = Arrays.asList("ID", "Name", "Death Date");
        ;        //table header for deceased
        List<List<String>> rowList_death = new ArrayList<>();        //list of death for print table

        for (int i = 0; i < people.size(); i++) {
            List<String> deathPerson = new ArrayList<>(Arrays.asList("0", "0", "0"));
            if (people.get(i).alive.equals("False")) {
                deathPerson.set(0, people.get(i).id_indi);
                deathPerson.set(1, people.get(i).name);
                deathPerson.set(2, people.get(i).deat_year + "-" + people.get(i).deat_month + "-" + people.get(i).deat_day);
                rowList_death.add(deathPerson);
            }
        }
        if (rowList_death.size() > 0) {
            System.out.println("Deceased");
            printTable(headerList_death, rowList_death, 530);
        } else {
            System.out.println("No Deceased In the GEDCOM");
        }

        return rowList_death;
    }

    /**
     * Sprint 3: US 33, list orphans
     */
    public List<List<String>> US33(List<Person> people, List<Family> families) {
        List <String> headerList_orphans = Arrays.asList("ID", "Name", "Family ID");;	//table header for orphans
        List<List<String>> rowList_orphans = new ArrayList<>();							//list of orphans for print table
        for (int i = 0; i < families.size(); i++) {
            List<String> orphans = new ArrayList<>(Arrays.asList("0","0","0"));
            if (families.get(i).husband.alive.equals("False") && families.get(i).wife.alive.equals("False") ) {
                for (Person p: families.get(i).children) {
                    if (p.alive.equals("True") && Integer.parseInt(p.age) < 18) {
                        orphans.set(0, p.id_indi);
                        orphans.set(1, p.name);
                        orphans.set(2, families.get(i).id_fam);
                        rowList_orphans.add(orphans);
                    }
                }
            }
        }
        if (rowList_orphans.size() > 0) {
            System.out.println("Orphans");
            printTable(headerList_orphans, rowList_orphans, 530);
        } else {
            System.out.println("No Orphans In the GEDCOM");
        }
        return rowList_orphans;
    }

    /**
     * sprint3 US30 List all living married people in a GEDCOM file
     */
    public List<List<String>> US30(List<Person> people, List<Family> families) {
        List<String> headerList_living_married = Arrays.asList("ID", "Name", "Family ID");
        List<List<String>> rowList_living_married = new ArrayList<>();        
        for (int i = 0; i < families.size(); i++) {
            if (families.get(i).marryDateValid && !families.get(i).marr_year.equals("NA") && families.get(i).div_year.equals("NA")) {
                for (int j = 0; j < people.size(); j++) {
                    if (people.get(j).id_indi.equals(families.get(i).id_husband) ||
                            people.get(j).id_indi.equals(families.get(i).id_wife)) {
                        if (people.get(j).alive.equals("True")) {
                            List<String> marriedPerson = new ArrayList<>(Arrays.asList("0", "0", "0"));
                            marriedPerson.set(0, people.get(j).id_indi);
                            marriedPerson.set(1, people.get(j).name);
                            marriedPerson.set(2, families.get(i).id_fam);
                            rowList_living_married.add(marriedPerson);
                        }
                    }
                }
            }
        }
        if (rowList_living_married.size() > 0) {
            System.out.println("Living married ");
            printTable(headerList_living_married, rowList_living_married, 530);
        } else {
            System.out.println("No living married  In the GEDCOM");
        }
        return rowList_living_married;
    }

}
